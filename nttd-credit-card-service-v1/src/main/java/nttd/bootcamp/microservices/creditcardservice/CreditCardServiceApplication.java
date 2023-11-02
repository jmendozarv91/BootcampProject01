package nttd.bootcamp.microservices.creditcardservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class CreditCardServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(CreditCardServiceApplication.class, args);
  }

}
