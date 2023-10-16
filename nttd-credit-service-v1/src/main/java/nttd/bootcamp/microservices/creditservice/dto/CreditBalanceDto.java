package nttd.bootcamp.microservices.creditservice.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Setter
public class CreditBalanceDto {
    private Double amount;
    private Double pendingAmount;
}
