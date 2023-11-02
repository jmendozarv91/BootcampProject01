package nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class TransferDetails {
  @Field(targetType = FieldType.IMPLICIT)
  private String fromAccountId;
  @Field(targetType = FieldType.IMPLICIT)
  private String toAccountId;
}
