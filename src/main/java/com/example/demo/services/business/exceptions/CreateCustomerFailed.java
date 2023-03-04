package com.example.demo.services.business.exceptions;

public class CreateCustomerFailed extends Exception {

  public CreateCustomerFailed(String cause) {
    super("Create customer failed: %s".formatted(cause));
  }
}
