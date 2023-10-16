package nttd.bootcamp.microservices.customer.management.customermanagement.entity;

import lombok.*;
import nttd.bootcamp.microservices.customer.management.customermanagement.entity.enums.ClientType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Document(collection = "clients")
public class Client {
    @Id
    private String id;
    private String name;
    private String lastname;
    private String phone;
    private String identification;
    private String email;
    private String clientType;
    private List<String> accounts;
    private List<String> credits;
    private List<String> creditCards;
}
