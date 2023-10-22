package nttd.bootcamp.microservices.creditcardservice.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Customer {
  private String id;
  private String name;
  private String lastname;
  private String phone;
  private String identification;
  private String email;
  private String clientType;
}
