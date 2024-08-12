package com.BitzNomad.identity_service.Mapper.Auth;

import com.BitzNomad.identity_service.DtoReponese.PermissionReponese;
import com.BitzNomad.identity_service.DtoRequest.PermissionRequest;
import com.BitzNomad.identity_service.entity.Auth.Permission;
import org.modelmapper.ModelMapper;

public class PermissionMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Permission ConvertToEntity(PermissionRequest request){
        return modelMapper.map(request, Permission.class);
    }

    public static PermissionReponese convertToReponese(Permission permission){
        return modelMapper.map(permission, PermissionReponese.class);
    }
}
