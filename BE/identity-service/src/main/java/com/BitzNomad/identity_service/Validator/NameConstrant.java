package com.BitzNomad.identity_service.Validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = {NameValid.class})
public @interface NameConstrant {
    String message() default "Invalid Name";

    String name();

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
