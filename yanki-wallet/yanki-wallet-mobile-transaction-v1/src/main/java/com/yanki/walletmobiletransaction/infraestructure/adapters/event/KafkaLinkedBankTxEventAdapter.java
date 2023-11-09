package com.yanki.walletmobiletransaction.infraestructure.adapters.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yanki.walletmobiletransaction.application.events.TransactionEvent;
import com.yanki.walletmobiletransaction.application.events.TransactionEvent.EventType;
import com.yanki.walletmobiletransaction.domain.port.WalletLinkedBankTxEventPort;
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
public class KafkaLinkedBankTxEventAdapter implements WalletLinkedBankTxEventPort {

  private final String TRANSACTION_EVENTS_TOPIC = "link-debit-events";
  private final KafkaTemplate<String, String> kafkaTemplate;
  private final ObjectMapper objectMapper;

  @Override
  public Mono<Void> publishDebitCardLinkedEvent(String walletId, String debitCardNumber,
      String accountId) {
    TransactionEvent event = TransactionEvent.builder()
        .sourceWalletId(walletId)
        .debitCardNumber(debitCardNumber)
        .bankAccountId(accountId)
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
      CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(topic, key, message)
          .completable();
      return Mono.fromFuture(() -> future).then();
    } catch (JsonProcessingException e) {
      log.error("Could not serialize event object to JSON", e);
      return Mono.error(e);
    }
  }

}
