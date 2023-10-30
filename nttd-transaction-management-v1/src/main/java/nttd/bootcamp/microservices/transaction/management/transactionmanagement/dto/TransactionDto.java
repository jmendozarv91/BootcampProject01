package nttd.bootcamp.microservices.transaction.management.transactionmanagement.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TransactionDto {
    private String id;
    private String transactionId;
    private String accountId;
    private String creditId;
    private String type;
    private Double amount;
    private LocalDateTime transactionDate;
}
