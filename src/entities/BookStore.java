package entities;

import exceptions.BookNotFoundException;
import exceptions.InSufficientQuantityException;
import exceptions.NonPurchasableBookException;
import factories.DeliveryStrategyFactory;
import services.PaymentService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class BookStore {
    private Map<String, Book> books = new HashMap<>();
    private PaymentService paymentService = new PaymentService();

    public BigDecimal buyBook(String ISBN, int requestedQuantity, Customer customer)
    {
        var book = this.books.get(ISBN);

        if(book == null)
            throw new BookNotFoundException();

        if(!(book instanceof PurchasableBook))
            throw new NonPurchasableBookException();

        if(book instanceof PaperBook)
        {
            var paperBook = (PaperBook)book;
            var availableQuantity = paperBook.getQuantity();
            if(availableQuantity < requestedQuantity)
                throw new InSufficientQuantityException();

            paperBook.setQuantity(paperBook.getQuantity() - requestedQuantity);
        }
        var purchasableBook = (PurchasableBook) book;
        paymentService.setPurchaseStrategy(new DeliveryStrategyFactory().createDeliveryStrategy(purchasableBook));

        var totalAmount = paymentService.executePurchase(purchasableBook, requestedQuantity, customer);
        return totalAmount;
    }

    public ArrayList<Book> removeOutDatedBooks(LocalDate threshold) {
        ArrayList<Book> outdatedBooks = new ArrayList<>();
        Iterator<Map.Entry<String, Book>> iterator = this.books.entrySet().iterator();

        while (iterator.hasNext()) {
            Map.Entry<String, Book> entry = iterator.next();
            if (!entry.getValue().getPublishDate().isAfter(threshold)) {
                outdatedBooks.add(entry.getValue());
                iterator.remove();
            }
        }

        return outdatedBooks;
    }

    public void addBook(Book book)
    {
        var bookISBN = book.getISBN();
        var existingBook = this.books.get(bookISBN);

        if(existingBook != null)
        {
            if(book instanceof PaperBook newPaperBook && existingBook instanceof PaperBook existingPaperBook)
                existingPaperBook.setQuantity(existingPaperBook.getQuantity() + newPaperBook.getQuantity());
        }
        else{
            this.books.put(bookISBN, book);
        }
    }

    public void printBooksInInventory()
    {
        this.books.forEach((isbn, book) -> {
            System.out.println(isbn + ": " + book.getTitle() + " - " +  book.getPublishDate());
        });
    }

    public void removeBook(String ISBN)
    {
        this.books.remove(ISBN);
    }
}
