package nttd.bootcamp.microservices.account.management.repository;

import nttd.bootcamp.microservices.account.management.dto.AccountDto;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface AccountRepository extends ReactiveMongoRepository<AccountDto, String> {

    Flux<AccountDto> findByOwnerId(String ownerId);
}
