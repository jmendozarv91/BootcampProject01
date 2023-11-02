package nttd.bootcamp.microservices.credittransaction.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
@Builder
public class CreditBalance {
  private Double amount;
  private Double pendingAmount;
}
