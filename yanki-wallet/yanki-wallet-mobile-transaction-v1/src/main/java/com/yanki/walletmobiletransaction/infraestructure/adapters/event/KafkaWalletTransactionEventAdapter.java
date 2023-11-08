package com.yanki.walletmobiletransaction.infraestructure.adapters.event;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yanki.walletmobiletransaction.application.events.TransactionEvent;
import com.yanki.walletmobiletransaction.application.events.TransactionEvent.EventType;
import com.yanki.walletmobiletransaction.domain.port.WalletTransactionEventPort;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaWalletTransactionEventAdapter implements WalletTransactionEventPort {


  private static final String TRANSACTION_EVENTS_TOPIC = "transaction-events";
  private final KafkaProducer<String, String> producer;
  private final ObjectMapper objectMapper;

  @Override
  public void publishTransactionEvent(TransactionEvent transactionEvent) {
    send(TRANSACTION_EVENTS_TOPIC, transactionEvent.getTransactionId(), transactionEvent);
  }

  @Override
  public void publishTransactionCanceledEvent(String transactionId) {
    send(TRANSACTION_EVENTS_TOPIC, transactionId, TransactionEvent.builder()
        .transactionId(transactionId)
        .eventType(EventType.TRANSACTION_CANCELED)
        .build());
  }

  @Override
  public void publishTransactionStatusUpdatedEvent(String transactionId, String status) {

    TransactionEvent event = TransactionEvent.builder()
        .transactionId(transactionId)
        .status(status)
        .eventType(EventType.TRANSACTION_STATUS_UPDATED)
        .build();

    send(TRANSACTION_EVENTS_TOPIC, transactionId, event);
  }

  @Override
  public void publishWalletToWalletTransferEvent(String sourceWalletId, String targetWalletId,
      double amount) {
    TransactionEvent event = TransactionEvent.builder()
        .sourceWalletId(sourceWalletId)
        .targetWalletId(targetWalletId)
        .amount(amount)
        .eventType(EventType.WALLET_TO_WALLET_TRANSFER)
        .build();
    send(TRANSACTION_EVENTS_TOPIC, sourceWalletId, event);
  }

  @Override
  public void publishDebitCardLinkedEvent(String walletId, String debitCardNumber) {
    TransactionEvent event = TransactionEvent.builder()
        .sourceWalletId(walletId)
        .debitCardNumber(debitCardNumber)
        .eventType(EventType.DEBIT_CARD_LINKED)
        .build();
    send(TRANSACTION_EVENTS_TOPIC, walletId, event);
  }

  @Override
  public void publishLoadWalletFromDebitCardEvent(String walletId, double amount) {
    TransactionEvent event = TransactionEvent.builder()
        .sourceWalletId(walletId)
        .amount(amount)
        .eventType(EventType.LOAD_WALLET_FROM_DEBIT_CARD)
        .build();

    send(TRANSACTION_EVENTS_TOPIC, walletId, event);
  }

  @Override
  public void publishCreditWalletToBankAccountEvent(String walletId, String bankAccountId,
      double amount) {
    TransactionEvent event = TransactionEvent.builder()
        .sourceWalletId(walletId)
        .bankAccountId(bankAccountId)
        .eventType(EventType.CREDIT_WALLET_TO_BANK_ACCOUNT)
        .build();
    send(TRANSACTION_EVENTS_TOPIC, walletId, event);
  }

  private void send(String topic, String key, Object value) {
    try {
      String message = objectMapper.writeValueAsString(value);
      producer.send(new ProducerRecord<>(topic, key, message));
    } catch (JsonProcessingException e) {
      log.error("Could not serialize event object to JSON", e);
    }
  }

}