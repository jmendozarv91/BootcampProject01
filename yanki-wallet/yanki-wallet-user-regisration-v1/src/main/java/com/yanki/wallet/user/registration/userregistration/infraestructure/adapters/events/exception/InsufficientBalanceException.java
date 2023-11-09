package com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.events.exception;

public class InsufficientBalanceException extends RuntimeException {

  public InsufficientBalanceException() {
    super("Insufficient balance!");
  }

  public InsufficientBalanceException(String message) {
    super(message);
  }

}