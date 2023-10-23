package nttd.bootcamp.microservices.accountmanagement.infraestructure.adapter.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class AccountException extends RuntimeException  {
  private static final long serialVersionUID = 1L;

  public AccountException(HttpStatus errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  public AccountException(HttpStatus errorCode, String errorMessage , Throwable throwable) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.throwable = throwable;
  }

  private HttpStatus errorCode;
  private String errorMessage;
  private Throwable throwable;
}
