package nttd.bootcamp.microservices.debitcardmanagement.domain.exception;

public class InsufficientFundsException extends RuntimeException {

  public InsufficientFundsException() {
    super("Insufficient funds in the primary account.");
  }

  public InsufficientFundsException(String message) {
    super(message);
  }

}