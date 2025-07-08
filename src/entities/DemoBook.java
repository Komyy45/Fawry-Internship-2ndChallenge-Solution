package entities;

import java.time.LocalDate;

public class DemoBook extends Book {
    public DemoBook(String ISBN, String title, LocalDate publishedOn) {
        super(ISBN, title, publishedOn);
    }
}
