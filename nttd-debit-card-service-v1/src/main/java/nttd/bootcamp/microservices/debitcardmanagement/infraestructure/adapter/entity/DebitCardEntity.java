package nttd.bootcamp.microservices.debitcardmanagement.infraestructure.adapter.entity;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

// Entidad de MongoDB
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "debit_cards")
public class DebitCardEntity {

  @Id
  private String id;
  private String cardNumber;
  private String expirationDate;
  private String securityCode;
  private Double balance;
  private String primaryAccountId;
  private List<String> associatedAccountIds;
  private String walletId;

}