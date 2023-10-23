package nttd.bootcamp.microservices.transaction.management.transactionmanagement.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransferDto {

  private String id;
  private String type;
  private Double amount;
  private String description;
  private LocalDateTime transactionDate;
  private String fromAccountId;
  private String toAccountId;
}
