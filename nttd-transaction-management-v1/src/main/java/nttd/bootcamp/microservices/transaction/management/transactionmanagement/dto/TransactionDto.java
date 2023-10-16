package nttd.bootcamp.microservices.transaction.management.transactionmanagement.dto;

import lombok.*;

import java.time.LocalDateTime;
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
