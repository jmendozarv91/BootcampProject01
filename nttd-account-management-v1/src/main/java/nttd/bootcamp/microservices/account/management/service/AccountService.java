package nttd.bootcamp.microservices.account.management.service;

import nttd.bootcamp.microservices.account.management.dto.AccountBalance;
import nttd.bootcamp.microservices.account.management.dto.AccountDto;
import nttd.bootcamp.microservices.account.management.exceptions.AccountException;
import nttd.bootcamp.microservices.account.management.repository.AccountRepository;
import nttd.bootcamp.microservices.account.management.repository.client.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ReactiveMongoTemplate reactiveMongoTemplate;

    @Autowired
    private ClientRepository clientRepository;

    public Flux<AccountDto> findAllAccounts() {
        return accountRepository.findAll().switchIfEmpty(Mono.error(new AccountException("Error al consultar las consultas")));
    }

    public Mono<AccountDto> findAccount(String id) {
        return accountRepository.findById(id).switchIfEmpty(Mono.error(new AccountException("Cuenta no encontrada")));
    }

    public Mono<AccountDto> saveAccountClientPersonal(AccountDto accountDto) {
        return clientRepository.findClientById(accountDto.getOwnerId())
                .flatMap(client -> {
                    if (client.getClientType().equals("01")){
                        return accountRepository.findByOwnerId(client.getId())
                                .collectList()
                                .flatMap(existingAccounts -> {
                                    System.out.println(existingAccounts);
                                    long currentAccountsCount = existingAccounts.stream()
                                            .filter(acc -> acc.getAccountType().equals("001") ||
                                                    acc.getAccountType().equals("002") ||
                                                    acc.getAccountType().equals("003"))
                                            .count();
                                    if (currentAccountsCount < 1) {
                                        return accountRepository.save(accountDto);
                                    } else {
                                        return Mono.error(new AccountException("Client can't have more than 3 specified accounts"));
                                    }
                                });
                    }
                    return Mono.error(new AccountException("the customer cannot register the account" ));
                });
    }

    public Mono<AccountDto> saveAccountClientBusiness(AccountDto accountDto) {
        return clientRepository.findClientById(accountDto.getOwnerId())
                .flatMap(client -> {
                        if (client.getClientType().equals("02")
                                && (accountDto.getAccountType().equals("001")) || accountDto.getAccountType().equals("003")) {
                            return Mono.error(new AccountException("The business client cannot have savings or fixed-term accounts"));
                        }
                    return accountRepository.save(accountDto);
                });
    }

    public Mono<AccountDto> updateBalanceAccount(String id,AccountBalance accountBalance){
        return accountRepository.findById(id)
                .flatMap(accountDto -> {
                    accountDto.setBalance(accountBalance.getBalance());
                    return accountRepository.save(accountDto);
                });
    }
}
