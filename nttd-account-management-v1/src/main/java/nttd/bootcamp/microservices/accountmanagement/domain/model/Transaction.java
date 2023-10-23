package nttd.bootcamp.microservices.accountmanagement.domain.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class Transaction {

  private String id;
  private String type;
  private Double amount;
  private LocalDateTime transactionDate;
  private String fromAccountId;
  private String toAccountId;
}
