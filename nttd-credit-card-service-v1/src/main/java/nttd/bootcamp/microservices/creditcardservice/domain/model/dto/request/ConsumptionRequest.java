package nttd.bootcamp.microservices.creditcardservice.domain.model.dto.request;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ConsumptionRequest {
  private BigDecimal amount;
}
