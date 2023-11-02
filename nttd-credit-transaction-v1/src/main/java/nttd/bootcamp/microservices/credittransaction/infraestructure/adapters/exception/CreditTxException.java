package nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditTxException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private HttpStatus errorCode;
  private String errorMessage;

}
