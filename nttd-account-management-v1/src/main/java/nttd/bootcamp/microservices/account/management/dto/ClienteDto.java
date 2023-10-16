package nttd.bootcamp.microservices.account.management.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClienteDto {
    private String id;
    private String clientType;
}
