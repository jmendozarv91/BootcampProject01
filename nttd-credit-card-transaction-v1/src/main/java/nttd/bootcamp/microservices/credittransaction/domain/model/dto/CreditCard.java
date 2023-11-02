package nttd.bootcamp.microservices.credittransaction.domain.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CreditCard {
  private String id;
  private String cardId;
  private String clientId;
  private BigDecimal creditLimit;
  private BigDecimal availableBalance;
}
