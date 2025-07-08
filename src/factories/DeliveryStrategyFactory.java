package factories;

import contracts.DeliveryStrategy;
import entities.*;

public final class DeliveryStrategyFactory {

    public DeliveryStrategy createDeliveryStrategy(PurchasableBook book)
    {
        if (book instanceof PaperBook)
            return new ShippingDeliveryStrategy();
        else if (book instanceof EBook)
            return new EmailDeliveryStrategy();
        else
            throw new UnsupportedOperationException("Unsupported book type.");
    }
}
