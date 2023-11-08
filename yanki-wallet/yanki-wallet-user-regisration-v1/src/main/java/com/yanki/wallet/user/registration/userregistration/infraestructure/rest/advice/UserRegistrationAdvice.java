package com.yanki.wallet.user.registration.userregistration.infraestructure.rest.advice;

import com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.exception.UserRegistrationException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Log4j2
public class UserRegistrationAdvice {

  @ExceptionHandler(UserRegistrationException.class)
  public ResponseEntity<String> handleUserRegistrationException(UserRegistrationException e) {
    log.error(e);
    return new ResponseEntity<>(e.getErrorMessage(), e.getErrorCode());
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<String> handleGeneralException(Exception e) {
    log.error(e);
    return new ResponseEntity<>("An unexpected error has occurred: " + e.getMessage(),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
