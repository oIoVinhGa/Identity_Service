package com.BitzNomad.identity_service.Service;

import com.BitzNomad.identity_service.DtoReponese.UserReponese;
import com.BitzNomad.identity_service.DtoRequest.UserCreateRequest;
import com.BitzNomad.identity_service.DtoRequest.UserUpdateRequest;
import com.BitzNomad.identity_service.Exception.AppException;
import com.BitzNomad.identity_service.Exception.ErrorCode;
import com.BitzNomad.identity_service.Mapper.UserMapper;
import com.BitzNomad.identity_service.contant.PredefineRole;
import com.BitzNomad.identity_service.entity.Role;
import com.BitzNomad.identity_service.entity.User;
import com.BitzNomad.identity_service.repository.RoleRepository;
import com.BitzNomad.identity_service.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.internal.constraintvalidators.hv.ParameterScriptAssertValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;


public interface UserService {

    UserReponese createUser(UserCreateRequest request);

    UserReponese getMyInfo();

    User getUserById(String id);

    User updateUser(UserUpdateRequest request);

    void deleteUser(String id);

    List<UserReponese> getAllUsers();


}

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
class UserImplement implements UserService{
    @Autowired
   UserRepository userRepository;
    @Autowired
   PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;


    @Override
    public UserReponese createUser(UserCreateRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) throw new AppException(ErrorCode.UserExitsted);
        // Convert the UserRequest to a User entity
        User user = UserMapper.UserCreateconvertToEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        HashSet<Role> roles = new HashSet<>();
        roleRepository.findById(PredefineRole.USER_ROLE).ifPresent(roles::add);

        user.setRoles(roles);
        try {
            user = userRepository.save(user);
        } catch (DataIntegrityViolationException exception){
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        // Return the saved user entity
        return UserMapper.convertUserToReponese(user);

    }

    @Override
    public UserReponese getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();
        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        return UserMapper.convertUserToReponese(user);
    }

    @Override
    public User getUserById(String id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not Found !"));
    }

    @Override
    @PostAuthorize("returnObject.username == authentication.name")
    public User updateUser(UserUpdateRequest request) {
        User user = UserMapper.UserUpdateconvertToEntity(request,roleRepository,passwordEncoder);
        return userRepository.save(user);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }

    @Override
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PreAuthorize("hasAuthority('WRITE_DATA')")
    public List<UserReponese> getAllUsers() {
        log.info("In method get Users");
        return userRepository.findAll().stream().map(UserMapper::convertUserToReponese).toList();
    }
}
