package nttd.bootcamp.microservices.debitcardmanagement.domain.model;

import java.util.List;
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
public class DebitCard {
  private String id;
  private String cardNumber;
  private String expirationDate;
  private String securityCode;
  private Double balance;
  private String primaryAccountId;
  private List<String> associatedAccountIds;
}
