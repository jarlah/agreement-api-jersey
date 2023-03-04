package com.example.demo.validation;

import jakarta.validation.ConstraintViolation;

public record ValidationError(String field, String message) {
    static ValidationError fromViolation(ConstraintViolation<?> violation) {
        return new ValidationError(violation.getPropertyPath().toString(), violation.getMessage());
    }
}
