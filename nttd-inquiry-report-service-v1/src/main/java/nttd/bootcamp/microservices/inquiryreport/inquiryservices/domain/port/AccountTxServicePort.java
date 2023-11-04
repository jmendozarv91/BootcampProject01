package nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.port;

import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.AccountTransaction;
import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AccountTxServicePort {

  Flux<AccountTransaction> getAccountTransactions(String clientId);
}
