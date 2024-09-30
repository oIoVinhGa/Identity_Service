package com.BitzNomad.identity_service.DtoReponese;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PermissionReponese {
    String name;
    String description;
}
