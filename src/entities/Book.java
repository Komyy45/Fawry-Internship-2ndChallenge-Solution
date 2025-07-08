package entities;

import java.lang.*;
import java.time.LocalDate;

public abstract class Book {
    private final String ISBN;
    private String title;

    private LocalDate publishedOn;

    public Book(String ISBN,
                String title,
                LocalDate publishedOn)
    {
        this.ISBN = ISBN;
        this.title = title;
        this.publishedOn = publishedOn;
    }

    public String getISBN(){
        return this.ISBN;
    }

    public String getTitle(){
        return this.title;
    }

    public LocalDate getPublishDate(){ return this.publishedOn; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishedOn(LocalDate publishedOn) {
        this.publishedOn = publishedOn;
    }


}
