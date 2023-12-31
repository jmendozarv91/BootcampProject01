package com.yanki.walletmobiletransaction.infraestructure.adapters.repository;

import com.yanki.walletmobiletransaction.domain.model.Transaction;
import com.yanki.walletmobiletransaction.domain.port.WalletTransactionPersistencePort;
import com.yanki.walletmobiletransaction.infraestructure.adapters.entity.TransactionEntity;
import com.yanki.walletmobiletransaction.infraestructure.adapters.entity.TransactionStatus;
import com.yanki.walletmobiletransaction.infraestructure.adapters.entity.TransactionType;
import com.yanki.walletmobiletransaction.infraestructure.adapters.mapper.TransactionDboMapper;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class TransactionRepositoryAdapter implements WalletTransactionPersistencePort {

  private final TransactionRepository transactionRepository;
  private final TransactionDboMapper transactionDboMapper;


  @Override
  public Mono<Void> linkDebitCard(Transaction transaction) {
    TransactionEntity transactionEntity = transactionDboMapper.toDboLinkDebit(transaction);
    transactionEntity.setType(TransactionType.DEBIT_CARD_LINKING.name());
    return transactionRepository.save(transactionEntity).then();
  }

  @Override
  public Mono<Void> cancelTransaction(Transaction transaction) {
    TransactionEntity transactionEntity = transactionDboMapper.toDboStatus(transaction);
    transactionEntity.setStatus(TransactionStatus.CANCELED.name());
    return transactionRepository.save(transactionEntity).then();
  }

  @Override
  public Mono<Transaction> create(Transaction model) {
    return Mono.just(model)
        .map(transactionDboMapper::toEntity)
        .flatMap(transactionRepository::save)
        .map(transactionDboMapper::toDomain);
  }

  @Override
  public Mono<Transaction> getById(String id) {
    return transactionRepository.findById(id)
        .map(transactionDboMapper::toDomain);
  }

  @Override
  public Flux<Transaction> getAll() {
    return transactionRepository.findAll()
        .map(transactionDboMapper::toDomain);
  }

  @Override
  public Mono<Void> deleteById(String id) {
    return transactionRepository.deleteById(id);
  }

  @Override
  public Mono<Transaction> update(Transaction model) {
    return Mono.just(model)
        .map(transactionDboMapper::toEntity)
        .flatMap(transactionRepository::save)
        .map(transactionDboMapper::toDomain);
  }

  @Override
  public Flux<Transaction> getByIds(List<String> ids) {
    return transactionRepository.findAllById(ids)
        .map(transactionDboMapper::toDomain);
  }


}
