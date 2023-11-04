package nttd.bootcamp.microservices.accounttransaction.infraestructure.rest.advice;

import nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.exception.AccountTransactionException;
import nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.exception.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AccountControllerAdvice {

  @ExceptionHandler(AccountTransactionException.class)
  public ResponseEntity<ErrorResponse> handleAccountTransactionException(
      AccountTransactionException ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode().toString(),
        ex.getErrorMessage());
    return new ResponseEntity<>(errorResponse, ex.getErrorCode());
  }

}
