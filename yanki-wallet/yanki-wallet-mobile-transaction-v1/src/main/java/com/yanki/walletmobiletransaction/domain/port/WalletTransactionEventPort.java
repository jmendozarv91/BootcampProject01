package com.yanki.walletmobiletransaction.domain.port;

import com.yanki.walletmobiletransaction.application.events.TransactionEvent;
import reactor.core.publisher.Mono;

public interface WalletTransactionEventPort {

  /**
   * Publishes a generic transaction event to Kafka.
   *
   * @param transactionEvent The transaction event to publish.
   */
  Mono<Void> publishTransactionEvent(TransactionEvent transactionEvent);

  /**
   * Publishes an event when a transaction is cancelled.
   *
   * @param transactionId The identifier of the transaction that was cancelled.
   */
  Mono<Void> publishTransactionCanceledEvent(String transactionId);

  /**
   * Publishes an event when a transaction status is updated.
   *
   * @param transactionId The identifier of the transaction being updated.
   * @param status        The new status of the transaction.
   */
  Mono<Void> publishTransactionStatusUpdatedEvent(String transactionId, String status);

  /**
   * Publishes an event when a wallet-to-wallet transfer occurs.
   *
   * @param sourceWalletId The identifier of the source wallet.
   * @param targetPhoneNumber The identifier of the target wallet.
   * @param amount         The amount transferred.

   *
   */
  Mono<Void> publishWalletToWalletTransferEvent(String sourceWalletId, String targetPhoneNumber, double amount);


}
