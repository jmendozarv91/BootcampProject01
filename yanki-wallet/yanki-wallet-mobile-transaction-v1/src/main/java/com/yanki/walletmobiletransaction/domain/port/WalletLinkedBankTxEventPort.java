package com.yanki.walletmobiletransaction.domain.port;

import reactor.core.publisher.Mono;

public interface WalletLinkedBankTxEventPort {
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
