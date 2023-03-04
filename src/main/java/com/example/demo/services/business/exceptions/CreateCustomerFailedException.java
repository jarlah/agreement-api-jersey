package com.example.demo.services.business.exceptions;

public class CreateCustomerFailedException extends Exception {

  public CreateCustomerFailedException(String cause) {
    super("Create customer failed: %s".formatted(cause));
  }
}
