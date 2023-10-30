package nttd.bootcamp.microservices.transaction.management.transactionmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreditBalanceDto {
    private Double pendingAmount;
    private Double amount;
}
