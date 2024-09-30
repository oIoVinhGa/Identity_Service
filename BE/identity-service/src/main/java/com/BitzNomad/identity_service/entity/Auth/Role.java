package com.BitzNomad.identity_service.entity.Auth;

import com.BitzNomad.identity_service.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Lazy;

import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Lazy
public class Role extends BaseEntity<String> {
    @Id
    String name;
    String description;


    @ManyToMany
    Set<Permission> permissions;
}
