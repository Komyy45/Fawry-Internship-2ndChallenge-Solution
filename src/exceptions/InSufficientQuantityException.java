package exceptions;

public class InSufficientQuantityException extends RuntimeException{
    public InSufficientQuantityException()
    {
        super("Not enough stock");
    }
}
