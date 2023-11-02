package nttd.bootcamp.microservices.credittransaction.infraestructure.rest.advice;

import nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.exception.CreditCardTxException;
import nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CreditCardTxAdvice {

  @ExceptionHandler(CreditCardTxException.class)
  public ResponseEntity<ErrorResponse> handleAccountTransactionException(CreditCardTxException ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode().toString(), ex.getErrorMessage());
    return new ResponseEntity<>(errorResponse, ex.getErrorCode());
  }
}
