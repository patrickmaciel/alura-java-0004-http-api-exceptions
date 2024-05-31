package org.example.exceptions;

public class TitleYearConversionException extends Throwable {
  private final String message;
  public TitleYearConversionException(String message) {
    this.message = message;
  }

  @Override
  public String getMessage() {
    return message;
  }
}
