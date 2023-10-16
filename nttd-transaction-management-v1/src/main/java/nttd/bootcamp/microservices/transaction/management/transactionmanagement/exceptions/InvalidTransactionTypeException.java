package nttd.bootcamp.microservices.transaction.management.transactionmanagement.exceptions;

public class InvalidTransactionTypeException extends RuntimeException {
    public InvalidTransactionTypeException(String message) {
        super(message);
    }
}
