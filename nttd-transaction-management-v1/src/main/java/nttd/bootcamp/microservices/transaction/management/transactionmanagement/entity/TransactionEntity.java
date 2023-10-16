package nttd.bootcamp.microservices.transaction.management.transactionmanagement.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "transactions")
public class TransactionEntity {
    @Id
    private String id;
    private String transactionId;
    private String accountId;
    private String creditId;
    private String type;
    private Double amount;
    private LocalDateTime transactionDate;
}
