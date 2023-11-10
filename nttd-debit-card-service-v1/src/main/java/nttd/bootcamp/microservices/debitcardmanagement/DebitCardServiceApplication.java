package nttd.bootcamp.microservices.debitcardmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class DebitCardServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DebitCardServiceApplication.class, args);
	}

}
