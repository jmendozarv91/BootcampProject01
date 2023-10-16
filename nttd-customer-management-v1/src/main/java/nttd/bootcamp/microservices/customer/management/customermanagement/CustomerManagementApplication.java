package nttd.bootcamp.microservices.customer.management.customermanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
@ComponentScan({
        "nttd.bootcamp.microservices.customer.management.customermanagement.controller",
        "nttd.bootcamp.microservices.customer.management.customermanagement.service",
        "nttd.bootcamp.microservices.customer.management.customermanagement.repository",
})
public class CustomerManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerManagementApplication.class, args);
    }

}
