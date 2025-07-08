package contracts;

import entities.Customer;
import entities.PurchasableBook;

import java.math.BigDecimal;

public interface DeliveryStrategy {
    public BigDecimal deliver(PurchasableBook book, Customer customer);
}
