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
public class AccountDto {
    private String id;
    private String accountNumber;
    private String accountType;
    private Double balance;
    private Integer maxMonthlyTransactions;
    private Double maintenanceFee;
    private String ownerId;
}
