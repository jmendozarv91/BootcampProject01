package nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@NoArgsConstructor

public class AccountTransactionException extends RuntimeException  {
  private static final long serialVersionUID = 1L;

  public AccountTransactionException(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public AccountTransactionException(HttpStatus errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  public AccountTransactionException(HttpStatus errorCode, String errorMessage , Throwable throwable) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.throwable = throwable;
  }

  private HttpStatus errorCode;
  private String errorMessage;
  private Throwable throwable;
}
