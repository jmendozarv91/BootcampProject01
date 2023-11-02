package nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
  private String codeError;
  private String descriptionError;
}
