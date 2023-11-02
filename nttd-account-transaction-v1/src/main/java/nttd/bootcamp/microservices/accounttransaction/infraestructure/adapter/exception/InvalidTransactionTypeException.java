package nttd.bootcamp.microservices.accounttransaction.infraestructure.adapter.exception;

public class InvalidTransactionTypeException extends RuntimeException {
    public InvalidTransactionTypeException(String message) {
        super(message);
    }
}
