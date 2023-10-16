package nttd.bootcamp.microservices.creditservice.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "credits")
public class Credit {
    @Id
    private String id;
    private String clientId;
    private Double amount ;
    private Double interestRate;
    private Double pendingAmount;
    private String creditType;
}
