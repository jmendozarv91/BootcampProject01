package nttd.bootcamp.microservices.account.management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import nttd.bootcamp.microservices.account.management.entity.enums.AccountType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "accountsDto")
public class AccountEntity {
    @Id
    private String id;
    private String accountNumber;
    private String accountType;
    private Double balance;
    private Integer maxMonthlyTransactions;
    private Double maintenanceFee;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createDate;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date modifyDate;
    private String ownerId;
    private List<String>  authorizedSigners;
}
