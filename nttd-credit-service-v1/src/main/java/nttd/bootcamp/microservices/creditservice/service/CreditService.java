package nttd.bootcamp.microservices.creditservice.service;

import nttd.bootcamp.microservices.creditservice.dto.CreditBalanceDto;
import nttd.bootcamp.microservices.creditservice.dto.CreditDto;
import nttd.bootcamp.microservices.creditservice.entity.ClientType;
import nttd.bootcamp.microservices.creditservice.entity.Credit;
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

    public Mono<Credit> saveCredit(CreditDto credit) {
        return clientRepository.getClient(credit.getClientId())
                .flatMap(clientDto -> {
                    if (clientDto.getClientType().equals(ClientType.PERSONAL.getDescription())) {
                        return creditRepository.findByClientId(credit.getClientId())
                                .collectList()
                                .flatMap(credits -> {
                                    if (credits.isEmpty()) {
                                        return creditRepository.save(AppUtils.entityToEntity(credit));
                                    } else {
                                        return Mono.error(new ResponseStatusException(
                                                HttpStatus.BAD_REQUEST, "Client can't have more than 1 credit"));
                                    }
                                });
                    } else if (clientDto.getClientType().equals(ClientType.BUSINESS.getDescription())) {
                        return creditRepository.save(AppUtils.entityToEntity(credit));
                    } else {
                        return Mono.error(new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "Unknown client type"));
                    }
                }).doOnError(System.out::println);
    }

    public Mono<Credit> updateBalancePendingCredit(String creditId, CreditBalanceDto creditBalanceDto){
        System.out.println("id =" + creditId);
        return creditRepository.findById(creditId)
                .flatMap(creditDto -> {
                    System.out.println(creditDto.toString());
                    System.out.println(creditBalanceDto.getPendingAmount());
                    creditDto.setPendingAmount(creditBalanceDto.getPendingAmount());
                    creditDto.setAmount(creditBalanceDto.getAmount());
                    return creditRepository.save(creditDto);
                });
    }

    public Mono<Credit> findCredit(String creditId) {
        System.out.println("id =" + creditId);
        return creditRepository.findById(creditId)
                .switchIfEmpty(Mono.error(
                        new ResponseStatusException(
                                HttpStatus.BAD_REQUEST, "Credit not found")));
    }


}
