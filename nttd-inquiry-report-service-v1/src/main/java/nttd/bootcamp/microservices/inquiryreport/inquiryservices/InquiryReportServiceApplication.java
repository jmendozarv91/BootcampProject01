package nttd.bootcamp.microservices.inquiryreport.inquiryservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableWebFlux
public class InquiryReportServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InquiryReportServiceApplication.class, args);
	}

}
