package nttd.bootcamp.microservices.debitcardmanagement.infraestructure.adapter.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
public class DebitCardException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public DebitCardException(HttpStatus errorCode, String errorMessage) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
  }

  public DebitCardException(HttpStatus errorCode, String errorMessage, Throwable throwable) {
    this.errorCode = errorCode;
    this.errorMessage = errorMessage;
    this.throwable = throwable;
  }

  private HttpStatus errorCode;
  private String errorMessage;
  private Throwable throwable;


}
