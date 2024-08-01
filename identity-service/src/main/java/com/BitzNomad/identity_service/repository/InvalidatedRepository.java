package com.BitzNomad.identity_service.repository;

import com.BitzNomad.identity_service.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvalidatedRepository extends JpaRepository<InvalidatedToken,String> {
}
