package nttd.bootcamp.microservices.accountmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class AccountManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountManagementApplication.class, args);
	}

}
