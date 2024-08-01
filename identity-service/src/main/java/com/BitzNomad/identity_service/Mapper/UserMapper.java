package com.BitzNomad.identity_service.Mapper;
import com.BitzNomad.identity_service.DtoReponese.RoleReponese;
import com.BitzNomad.identity_service.DtoReponese.UserReponese;
import com.BitzNomad.identity_service.DtoRequest.UserCreateRequest;
import com.BitzNomad.identity_service.DtoRequest.UserUpdateRequest;
import com.BitzNomad.identity_service.entity.Role;
import com.BitzNomad.identity_service.entity.User;
import com.BitzNomad.identity_service.repository.RoleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


public class UserMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static User UserCreateconvertToEntity(UserCreateRequest request) {
        User u = modelMapper.map(request, User.class);
        return u;
    }

    public static User UserUpdateconvertToEntity(UserUpdateRequest request,RoleRepository rolerepository,PasswordEncoder passwordencoder) {
        User u = modelMapper.map(request, User.class);

        //get ra all role theo id
        var roles = rolerepository.findAllById(request.getRoles());
        u.setPassword(passwordencoder.encode(request.getPassword()));
        //convert to Set
        Set<Role> roleSet = new HashSet<>(roles);

        u.setRoles(roleSet);
        return u;
    }

    public static UserReponese convertUserToReponese(User user) {
        UserReponese u = modelMapper.map(user, UserReponese.class);
        Set<RoleReponese> roles = user.getRoles().stream().map(RoleMapper::ConvertToReponese).collect(Collectors.toSet());
        u.setRoles(roles);
        return u;
    }
}
