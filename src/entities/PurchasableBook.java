package entities;
import java.math.BigDecimal;
import java.time.LocalDate;

public abstract class PurchasableBook extends Book {
     private BigDecimal price;

     public PurchasableBook(String ISBN,
                            String title,
                            LocalDate publishedOn,
                            BigDecimal price)
     {
          super(ISBN, title, publishedOn);
          this.price = price;
     }

     public void setPrice(BigDecimal price) {
          this.price = price;
     }

     public BigDecimal getPrice() {
          return price;
     }
}
