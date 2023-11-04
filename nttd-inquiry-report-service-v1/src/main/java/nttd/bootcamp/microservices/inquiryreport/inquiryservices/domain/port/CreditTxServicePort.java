package nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.port;

import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.CreditTransaction;
import reactor.core.publisher.Flux;

public interface CreditTxServicePort {
  Flux<CreditTransaction> getCreditCardTransactions(String clientId);
}
