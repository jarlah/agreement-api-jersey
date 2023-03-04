package com.example.demo.services.letter.exceptions;

public class LetterFailedExceptionException extends Exception {
  public LetterFailedExceptionException(String cause) {
    super("Failed to send letter: %s".formatted(cause));
  }
}
