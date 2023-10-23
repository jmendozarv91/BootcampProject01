package nttd.bootcamp.microservices.accountmanagement.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Account {
  private String id;
  private String accountNumber;
  private String accountType;
  private Double balance;
  private Integer maxMonthlyTransactions;
  private Double maintenanceFee;
  private String ownerId;
  private Double minimumOpeningAmount;
  private String bankId;
}
