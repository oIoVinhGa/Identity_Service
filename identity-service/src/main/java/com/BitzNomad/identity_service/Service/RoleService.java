package com.BitzNomad.identity_service.Service;

import com.BitzNomad.identity_service.DtoReponese.RoleReponese;
import com.BitzNomad.identity_service.DtoRequest.RoleRequest;
import com.BitzNomad.identity_service.Exception.AppException;
import com.BitzNomad.identity_service.Exception.ErrorCode;
import com.BitzNomad.identity_service.Mapper.RoleMapper;
import com.BitzNomad.identity_service.entity.Role;
import com.BitzNomad.identity_service.repository.PermissionRepository;
import com.BitzNomad.identity_service.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface RoleService {
    RoleReponese create(RoleRequest request);
    void delete(String RoleId);
    List<RoleReponese> findAll();

}
@Service
class RoleImpl implements RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PermissionRepository permissionRepository;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RoleReponese create(RoleRequest request) {
        Role role = RoleMapper.ConvertToEntity(request,permissionRepository);
        roleRepository.save(role);
        return RoleMapper.ConvertToReponese(role);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(String RoleId) {
        Role role = roleRepository.findById(RoleId).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        roleRepository.delete(role);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<RoleReponese> findAll() {
        return roleRepository.findAll().stream().map(RoleMapper::ConvertToReponese).collect(Collectors.toList());
    }
}