package com.BitzNomad.identity_service.DtoReponese;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RoleReponese {
    String name;
    String description;
    Set<PermissionReponese> permissions;
}
