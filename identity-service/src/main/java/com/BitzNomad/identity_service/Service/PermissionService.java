package com.BitzNomad.identity_service.Service;

import com.BitzNomad.identity_service.DtoReponese.PermissionReponese;
import com.BitzNomad.identity_service.DtoRequest.PermissionRequest;
import com.BitzNomad.identity_service.Exception.AppException;
import com.BitzNomad.identity_service.Exception.ErrorCode;
import com.BitzNomad.identity_service.Mapper.PermissionMapper;
import com.BitzNomad.identity_service.entity.Permission;
import com.BitzNomad.identity_service.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface PermissionService {
    PermissionReponese create(PermissionRequest request);
   void delete(String permissionid);
   List<PermissionReponese> findAll();
}
@Service
class PermissionImpl implements PermissionService {
    @Autowired
    PermissionRepository permissionRepository;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public PermissionReponese create(PermissionRequest request) {
        Permission permission = PermissionMapper.ConvertToEntity(request);
        permissionRepository.save(permission);
        return PermissionMapper.convertToReponese(permission);
    }


    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(String permissionid) {
        permissionRepository.findById(permissionid).orElseThrow( () -> new AppException(ErrorCode.USER_NOT_EXISTED));
        permissionRepository.deleteById(permissionid);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<PermissionReponese> findAll() {
        return permissionRepository.findAll().stream()
                .map(PermissionMapper::convertToReponese).collect(Collectors.toList());
    }
}
