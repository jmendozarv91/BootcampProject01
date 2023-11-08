package com.yanki.walletmobiletransaction.domain.port;

import com.yanki.walletmobiletransaction.application.events.TransactionEvent;
import com.yanki.walletmobiletransaction.domain.model.dto.LinkDebitCardRequest;
import com.yanki.walletmobiletransaction.domain.model.dto.TransactionRequest;
import com.yanki.walletmobiletransaction.domain.model.dto.TransactionStatusUpdateRequest;
import com.yanki.walletmobiletransaction.domain.model.dto.WalletTransferRequest;
import reactor.core.publisher.Mono;

public interface WalletTransactionEventPort {

  /**
   * Publishes a generic transaction event to Kafka.
   * @param transactionEvent The transaction event to publish.
   */
  void publishTransactionEvent(TransactionEvent transactionEvent);

  /**
   * Publishes an event when a transaction is cancelled.
   * @param transactionId The identifier of the transaction that was cancelled.
   */
  void publishTransactionCanceledEvent(String transactionId);

  /**
   * Publishes an event when a transaction status is updated.
   * @param transactionId The identifier of the transaction being updated.
   * @param status The new status of the transaction.
   */
  void publishTransactionStatusUpdatedEvent(String transactionId, String status);

  /**
   * Publishes an event when a wallet-to-wallet transfer occurs.
   * @param sourceWalletId The identifier of the source wallet.
   * @param targetWalletId The identifier of the target wallet.
   * @param amount The amount transferred.
   */
  void publishWalletToWalletTransferEvent(String sourceWalletId, String targetWalletId, double amount);

  /**
   * Publishes an event when a debit card is linked to a wallet.
   * @param walletId The identifier of the wallet to which the card is linked.
   * @param debitCardNumber The number of the debit card.
   */
  void publishDebitCardLinkedEvent(String walletId, String debitCardNumber);

  /**
   * Publishes an event when funds are loaded onto a wallet from a debit card.
   * @param walletId The identifier of the wallet being loaded.
   * @param amount The amount loaded.
   */
  void publishLoadWalletFromDebitCardEvent(String walletId, double amount);

  /**
   * Publishes an event when a wallet balance is credited to a bank account.
   * @param walletId The identifier of the wallet.
   * @param bankAccountId The bank account ID where funds are credited.
   * @param amount The amount credited.
   */
  void publishCreditWalletToBankAccountEvent(String walletId, String bankAccountId, double amount);
}