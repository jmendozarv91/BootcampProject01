package com.yanki.debitcardlinking.domain.port;

import com.yanki.debitcardlinking.application.event.DebitCardLinkedEvent;

public interface LinkedBankEventPort {
   void handleDebitCardLinked(DebitCardLinkedEvent event);
}
