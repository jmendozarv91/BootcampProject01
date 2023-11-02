package nttd.bootcamp.microservices.credittransaction.infraestructure.adapters.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "bank-nttdata.client.credit")
public class CreditProperties {
  private String baseUrl;
}
