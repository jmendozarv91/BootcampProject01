package com.yanki.walletmobiletransaction.application.usecases;

import com.yanki.walletmobiletransaction.domain.model.dto.CreditWalletRequest;
import com.yanki.walletmobiletransaction.domain.model.dto.DebitCardLinkResponse;
import com.yanki.walletmobiletransaction.domain.model.dto.LinkDebitCardRequest;
import com.yanki.walletmobiletransaction.domain.model.dto.TransactionRequest;
import com.yanki.walletmobiletransaction.domain.model.dto.TransactionResponse;
import com.yanki.walletmobiletransaction.domain.model.dto.TransactionStatusUpdateRequest;
import com.yanki.walletmobiletransaction.domain.model.dto.WalletBalanceResponse;
import com.yanki.walletmobiletransaction.domain.model.dto.WalletTransferRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface WalletTransactionService {

  Mono<TransactionResponse> cancelTransaction(String transactionId);

  Mono<TransactionResponse> createTransaction(TransactionRequest transactionRequest);

  Mono<WalletBalanceResponse> creditWalletToBankAccount(String walletId,
      CreditWalletRequest creditWalletRequest);


  Mono<TransactionResponse> getTransactionById(String transactionId);

  Flux<TransactionResponse> getTransactionsByUserId(String userId);

  Mono<WalletBalanceResponse> getWalletBalance(String walletId);

  Mono<DebitCardLinkResponse> linkDebitCard(LinkDebitCardRequest linkDebitCardRequest);

  Flux<TransactionResponse> listWalletTransactions(String walletId);

  Mono<WalletBalanceResponse> loadWalletFromDebitCard(String walletId);

  Mono<TransactionResponse> transferWalletToWallet(WalletTransferRequest walletTransferRequest);

  Mono<TransactionResponse> updateTransactionStatus(String transactionId,
      TransactionStatusUpdateRequest transactionStatusUpdateRequest);
}
