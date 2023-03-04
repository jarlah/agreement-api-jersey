package com.example.demo.services.business.exceptions;

public class CreateAgreementFailed extends Exception {
  public CreateAgreementFailed(String cause) {
    super("Create agreement failed: %s".formatted(cause));
  }
}
