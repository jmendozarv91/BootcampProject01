package nttd.bootcamp.microservices.creditservice.service;

import nttd.bootcamp.microservices.creditservice.dto.CreditDto;
import nttd.bootcamp.microservices.creditservice.repository.CreditRepository;
import nttd.bootcamp.microservices.creditservice.repository.client.ClientRepository;
import nttd.bootcamp.microservices.creditservice.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@Service
public class CreditService {
    @Autowired
    private CreditRepository creditRepository;

    @Autowired
    private ClientRepository clientRepository;

    public Mono<CreditDto> saveCredit(CreditDto credit) {
        return clientRepository.getClient(credit.getClientId())
                .flatMap(clientDto -> {
                    if (clientDto.getClientType().equals("01")) {
                        return creditRepository.findByClientId(credit.getClientId())
                                .collectList()
                                .flatMap(credits -> {
                                    if (credits.size() == 0) {
                                        return creditRepository.save(credit);
                                    } else {
                                        return Mono.error(new ResponseStatusException(
                                                HttpStatus.BAD_REQUEST, "Client can't have more than 1 credit"));
                                    }
                                });
                    } else if (clientDto.getClientType().equals("02")) {
                        return creditRepository.save(credit);
                    } else {
                        return Mono.error(new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "Unknown client type"));
                    }
                }).doOnError(System.out::println);
    }

    public Mono<CreditDto> findCredit(String creditId) {
        return creditRepository.findById(creditId)
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "Credit not found")));
    }


}
