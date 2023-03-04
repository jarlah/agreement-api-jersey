package com.example.demo.util.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({FIELD})
@Documented
@Constraint(validatedBy = {ValidCustomerPidCheck.class})
public @interface ValidCustomerPid {

  String message() default "Customer pid is not valid";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
