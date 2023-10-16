package nttd.bootcamp.microservices.transaction.management.transactionmanagement.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreditBalanceDto {
    private Double pendingAmount;
    private Double amount;
}
