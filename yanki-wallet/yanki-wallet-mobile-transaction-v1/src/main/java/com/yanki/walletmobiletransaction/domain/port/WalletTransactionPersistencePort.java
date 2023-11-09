package com.yanki.walletmobiletransaction.domain.port;

import com.yanki.walletmobiletransaction.domain.model.Transaction;
import com.yanki.walletmobiletransaction.domain.model.dto.TransactionRequest;
import java.util.List;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WalletTransactionPersistencePort {

  Mono<Transaction> create(Transaction creditCard);

  Mono<Transaction> getById(String id);

  Flux<Transaction> getAll();

  Mono<Void> deleteById(String id);

  Mono<Transaction> update(Transaction creditCard);

  Flux<Transaction> getByIds(List<String> ids);

  Mono<Void> cancelTransaction(Transaction transaction);
}
