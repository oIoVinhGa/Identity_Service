package com.BitzNomad.identity_service.Mapper.Auth;

import com.BitzNomad.identity_service.DtoReponese.RoleReponese;
import com.BitzNomad.identity_service.DtoRequest.RoleRequest;
import com.BitzNomad.identity_service.entity.Auth.Permission;
import com.BitzNomad.identity_service.entity.Auth.Role;
import com.BitzNomad.identity_service.repository.PermissionRepository;
import lombok.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Lazy
public class RoleMapper {


  @Autowired ModelMapper modelMapper;

  @Autowired PermissionRepository permissionRepository;

    public Role ConvertToEntity(RoleRequest request) {
        Role role = modelMapper.map(request, Role.class);
        List<Permission> permissions =  permissionRepository.findAllById(request.getPermissions());
        Set<Permission> permissionSet = new HashSet<>(permissions);
        role.setPermissions(permissionSet);
        return role;
    }

    public RoleReponese ConvertToReponese(Role role) {
        RoleReponese reponese = modelMapper.map(role, RoleReponese.class);
        reponese.setPermissions(role.getPermissions().stream().map(PermissionMapper::convertToReponese).collect(Collectors.toSet()));
        return  reponese;
    }
}
