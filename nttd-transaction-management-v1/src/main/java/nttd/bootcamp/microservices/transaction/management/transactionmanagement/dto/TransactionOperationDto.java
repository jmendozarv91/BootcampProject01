package nttd.bootcamp.microservices.transaction.management.transactionmanagement.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionOperationDto {
    private String accountId;
    private Double amount;
}
