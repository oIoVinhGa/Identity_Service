package com.BitzNomad.identity_service.DtoRequest;

import com.BitzNomad.identity_service.Validator.DobConstraint;
import com.BitzNomad.identity_service.Validator.NameConstrant;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UserCreateRequest {

    @NotBlank(message = "Username is required")
    @Size(min = 6, max = 50, message = "USER_INVALID")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "PASSWORD_INVALID")
    private String password;

    private String firstName;
    private String lastName;

    @DobConstraint(min = 2,message = "INVALID_DOB")
    @NotNull
    private LocalDate dob;
}
