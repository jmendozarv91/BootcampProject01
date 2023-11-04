package nttd.bootcamp.microservices.accounttransaction.domain.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.entity.TransferDetails;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Transaction {
  private String id;
  private String type;
  private Double amount;
  private String accountId;
  private String description;
  private LocalDateTime transactionDate;
  private TransferDetails transferDetails;
  private String ownerId;
}
