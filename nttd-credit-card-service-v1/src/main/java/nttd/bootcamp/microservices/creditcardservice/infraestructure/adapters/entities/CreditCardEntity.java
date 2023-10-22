package nttd.bootcamp.microservices.creditcardservice.infraestructure.adapters.entities;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "creditcards")
public class CreditCardEntity {

  @Id
  private String id;
  private String clientId;
  private String cardId;
  private BigDecimal creditLimit;
  private BigDecimal availableBalance;
  private boolean isActive;
  private boolean isBlocked;
  private LocalDateTime dueDate;
}
