package com.example.demo.services.business.exceptions;

public class UpdateAgreementStatusFailedException extends Exception {
  public UpdateAgreementStatusFailedException(String cause) {
    super("Update agreement status failed: %s".formatted(cause));
  }
}
