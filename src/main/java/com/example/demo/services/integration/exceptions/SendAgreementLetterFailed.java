package com.example.demo.services.integration.exceptions;

public class SendAgreementLetterFailed extends Exception {
  public SendAgreementLetterFailed(String cause) {
    super("Send agreement letter failed: %s".formatted(cause));
  }
}
