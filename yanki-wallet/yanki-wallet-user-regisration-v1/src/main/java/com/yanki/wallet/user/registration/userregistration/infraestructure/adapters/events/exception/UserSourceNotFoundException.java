package com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.events.exception;

public class UserSourceNotFoundException extends RuntimeException {

  public UserSourceNotFoundException() {
    super("User Source not found!");
  }

  public UserSourceNotFoundException(String message) {
    super(message);
  }


}

