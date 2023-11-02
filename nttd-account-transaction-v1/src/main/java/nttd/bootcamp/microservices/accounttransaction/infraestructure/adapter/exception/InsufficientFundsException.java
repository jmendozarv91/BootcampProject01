package nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.exception;

public class InsufficientFundsException extends RuntimeException{
    public InsufficientFundsException(String message) {
        super(message);
    }
}
