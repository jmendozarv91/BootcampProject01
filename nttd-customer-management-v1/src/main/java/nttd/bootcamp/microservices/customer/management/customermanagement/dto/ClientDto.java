package nttd.bootcamp.microservices.customer.management.customermanagement.dto;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientDto {
    private String id;
    private String name;
    private String lastname;
    private String phone;
    private String identification;
    private String email;
    private String clientType;
    private String profileType;
}
