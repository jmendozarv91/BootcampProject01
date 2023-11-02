package nttd.bootcamp.microservices.accountmanagement.infraestructure.adapter.config.mongo;

import nttd.bootcamp.microservices.accountmanagement.infraestructure.adapter.entities.AccountEntity;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

@Component
public class AccountEntityListener extends AbstractMongoEventListener<AccountEntity> {

  @Override
  public void onBeforeConvert(BeforeConvertEvent<AccountEntity> event) {
    super.onBeforeConvert(event);
    AccountEntity account = event.getSource();
    if (account.getBankId() == null || account.getBankId().isEmpty()) {
      account.setBankId("BANK-1");
    }
  }
}
