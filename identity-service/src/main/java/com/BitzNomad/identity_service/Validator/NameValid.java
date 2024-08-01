package com.BitzNomad.identity_service.Validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;

public class NameValid implements ConstraintValidator<NameConstrant, String> {
    private String name;
    @Override
    public void initialize(NameConstrant constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        name = constraintAnnotation.name();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Objects.isNull(value))
            return true;
        return name.equals(value);
    }
}
