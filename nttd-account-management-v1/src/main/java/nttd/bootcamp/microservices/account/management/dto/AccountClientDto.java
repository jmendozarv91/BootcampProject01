package nttd.bootcamp.microservices.account.management.dto;

import lombok.*;
import nttd.bootcamp.microservices.account.management.entity.enums.AccountType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountClientDto {
    private String id;
    private String accountNumber;
    private String accountType;
}
