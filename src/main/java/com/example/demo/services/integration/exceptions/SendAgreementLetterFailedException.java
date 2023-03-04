package com.example.demo.services.integration.exceptions;

public class SendAgreementLetterFailedException extends Exception {
  public SendAgreementLetterFailedException(String cause) {
    super("Send agreement letter failed: %s".formatted(cause));
  }
}
