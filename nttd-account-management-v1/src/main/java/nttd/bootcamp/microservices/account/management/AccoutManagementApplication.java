package nttd.bootcamp.microservices.account.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
@ComponentScan({
		"nttd.bootcamp.microservices.account.management.controller",
		"nttd.bootcamp.microservices.account.management.service",
		"nttd.bootcamp.microservices.account.management.repository",
		"nttd.bootcamp.microservices.account.management.config"
})
public class AccoutManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccoutManagementApplication.class, args);
	}

}
