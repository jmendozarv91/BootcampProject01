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
//@TypeAlias("CreditTransaction")
//@Component
public class CreditTransaction
//    extends Transaction
{
  private String creditId;
}
