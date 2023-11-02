package nttd.bootcamp.microservices.accounttransaction.domain.model.constants;

public class TransactionConstant {

  public static final double COMMISSION_FEE = 5.00;
  public static final int MAX_FREE_TRANSACTIONS = 20;

  public static final String INSUFFICIENT_FUNDS_FOR_COMMISSION = "Insufficient funds to cover the commission";
  public static final String INSUFFICIENT_FUNDS = "Insufficient funds";
  public static final String INVALID_TRANSACTION_TYPE = "Invalid transaction type";

  public static final String AMOUNT_EXCEEDS_PENDING_CREDIT = "Amount exceeds pending credit";

  public static final String DEPOSIT = "deposit";
  public static final String WITHDRAWAL = "withdrawal";

  public static final String ACCOUNT_UNEXPECTED_ERROR = "Unexpected error";

  public static final String INVALID_TRANSFER_BETWEEN_DIFFERENT_BANKS = "Invalid transfer between accounts from different banks";
}
