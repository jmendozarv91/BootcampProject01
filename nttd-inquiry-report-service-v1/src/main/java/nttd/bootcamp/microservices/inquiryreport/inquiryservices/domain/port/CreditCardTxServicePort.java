package nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.port;

import nttd.bootcamp.microservices.inquiryreport.inquiryservices.domain.model.dto.CreditCardTransaction;
import reactor.core.publisher.Flux;

public interface CreditCardTxServicePort {
  Flux<CreditCardTransaction> getCreditCardTransactions(String creditCardId);

}
