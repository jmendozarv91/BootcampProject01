package nttd.bootcamp.microservices.transaction.management.transactionmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.stereotype.Component;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TypeAlias("CreditCardTransaction")
@Component
public class CreditCardTransaction
    extends TransactionEntity
{
  private String clientId;
  private String creditCardId;
}
