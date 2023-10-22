package nttd.bootcamp.microservices.creditcardservice.domain.model.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreditCardDto {
  private String cardId;
  private String clientId;
  private BigDecimal creditLimit;
  private BigDecimal availableBalance;

}
