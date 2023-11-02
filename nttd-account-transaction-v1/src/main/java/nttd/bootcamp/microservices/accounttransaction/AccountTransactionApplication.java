package nttd.bootcamp.microservices.accounttransaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class AccountTransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountTransactionApplication.class, args);
	}

}
