package exceptions;

public class NonPurchasableBookException extends RuntimeException{
    public NonPurchasableBookException()
    {
        super("This Book isn't available for purchase");
    }
}
