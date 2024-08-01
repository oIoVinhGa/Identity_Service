package com.BitzNomad.identity_service.repository;

import com.BitzNomad.identity_service.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissionRepository extends JpaRepository<Permission, String> {
}
