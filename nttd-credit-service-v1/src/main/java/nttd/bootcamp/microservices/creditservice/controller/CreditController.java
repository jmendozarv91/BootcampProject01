package nttd.bootcamp.microservices.creditservice.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nttd.bootcamp.microservices.creditservice.dto.CreditDto;
import nttd.bootcamp.microservices.creditservice.service.CreditService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping(value = "/api/v1/credit-service")
@RestController
@AllArgsConstructor
@Slf4j
public class CreditController {

    private final CreditService creditService;

    @GetMapping("/credits/{creditId}")
    public Mono<CreditDto> findCredit(@PathVariable String creditId){
        return creditService.findCredit(creditId);
    }

    @PostMapping("/credits")
    public Mono<CreditDto> createCredit(@RequestBody CreditDto creditDto){
        return creditService.saveCredit(creditDto);
    }


}
