package nttd.bootcamp.microservices.creditcardservice.infraestructure.rest.advice;

import nttd.bootcamp.microservices.creditcardservice.infraestructure.adapters.exception.CreditCardException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class CreditControllerAdvice {

  @ExceptionHandler(CreditCardException.class)
  public ResponseEntity<String> handleEmptyInput(CreditCardException emptyInputException) {
    return new ResponseEntity<>(emptyInputException.getErrorMessage(),
        emptyInputException.getErrorCode());
  }
}
