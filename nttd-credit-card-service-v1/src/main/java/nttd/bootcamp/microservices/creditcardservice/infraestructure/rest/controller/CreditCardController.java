package nttd.bootcamp.microservices.creditcardservice.infraestructure.rest.controller;

import lombok.AllArgsConstructor;
import nttd.bootcamp.microservices.creditcardservice.application.services.CreditCardManagementService;
import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.CreditCardDto;
import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.request.ConsumptionRequest;
import nttd.bootcamp.microservices.creditcardservice.domain.model.dto.request.CreditCardRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequestMapping("/api/v1/credit-card")
@RestController
@AllArgsConstructor
public class CreditCardController {

  private final CreditCardManagementService creditCardService;


  @PostMapping("/save")
  public Mono<CreditCardDto> saveTransaction(@RequestBody CreditCardRequest creditCardRequest) {
    return creditCardService.createNew(creditCardRequest);
  }

  @PostMapping("/consumption")
  public Mono<Void> processConsumption(@RequestParam String clientId,
      @RequestParam String creditCardId, @RequestBody ConsumptionRequest request) {
    return creditCardService.executeConsumption(clientId,creditCardId,request);
  }

}
