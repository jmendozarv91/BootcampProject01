package nttd.bootcamp.microservices.creditcardservice.infraestructure.adapters.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "bank-nttdata.client.transaction")
public class TransactionProperties {
  private String baseUrl;
}
