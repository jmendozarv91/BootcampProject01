package nttd.bootcamp.microservices.transaction.management.transactionmanagement.entity;

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
@Document(collection = "transactions")
public  class TransactionEntity {
  @Id
  private String id;
  private String type;
  private Double amount;
  @Field(targetType = FieldType.IMPLICIT)
  private String description;
  private LocalDateTime transactionDate;
  @Field(targetType = FieldType.IMPLICIT)
  private String fromAccountId;
  @Field(targetType = FieldType.IMPLICIT)
  private String toAccountId;
}
