package nttd.bootcamp.microservices.credittransaction.domain.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class Customer {

  private String id;
  private String name;
  private String lastname;
  private String phone;
  private String identification;
  private String email;
  private String clientType;
  private String profileType;
}

