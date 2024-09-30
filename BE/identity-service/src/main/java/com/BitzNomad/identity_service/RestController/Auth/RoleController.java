package com.BitzNomad.identity_service.RestController.Auth;


import com.BitzNomad.identity_service.DtoReponese.ApiResponse;
import com.BitzNomad.identity_service.DtoReponese.RoleReponese;
import com.BitzNomad.identity_service.DtoRequest.RoleRequest;
import com.BitzNomad.identity_service.Service.RoleService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("auth/role")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleController {

    @Autowired
    RoleService roleService;

    @PostMapping
    ApiResponse<RoleReponese> createRole(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleReponese>builder()
                .result(roleService.create(request))
                .build();
    }


    @GetMapping
    ApiResponse<List<RoleReponese>> getRoles() {
        return ApiResponse.<List<RoleReponese>>builder()
                .result(roleService.findAll())
                .build();
    }

    @DeleteMapping("{roleId}")
    ApiResponse<Void> deleteRole(@PathVariable("roleId") String roleId) {
        roleService.delete(roleId);
        return ApiResponse.<Void>builder()
                .status(204)
                .message("Role"+ roleId + "deleted successfully")
                .build();
    }

    @PutMapping
    ApiResponse<RoleReponese> updateRole(@RequestBody RoleRequest request) {
        return ApiResponse.<RoleReponese>builder()
                .message("Update Role successfully !")
                .result(roleService.update(request))
                .status(201)
                .build();
    }
}
