package com.yanki.walletmobiletransaction.infraestructure.adapters.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private Throwable throwable;
  private String errorMessage;

  private HttpStatus errorCode;

}

