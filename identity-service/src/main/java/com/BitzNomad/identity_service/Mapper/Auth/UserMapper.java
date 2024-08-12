package com.BitzNomad.identity_service.Mapper.Auth;
import com.BitzNomad.identity_service.DtoReponese.RoleReponese;
import com.BitzNomad.identity_service.DtoReponese.UserReponese;
import com.BitzNomad.identity_service.DtoRequest.UserCreateRequest;
import com.BitzNomad.identity_service.DtoRequest.UserUpdateRequest;
import com.BitzNomad.identity_service.entity.Auth.Role;
import com.BitzNomad.identity_service.entity.Auth.User;
import com.BitzNomad.identity_service.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Component
@Lazy
public class UserMapper {

    @Autowired ModelMapper modelMapper;
    @Autowired RoleRepository roleRepository;
    @Autowired PasswordEncoder passwordEncoder;
    @Autowired RoleMapper roleMapper;

    public User UserCreateconvertToEntity(UserCreateRequest request) {
        User u = modelMapper.map(request, User.class);
        return u;
    }

    public User UserUpdateconvertToEntity(UserUpdateRequest request) {
        User u = modelMapper.map(request, User.class);

        //get ra all role theo id
        var roles = roleRepository.findAllById(request.getRoles());
        u.setPassword(passwordEncoder.encode(request.getPassword()));
        //convert to Set
        Set<Role> roleSet = new HashSet<>(roles);

        u.setRoles(roleSet);
        return u;
    }

    public  UserReponese convertUserToReponese(User user) {
        UserReponese u = modelMapper.map(user, UserReponese.class);
        Set<RoleReponese> roles = user.getRoles().stream().map(roleMapper::ConvertToReponese).collect(Collectors.toSet());
        u.setRoles(roles);
        return u;
    }
}
