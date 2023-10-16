package nttd.bootcamp.microservices.transaction.management.transactionmanagement.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class CreditPaymentDto {
    private Double amount;
}