package com.yanki.walletmobiletransaction.infraestructure.adapters.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@NoArgsConstructor
public class TransactionException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public TransactionException(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public TransactionException(HttpStatus errorCode , String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  public TransactionException(HttpStatus errorCode , Throwable throwable , String errorMessage) {
    this.throwable = throwable;
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }


  private Throwable throwable;
  private String errorMessage;
  private HttpStatus errorCode;

}

