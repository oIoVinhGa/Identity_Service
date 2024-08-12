package com.BitzNomad.identity_service.entity.Auth;

import com.BitzNomad.identity_service.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Permission extends BaseEntity<String> {
    @Id
    String name;
    String description;
}
