package nttd.bootcamp.microservices.creditservice.dto;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ClientDto {
    private String id;
    private String clientType;
}
