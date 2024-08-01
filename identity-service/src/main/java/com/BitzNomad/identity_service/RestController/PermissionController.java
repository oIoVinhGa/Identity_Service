package com.BitzNomad.identity_service.RestController;

import com.BitzNomad.identity_service.DtoReponese.ApiResponse;
import com.BitzNomad.identity_service.DtoReponese.PermissionReponese;
import com.BitzNomad.identity_service.DtoRequest.PermissionRequest;
import com.BitzNomad.identity_service.Service.PermissionService;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/permission")
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionController {
    @Autowired
    PermissionService permissionService;

    @PostMapping
    ApiResponse<PermissionReponese> CreatePermission(@RequestBody PermissionRequest request) {
        return  ApiResponse.<PermissionReponese>builder()
                .result(permissionService.create(request))
                .status(200)
                .message("Permission created successfully")
                .build();
    }

    @GetMapping
    ApiResponse<List<PermissionReponese>> GetAllPermissions() {
        return ApiResponse.<List<PermissionReponese>>builder()
                .result(permissionService.findAll())
                .status(200)
                .build();
    }

    @DeleteMapping("/{permission}")
    ApiResponse<Void> DeletePermission(@PathVariable("permission") String permission) {
        permissionService.delete(permission);
        return ApiResponse.<Void>builder()
                .status(204)
                .build();
    }

}
