package nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.entity;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "creditcard_transactions")
public class TransactionEntity {
  @Id
  private String id;
  private String type;
  private Double amount;
  private String accountId;
  @Field(targetType = FieldType.IMPLICIT)
  private String description;
  @Field(targetType = FieldType.DATE_TIME)
  private LocalDateTime transactionDate;
  private String clientId;
  private String creditCardId;

}
