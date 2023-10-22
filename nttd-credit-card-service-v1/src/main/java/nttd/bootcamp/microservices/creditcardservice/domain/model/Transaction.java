package nttd.bootcamp.microservices.creditcardservice.domain.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Transaction {
  private String id;
  private String creditCardId;
  private String clientId;
  private String type;
  private Double amount;
  private LocalDateTime transactionDate;
  private String description;
}
