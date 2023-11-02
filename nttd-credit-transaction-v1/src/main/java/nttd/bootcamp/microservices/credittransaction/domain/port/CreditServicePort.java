package nttd.bootcamp.microservices.credittransaction.domain.port;

import nttd.bootcamp.microservices.credittransaction.domain.model.dto.Credit;
import nttd.bootcamp.microservices.credittransaction.domain.model.dto.CreditBalance;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public interface CreditServicePort {

  Mono<Credit> findCredit(String creditId);

  Mono<Credit> refreshBalanceCredit(String creditId, CreditBalance creditBalance);


}
