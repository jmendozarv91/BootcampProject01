package nttd.bootcamp.microservices.account.management.dto;


import lombok.*;
import nttd.bootcamp.microservices.account.management.entity.enums.AccountType;

import java.util.Date;
import java.util.List;

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
