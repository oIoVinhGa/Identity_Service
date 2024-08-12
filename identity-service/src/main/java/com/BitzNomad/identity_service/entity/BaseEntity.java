package com.BitzNomad.identity_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity<U> {

    @CreatedBy
    @Column(updatable = false , insertable = true)
    private U createdBy;

    @CreatedDate
    @Column(updatable = false , insertable = true)
    private LocalDateTime createdDate;

    @LastModifiedBy
    @Column(insertable = false , updatable = true)
    private U lastModifiedBy;

    @LastModifiedDate
    @Column(insertable = false, updatable = true)
    private LocalDateTime lastModifiedDate;

    @Column(nullable = false)
    private boolean isDeleted = false;

}
