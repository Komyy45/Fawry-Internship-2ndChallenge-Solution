package exceptions;

public class InSufficientFundsException extends RuntimeException{

    public InSufficientFundsException()
    {
        super("Insufficient funds to complete the payment");
    }
}
