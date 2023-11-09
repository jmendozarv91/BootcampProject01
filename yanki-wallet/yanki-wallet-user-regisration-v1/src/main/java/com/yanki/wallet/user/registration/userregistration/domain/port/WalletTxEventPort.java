package com.yanki.wallet.user.registration.userregistration.domain.port;

import com.yanki.wallet.user.registration.userregistration.application.event.TransactionEvent;

public interface WalletTxEventPort {
  // Métodos de escucha para consumir eventos (esto podría variar según tus necesidades)
  void publishWalletToWalletTransferEvent(TransactionEvent transactionEvent);
}
