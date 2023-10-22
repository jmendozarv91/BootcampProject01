package nttd.bootcamp.microservices.creditcardservice.domain.model.dto.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreditCardRequest {
  private String cardId;
  private String clientId;
  private BigDecimal creditLimit;
  private BigDecimal availableBalance;
}
