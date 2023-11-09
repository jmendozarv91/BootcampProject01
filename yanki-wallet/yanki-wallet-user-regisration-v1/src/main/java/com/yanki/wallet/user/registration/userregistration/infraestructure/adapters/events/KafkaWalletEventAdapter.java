package com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yanki.wallet.user.registration.userregistration.application.event.TransactionEvent;
import com.yanki.wallet.user.registration.userregistration.domain.port.WalletTxEventPort;
import com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.entity.UserEntity;
import com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.events.exception.InsufficientBalanceException;
import com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.events.exception.UserSourceNotFoundException;
import com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.events.exception.UserTargetNotFoundException;
import com.yanki.wallet.user.registration.userregistration.infraestructure.adapters.repository.UserRegistrationRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
@AllArgsConstructor
public class KafkaWalletEventAdapter implements WalletTxEventPort {

  private final String TRANSACTION_EVENTS_TOPIC = "transaction-events";
  private final KafkaTemplate<String, String> kafkaTemplate;
  private final UserRegistrationRepository userRegistrationRepository;

  @KafkaListener(topics = TRANSACTION_EVENTS_TOPIC)
  public void consumeWalletTxEvent(ConsumerRecord<String, String> record)
      throws JsonProcessingException {
    String eventJson = record.value();
    TransactionEvent walletTxEvent = new TransactionEvent(eventJson);
    log.info("Received Wallet Transaction Event:");
    log.info("Event Type: " + walletTxEvent.getEventType());

    switch (walletTxEvent.getEventType()) {
      case WALLET_TO_WALLET_TRANSFER:
        publishWalletToWalletTransferEvent(walletTxEvent);
        break;
      default:
        throw new IllegalArgumentException("Unknown event type: " + walletTxEvent.getEventType());
    }
  }


  @Override
  public void publishWalletToWalletTransferEvent(TransactionEvent event) {
    log.info("sourceWalletId: " + event.getSourceWalletId());
    log.info("targetPhoneNumber: " + event.getTargetPhoneNumber());
    log.info("amount: " + event.getAmount());

    // Obtener el usuario de origen
    UserEntity sourceUser = userRegistrationRepository.findByWalletId(event.getSourceWalletId()).block();
    if (sourceUser == null) {
      throw new UserSourceNotFoundException("User Source not found!");
    }

    // Obtener el usuario de destino
    UserEntity targetUser = userRegistrationRepository.findByPersonalInfoPhoneNumber(
        event.getTargetPhoneNumber()).block();
    if (targetUser == null) {
      throw new UserTargetNotFoundException("User Target not found!");
    }

    // Verificar que el saldo de la billetera de origen sea suficiente para realizar la transferencia
    if (sourceUser.getWallet().getBalance() < event.getAmount()) {
      throw new InsufficientBalanceException("Insufficient balance!");
    }

    // Actualizar el saldo de la billetera de destino

    targetUser.getWallet().setBalance(targetUser.getWallet().getBalance() + event.getAmount());

    // Guardar la información de la billetera de destino en la base de datos
    userRegistrationRepository.save(targetUser).block();

    // Actualizar el saldo de la billetera de origen
    sourceUser.getWallet().setBalance(sourceUser.getWallet().getBalance() - event.getAmount());

    // Guardar la información de la billetera de origen en la base de datos
    userRegistrationRepository.save(sourceUser).block();

    log.info("Wallet to Wallet Transfer Event published successfully.");

  }
}



