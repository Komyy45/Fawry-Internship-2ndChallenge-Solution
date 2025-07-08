package entities;

import value_objects.Address;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaperBook extends PurchasableBook {
    private int quantity;

    public PaperBook(String ISBN, String title, LocalDate publishedOn, BigDecimal price, int quantity) {
        super(ISBN, title, publishedOn, price);
        this.quantity = quantity;
    }


    public void setQuantity(int quantity) {
        if(quantity < 0)
            throw new IllegalArgumentException("Book Quantity must be positive value");

        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }
}
