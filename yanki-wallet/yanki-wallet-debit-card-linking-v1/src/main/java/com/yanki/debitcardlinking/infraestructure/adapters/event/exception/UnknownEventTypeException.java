package com.yanki.debitcardlinking.infraestructure.adapters.event.exception;

public class UnknownEventTypeException extends RuntimeException {
  public UnknownEventTypeException(String message) {
    super(message);
  }
}