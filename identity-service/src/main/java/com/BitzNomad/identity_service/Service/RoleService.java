package com.BitzNomad.identity_service.Service;

import com.BitzNomad.identity_service.DtoReponese.RoleReponese;
import com.BitzNomad.identity_service.DtoRequest.RoleRequest;
import com.BitzNomad.identity_service.Exception.AppException;
import com.BitzNomad.identity_service.Exception.ErrorCode;
import com.BitzNomad.identity_service.Mapper.Auth.RoleMapper;
import com.BitzNomad.identity_service.contant.PredefineRole;
import com.BitzNomad.identity_service.entity.Auth.Role;
import com.BitzNomad.identity_service.repository.PermissionRepository;
import com.BitzNomad.identity_service.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface RoleService {
    RoleReponese create(RoleRequest request);
    void delete(String RoleId);
    List<RoleReponese> findAll();
    RoleReponese update(RoleRequest request);
}
@Service
class RoleImpl implements RoleService {


    @Autowired
    RoleRepository roleRepository;
    @Autowired
    RoleMapper roleMapper;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public RoleReponese create(RoleRequest request) {
        if (roleRepository.existsById(request.getName())) {
            throw new AppException(ErrorCode.UserExitsted);
        }
        Role role = roleMapper.ConvertToEntity(request);
        roleRepository.save(role);

        return roleMapper.ConvertToReponese(role);
    }



    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void delete(String RoleId) {
        roleRepository.findById(RoleId).ifPresentOrElse(
              p->{
                  if (!Set.of(PredefineRole.ADMIN_ROLE, PredefineRole.USER_ROLE).contains(p.getName())) {
                      p.setDeleted(true);
                      roleRepository.save(p);
                  }
              } ,
                () -> {
                    throw new AppException(ErrorCode.USER_NOT_EXISTED);
                }
        );

    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<RoleReponese> findAll() {
        return roleRepository.findAll().stream().map(roleMapper::ConvertToReponese).collect(Collectors.toList());
    }
    @Override
    public RoleReponese update(RoleRequest request) {
        if (!roleRepository.existsById(request.getName())) {
            throw new AppException(ErrorCode.USER_NOT_EXISTED);
        }
        Role role = roleMapper.ConvertToEntity(request);
        roleRepository.save(role);
        return roleMapper.ConvertToReponese(role);
    }
//    @PostConstruct
//    public void init() {
//        System.out.println("PostContruct init");
//    }
//
//    @PreDestroy
//    public void destroy() {
//        System.out.println("RoleServiceImpl destroy");
//    }


}