package com.yanki.walletmobiletransaction.infraestructure.adapters.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yanki.walletmobiletransaction.application.events.TransactionEvent;
import com.yanki.walletmobiletransaction.application.events.TransactionEvent.EventType;
import com.yanki.walletmobiletransaction.domain.port.WalletTransactionEventPort;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Slf4j
@AllArgsConstructor
@Service
public class KafkaWalletTransactionEventAdapter implements WalletTransactionEventPort {

  private final String TRANSACTION_EVENTS_TOPIC = "transaction-events";

  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper objectMapper;

  @Override
  public Mono<Void> publishTransactionEvent(TransactionEvent transactionEvent) {
    return send(TRANSACTION_EVENTS_TOPIC, transactionEvent.getTransactionId(), transactionEvent);
  }

  @Override
  public Mono<Void> publishTransactionCanceledEvent(String transactionId) {
    TransactionEvent event = TransactionEvent.builder()
        .transactionId(transactionId)
        .eventType(EventType.TRANSACTION_CANCELED)
        .build();
    return send(TRANSACTION_EVENTS_TOPIC, transactionId, event);
  }

  @Override
  public Mono<Void> publishTransactionStatusUpdatedEvent(String transactionId, String status) {
    TransactionEvent event = TransactionEvent.builder()
        .transactionId(transactionId)
        .status(status)
        .eventType(EventType.TRANSACTION_STATUS_UPDATED)
        .build();
    return send(TRANSACTION_EVENTS_TOPIC, transactionId, event);
  }


  @Override
  public Mono<Void> publishWalletToWalletTransferEvent(String sourceWalletId, String targetPhoneNumber, double amount) {
    TransactionEvent event = TransactionEvent.builder()
        .sourceWalletId(sourceWalletId)
        .targetPhoneNumber(targetPhoneNumber)
        .amount(amount)
        .eventType(EventType.WALLET_TO_WALLET_TRANSFER)
        .build();
    log.info("event -->" + event);
    return send(TRANSACTION_EVENTS_TOPIC, sourceWalletId, event);
  }

  @Override
  public Mono<Void> publishDebitCardLinkedEvent(String walletId, String debitCardNumber) {
    TransactionEvent event = TransactionEvent.builder()
        .sourceWalletId(walletId)
        .debitCardNumber(debitCardNumber)
        .eventType(EventType.DEBIT_CARD_LINKED)
        .build();
    return send(TRANSACTION_EVENTS_TOPIC, walletId, event);
  }

  @Override
  public Mono<Void> publishLoadWalletFromDebitCardEvent(String walletId, double amount) {
    TransactionEvent event = TransactionEvent.builder()
        .sourceWalletId(walletId)
        .amount(amount)
        .eventType(EventType.LOAD_WALLET_FROM_DEBIT_CARD)
        .build();
    return send(TRANSACTION_EVENTS_TOPIC, walletId, event);
  }

  @Override
  public Mono<Void> publishCreditWalletToBankAccountEvent(String walletId, String bankAccountId,
      double amount) {
    TransactionEvent event = TransactionEvent.builder()
        .sourceWalletId(walletId)
        .bankAccountId(bankAccountId)
        .eventType(EventType.CREDIT_WALLET_TO_BANK_ACCOUNT)
        .build();
    return send(TRANSACTION_EVENTS_TOPIC, walletId, event);
  }

  private Mono<Void> send(String topic, String key, TransactionEvent value) {
    try {
      String message = objectMapper.writeValueAsString(value);
      log.info("xxxx -->" + message);
      CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, message)
          .completable();
      return Mono.fromFuture(() -> future).then();
    } catch (JsonProcessingException e) {
      log.error("Could not serialize event object to JSON", e);
      return Mono.error(e);
    }
  }

}