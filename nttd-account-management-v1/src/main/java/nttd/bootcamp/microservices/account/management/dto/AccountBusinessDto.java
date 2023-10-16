package nttd.bootcamp.microservices.account.management.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountBusinessDto {

    private String id;
    private String accountNumber;
    private String accountType;
    private Double balance;
    private Integer maxMonthlyTransactions;
    private Double maintenanceFee;
    private String ownerId;
}
