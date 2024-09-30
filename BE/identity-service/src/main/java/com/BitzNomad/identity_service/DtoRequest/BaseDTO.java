package com.BitzNomad.identity_service.DtoRequest;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class BaseDTO<T> {
    T createdBy;
    LocalDateTime createdDate;
    T lastModifiedBy;
    LocalDateTime lastModifiedDate;
    boolean deleted;
}
