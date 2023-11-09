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

  /**
   * Publishes an event when a debit card is linked to a wallet.
   *
   * @param walletId        The identifier of the wallet to which the card is linked.
   * @param debitCardNumber The number of the debit card.
   */
  Mono<Void> publishDebitCardLinkedEvent(String walletId, String debitCardNumber);

  /**
   * Publishes an event when funds are loaded onto a wallet from a debit card.
   *
   * @param walletId The identifier of the wallet being loaded.
   * @param amount   The amount loaded.
   */
  Mono<Void> publishLoadWalletFromDebitCardEvent(String walletId, double amount);

  /**
   * Publishes an event when a wallet balance is credited to a bank account.
   *
   * @param walletId      The identifier of the wallet.
   * @param bankAccountId The bank account ID where funds are credited.
   * @param amount        The amount credited.
   */
  Mono<Void> publishCreditWalletToBankAccountEvent(String walletId, String bankAccountId, double amount);
}
