package com.BitzNomad.identity_service.DtoRequest;

import com.BitzNomad.identity_service.Validator.DobConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class UserUpdateRequest {
    private String id;

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private String firstName;
    private String lastName;


    @DobConstraint(min = 2,message = "INVALID_DOB")
    @NotNull
    private LocalDate dob;

    List<String> roles;

}
