package entities;

import contracts.DeliveryStrategy;

import java.math.BigDecimal;

public class EmailDeliveryStrategy implements DeliveryStrategy {

    @Override
    public BigDecimal deliver(PurchasableBook book, Customer customer) {

        System.out.println("The Book Download link has been sent to " + customer.getEmail());
        return BigDecimal.ZERO;
    }
}
