package com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.events.exception;

public class InvalidAmountException extends RuntimeException {

  public InvalidAmountException() {
    super("Invalid amount!");
  }

  public InvalidAmountException(String message) {
    super(message);
  }

}