package nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.entity;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
@Builder
@Document(collection = "account_transactions")
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
  @Field(targetType = FieldType.IMPLICIT)
  private TransferDetails transferDetails;
  @Field(targetType = FieldType.IMPLICIT)
  private String ownerId;
}
