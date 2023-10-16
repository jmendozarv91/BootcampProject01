package nttd.bootcamp.microservices.creditservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
//@ComponentScan({
//		"nttd.bootcamp.microservices.creditservice.controller",
//		"nttd.bootcamp.microservices.creditservice.service",
//		"nttd.bootcamp.microservices.creditservice.repository"
//})
public class CreditServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditServiceApplication.class, args);
	}

}
