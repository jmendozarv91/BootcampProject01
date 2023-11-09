package com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.events.exception;

public class UserTargetNotFoundException extends RuntimeException {

  public UserTargetNotFoundException() {
    super("User Target not found!");
  }

  public UserTargetNotFoundException(String message) {
    super(message);
  }

}
