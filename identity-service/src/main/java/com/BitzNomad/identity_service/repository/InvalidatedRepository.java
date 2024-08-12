package com.BitzNomad.identity_service.repository;

import com.BitzNomad.identity_service.entity.InvalidatedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvalidatedRepository extends JpaRepository<InvalidatedToken,String> {
}
