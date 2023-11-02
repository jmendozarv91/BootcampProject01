package nttd.bootcamp.microservices.accounttransaction.infraestructure.rest.advice;

import javax.security.auth.login.AccountNotFoundException;
import nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.exception.AccountTransactionException;
import nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.exception.ErrorResponse;
import nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.exception.InsufficientFundsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AccountControllerAdvice {

  @ExceptionHandler(AccountTransactionException.class)
  public ResponseEntity<ErrorResponse> handleAccountTransactionException(AccountTransactionException ex) {
    ErrorResponse errorResponse = new ErrorResponse(ex.getErrorCode().toString(), ex.getErrorMessage());
    return new ResponseEntity<>(errorResponse, ex.getErrorCode());
  }

}
