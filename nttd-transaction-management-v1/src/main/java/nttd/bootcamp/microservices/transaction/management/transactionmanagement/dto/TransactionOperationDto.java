package nttd.bootcamp.microservices.transaction.management.transactionmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionOperationDto {
    private String accountId;
    private Double amount;
}
