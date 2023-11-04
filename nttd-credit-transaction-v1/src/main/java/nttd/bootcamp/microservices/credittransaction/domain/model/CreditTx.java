package nttd.bootcamp.microservices.credittransaction.domain.model;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CreditTx {
  private String id;
  private String type;
  private Double amount;
  @Field(targetType = FieldType.DATE_TIME)
  private LocalDateTime transactionDate;
  private String creditId;
  private String clientId;
}
