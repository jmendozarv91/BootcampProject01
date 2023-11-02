package nttd.bootcamp.microservices.accounttransaction.application.usecases;

import nttd.bootcamp.microservices.accounttransaction.domain.model.dto.TransactionRequest;
import nttd.bootcamp.microservices.accounttransaction.domain.model.dto.TransactionResponse;
import nttd.bootcamp.microservices.accounttransaction.domain.model.dto.TransferRequest;
import nttd.bootcamp.microservices.accounttransaction.domain.model.dto.TransferResponse;
import reactor.core.publisher.Mono;

public interface AccountTransactionService {

  Mono<TransactionResponse> depositTransaction(String accountId,
      TransactionRequest request);

  Mono<TransactionResponse> withdrawalTransaction(String accountId, TransactionRequest request);

  Mono<TransferResponse> makeTransfer(TransferRequest request);

}
