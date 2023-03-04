package com.example.demo.services.letter.exceptions;

public class LetterFailedException extends Exception {
  public LetterFailedException(String cause) {
    super("Failed to send letter: %s".formatted(cause));
  }
}
