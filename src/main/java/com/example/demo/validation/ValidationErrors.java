package com.example.demo.validation;

import jakarta.validation.ConstraintViolationException;

import java.util.List;

public record ValidationErrors(List<ValidationError> errors) {
    static ValidationErrors fromViolationException(ConstraintViolationException exception) {
        return new ValidationErrors(
                exception.getConstraintViolations().stream().map(ValidationError::fromViolation).toList());
    }
}
