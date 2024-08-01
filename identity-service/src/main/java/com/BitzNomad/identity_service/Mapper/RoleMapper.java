package com.BitzNomad.identity_service.Mapper;

import com.BitzNomad.identity_service.DtoReponese.RoleReponese;
import com.BitzNomad.identity_service.DtoRequest.RoleRequest;
import com.BitzNomad.identity_service.entity.Permission;
import com.BitzNomad.identity_service.entity.Role;
import com.BitzNomad.identity_service.repository.PermissionRepository;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class RoleMapper {


    private static final ModelMapper modelMapper = new ModelMapper();

    public static Role ConvertToEntity(RoleRequest request,PermissionRepository permissionRepository) {
        Role role = modelMapper.map(request, Role.class);
        List<Permission> permissions =  permissionRepository.findAllById(request.getPermissions());
        Set<Permission> permissionSet = new HashSet<>(permissions);
        role.setPermissions(permissionSet);
        return role;
    }

    public static RoleReponese ConvertToReponese(Role role) {
        RoleReponese reponese = modelMapper.map(role, RoleReponese.class);
        reponese.setPermissions(role.getPermissions().stream().map(PermissionMapper::convertToReponese).collect(Collectors.toSet()));
        return  reponese;
    }
}
