package nttd.bootcamp.microservices.credittransaction.infraestructure.rest.advice;

import nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.exception.CreditTxException;
import nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CreditTxAdvice {

  @ExceptionHandler(CreditTxException.class)
  public ResponseEntity<ErrorResponse> handleAccountTransactionException(CreditTxException ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode().toString(), ex.getErrorMessage());
    return new ResponseEntity<>(errorResponse, ex.getErrorCode());
  }
}
