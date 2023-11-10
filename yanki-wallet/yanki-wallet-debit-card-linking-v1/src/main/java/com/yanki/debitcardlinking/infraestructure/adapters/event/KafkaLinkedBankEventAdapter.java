package com.yanki.debitcardlinking.infraestructure.adapters.event;

import com.yanki.debitcardlinking.application.event.DebitCardLinkedEvent;
import com.yanki.debitcardlinking.application.services.LinkDebitCardManagementService;
import com.yanki.debitcardlinking.domain.port.LinkedBankEventPort;
import com.yanki.debitcardlinking.infraestructure.adapters.event.exception.UnknownEventTypeException;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class KafkaLinkedBankEventAdapter implements LinkedBankEventPort {

  private final String TRANSACTION_EVENTS_TOPIC = "link-debit-events";
  private final LinkDebitCardManagementService linkDebitCardManagementService;

  @KafkaListener(topics = TRANSACTION_EVENTS_TOPIC, groupId = "yanki-transactions-group", containerFactory = "kafkaListenerContainerFactory")
  public void listen(@Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
      @Payload final DebitCardLinkedEvent event) {
    if (event == null || key == null) {
      log.error("Received null event or key");
      return;
    }

    log.info("Received Wallet Transaction Event: Event Type: {}, Key: {}", event, key);

    switch (event.getEventType()) {
      case WALLET_TO_WALLET_TRANSFER:
        handleWalletToWalletTransfer(event);
        break;
      case DEBIT_CARD_LINKED:
        handleDebitCardLinked(event);
        break;
      case LOAD_WALLET_FROM_DEBIT_CARD:
        handleLoadWalletFromDebitCard(event);
        break;
      case CREDIT_WALLET_TO_BANK_ACCOUNT:
        handleCreditWalletToBankAccount(event);
        break;
      case LINK_DEBIT_CARD:
        handleLinkDebitCard(event);
        break;
      default:
        throw new UnknownEventTypeException("Unknown event type: " + event.getEventType());
    }
  }

  private void handleWalletToWalletTransfer(DebitCardLinkedEvent event) {
    // Logic for Wallet to Wallet Transfer
  }

  private void handleLoadWalletFromDebitCard(DebitCardLinkedEvent event) {
    // Logic for Loading Wallet from Debit Card
  }

  private void handleCreditWalletToBankAccount(DebitCardLinkedEvent event) {
    // Logic for Crediting Wallet to Bank Account
  }

  private void handleLinkDebitCard(DebitCardLinkedEvent event) {
    // Logic for Linking Debit Card
  }

  @Override
  public void handleDebitCardLinked(DebitCardLinkedEvent event) {
    linkDebitCardManagementService.associateDebitCardWithWallet(event.getSourceWalletId(),
            event.getDebitCardNumber(), event.getBankAccountId())
        .subscribe(
            result -> log.info("Debit Card Linked Successfully"),
            error -> log.error("Error linking Debit Card", error)
        );
  }
}
