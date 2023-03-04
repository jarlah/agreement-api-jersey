package com.example.demo.util.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import no.bekk.bekkopen.person.FodselsnummerValidator;

public class ValidCustomerPidCheck implements ConstraintValidator<ValidCustomerPid, String> {

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return FodselsnummerValidator.isValid(value);
  }
}
