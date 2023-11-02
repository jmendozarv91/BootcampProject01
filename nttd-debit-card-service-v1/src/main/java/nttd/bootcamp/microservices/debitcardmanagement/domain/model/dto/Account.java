package nttd.bootcamp.microservices.debitcardmanagement.domain.model.dto;

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
public class Account {
  private String id;
  private String accountNumber;
  private String accountType;
  private Double balance;
}
