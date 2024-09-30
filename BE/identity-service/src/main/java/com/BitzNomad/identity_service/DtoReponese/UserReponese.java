package com.BitzNomad.identity_service.DtoReponese;

import lombok.Data;

import java.time.LocalDate;
import java.util.Set;

@Data
public class UserReponese {

    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private LocalDate dob;
    Set<RoleReponese> roles;
}
