package nttd.bootcamp.microservices.credittransaction.domain.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Credit {
  private String id;
  private String clientId;
  private Double amount ;
  private Double interestRate;
  private Double pendingAmount;
  private String creditType;
}
