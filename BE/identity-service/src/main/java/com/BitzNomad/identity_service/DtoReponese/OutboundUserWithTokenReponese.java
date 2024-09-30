package com.BitzNomad.identity_service.DtoReponese;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class OutboundUserWithTokenReponese {
    String id;
    String email;
    String familyName;
    String givenName;
    String name;
    String picture;
    boolean verified_email;
    String locale;
}
