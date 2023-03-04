package com.example.demo.services.business.exceptions;

public class CreateAgreementFailedException extends Exception {
  public CreateAgreementFailedException(String cause) {
    super("Create agreement failed: %s".formatted(cause));
  }
}
