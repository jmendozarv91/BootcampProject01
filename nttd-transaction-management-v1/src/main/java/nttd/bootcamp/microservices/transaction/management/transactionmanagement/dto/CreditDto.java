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
public class CreditDto {
    private String id;
    private String clientId;
    private Double amount ;
    private Double interestRate;
    private Double pendingAmount;
    private String creditType;
}
