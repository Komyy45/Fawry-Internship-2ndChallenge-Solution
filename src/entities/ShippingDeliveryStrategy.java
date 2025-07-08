package entities;

import contracts.DeliveryStrategy;

import java.math.BigDecimal;

public class ShippingDeliveryStrategy implements DeliveryStrategy
{
    private final BigDecimal SHIPPING_COST_PERCENT = BigDecimal.valueOf(2.5 / 100);
    @Override
    public BigDecimal deliver(PurchasableBook book, Customer customer) {
        var bookPrice = book.getPrice();
        System.out.println("Item has been Shipped to " + customer.getAddress());
        return bookPrice.multiply(SHIPPING_COST_PERCENT);
    }
}
