package com.example.demo.services.business.exceptions;

public class UpdateAgreementStatusFailed extends Exception {
  public UpdateAgreementStatusFailed(String cause) {
    super("Update agreement status failed: %s".formatted(cause));
  }
}
