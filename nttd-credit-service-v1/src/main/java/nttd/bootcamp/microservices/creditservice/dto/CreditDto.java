package nttd.bootcamp.microservices.creditservice.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreditDto {
    private String id;
    private String clientId;
    private Double amount ;
    private Double interestRate;
    private Double pendingAmount;
    private String creditType;
}
