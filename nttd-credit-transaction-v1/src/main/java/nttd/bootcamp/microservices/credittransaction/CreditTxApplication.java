package nttd.bootcamp.microservices.credittransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class CreditTxApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditTxApplication.class, args);
	}

}
