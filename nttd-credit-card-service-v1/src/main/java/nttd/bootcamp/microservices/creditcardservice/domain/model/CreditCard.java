package nttd.bootcamp.microservices.creditcardservice.domain.model;

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
@ToString(exclude = {"id","isActive","isBlocked","dueDate"})
@Builder
public class CreditCard {
  private String id;
  private String cardId;
  private String clientId;
  private BigDecimal creditLimit;
  private BigDecimal availableBalance;
  private boolean isActive;
  private boolean isBlocked;
  private LocalDateTime dueDate;
}
