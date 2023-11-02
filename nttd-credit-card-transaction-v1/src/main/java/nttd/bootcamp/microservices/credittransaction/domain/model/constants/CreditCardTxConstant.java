package nttd.bootcamp.microservices.credittransaction.domain.model.constants;

public class CreditCardTxConstant {

  public static final String CURRENT_CREDIT_CARD_NOT_FOUND = "Credit card not found - ID %s";
  public static final String CURRENT_INSUFFICIENT_CREDIT = "Insufficient credit";
  public static final String CURRENT_CLIENT_NOT_FOUND = "Client don't found - ID %s";
  public static final String CURRENT_FULL_CREDIT = "The available balance is equal to the credit limit. No more transactions can be processed until a payment is made.";
}
