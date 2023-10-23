package nttd.bootcamp.microservices.accountmanagement.infraestructure.adapter.config.properties;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "bank-nttdata.client.credit-card")
public class CreditCardProperties {
  private String baseUrl;
}
