package nttd.bootcamp.microservices.debitcardmanagement.domain.model;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import nttd.bootcamp.microservices.debitcardmanagement.domain.exception.InsufficientFundsException;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DebitCard {
  private String id;
  private String cardNumber;
  private String expirationDate;
  private String securityCode;
  private Double balance;
  private String primaryAccountId;
  private List<String> associatedAccountIds;
  private String walletId;

  public void creditBalance(double amount) {
    this.balance += amount;
  }

  // Método para cargar saldo de la tarjeta de débito
  public void debitBalance(double amount) {
    if (this.balance < amount) {
      throw new InsufficientFundsException();
    }
    this.balance -= amount;
  }

  // Desasociar el monedero actual
  public void disassociateWallet() {
    this.walletId = null;
  }

  // Asociar un monedero con la tarjeta de débito
  public void associateWithWallet(String walletId) {
    if (this.walletId != null && !this.walletId.isEmpty()) {
      throw new IllegalStateException("Debit card is already associated with a wallet.");
    }
    this.walletId = walletId;
  }

  // Agregar una cuenta asociada
  public void addAssociatedAccountId(String accountId) {
    if (associatedAccountIds == null) {
      associatedAccountIds = new ArrayList<>();
    }
    associatedAccountIds.add(accountId);
  }

  // Remover una cuenta asociada
  public void removeAssociatedAccountId(String accountId) {
    if (associatedAccountIds != null) {
      associatedAccountIds.remove(accountId);
    }
  }



}
