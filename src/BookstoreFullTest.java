import entities.*;
import enums.FileType;
import value_objects.Address;
import exceptions.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class BookstoreFullTest {

    private BookStore bookStore;
    private Customer customer;

    private DemoBook demoBook;
    private PaperBook paperBook;
    private EBook eBook;

    public BookstoreFullTest() {
        this.bookStore = new BookStore();
        setupTestData();
    }

    private void setupTestData() {
        customer = new Customer(
                "6B29FC40-CA47-1067-B31D-00DD010662DA",
                "Ibrahim",
                "Ibrahim@fawry.com",
                new Address("Egypt", "Cairo", "Borsaed", "1A"),
                BigDecimal.valueOf(10_000)
        );

        demoBook = new DemoBook(
                "978-3-16-148410-0",
                "Clean Code",
                LocalDate.of(2008, 8, 1)
        );

        paperBook = new PaperBook(
                "978-0-13-235088-4",
                "Effective Java",
                LocalDate.of(2018, 1, 6),
                BigDecimal.valueOf(200),
                10
        );

        eBook = new EBook(
                "978-1-4919-1889-0",
                "Domain-Driven Design",
                LocalDate.of(2003, 8, 1),
                BigDecimal.valueOf(1_000),
                FileType.PDF
        );
    }

    public void runAllTests() {
        testAddingBooks();
        testBuyingBooks();
        testRemoveOutdatedBooks();
        testErrorScenarios();
    }

    private void testAddingBooks() {
        System.out.println("\nbook store: Test 1: Adding Books to Inventory");

        System.out.println("Adding demo book");
        bookStore.addBook(demoBook);

        System.out.println("Adding e-book");
        bookStore.addBook(eBook);

        System.out.println("Adding paper book");
        bookStore.addBook(paperBook);
        
        System.out.println("PaperBook Quantity After 1st addition: " + paperBook.getQuantity());

        System.out.println("\nTrying to add same paper book again...");
        bookStore.addBook(paperBook);
        System.out.println("PaperBook Quantity After 2nd addition: " + paperBook.getQuantity());

        System.out.println("\nCurrent Inventory:");
        bookStore.printBooksInInventory();
    }

    private void testBuyingBooks() {
        System.out.println("\nBook store: Test 2: Buying Books");

        System.out.println("Customer Initial Balance: $" + customer.getBalance());
        System.out.println("PaperBook Initial Quantity: " + paperBook.getQuantity());

        try {
            System.out.println("\nBuying 3 copies of '" + paperBook.getTitle());
            BigDecimal paidAmount = bookStore.buyBook(paperBook.getISBN(), 3, customer);

            System.out.println("Total Paid Amount: $" + paidAmount);
            System.out.println("PaperBook Quantity After Purchase: " + paperBook.getQuantity());
            System.out.println("Customer Balance After Purchase: $" + customer.getBalance());

        } catch (Exception e) {
            System.err.println("Purchase failed: " + e.getMessage());
        }

        try {
            System.out.println("\nBuying e-book '" + eBook.getTitle() + "'...");
            BigDecimal eBookAmount = bookStore.buyBook(eBook.getISBN(), 1, customer);
            System.out.println("E-book purchased for: $" + eBookAmount);
            System.out.println("Customer Balance: $" + customer.getBalance());

        } catch (Exception e) {
            System.err.println("E-book purchase failed: " + e.getMessage());
        }
    }

    private void testRemoveOutdatedBooks() {
        System.out.println("\nbook store: Test 3: Removing Outdated Books");

        PaperBook oldBook1 = new PaperBook(
                "978-0-201-63361-0",
                "Design Patterns",
                LocalDate.of(1994, 10, 31),
                BigDecimal.valueOf(150),
                5
        );

        EBook oldBook2 = new EBook(
                "978-0-321-12742-6",
                "Refactoring",
                LocalDate.of(1999, 7, 8),
                BigDecimal.valueOf(80),
                FileType.PDF
        );

        bookStore.addBook(oldBook1);
        bookStore.addBook(oldBook2);

        System.out.println("Books before removal:");
        bookStore.printBooksInInventory();

        LocalDate cutoffDate = LocalDate.of(2010, 9, 1);
        System.out.println("\nRemoving books published before: " + cutoffDate);

        List<Book> outdatedBooks = bookStore.removeOutDatedBooks(cutoffDate);

        System.out.println("\nBooks after removal:");
        bookStore.printBooksInInventory();

        System.out.println("\nRemoved " + outdatedBooks.size() + " outdated books:");
        for (Book book : outdatedBooks) {
            System.out.println(book.getTitle() + " (Published: " + book.getPublishDate() + ")");
        }
    }

    private void testErrorScenarios() {
        System.out.println("\nbook store: Test 4: Error Scenarios");

        testBookNotFound();
        testNonPurchasableBook();
        testInsufficientFunds();
        testInsufficientQuantity();
    }

    private void testBookNotFound() {
        System.out.println("\n4.1 Testing BookNotFoundException:");
        try {
            bookStore.buyBook("INVALID-ISBN", 1, customer);
        } catch (BookNotFoundException e) {
            System.out.println("Expected error caught: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    private void testNonPurchasableBook() {
        System.out.println("\n4.2 Testing NonPurchasableBookException:");
        try {
            bookStore.addBook(demoBook);
            bookStore.buyBook(demoBook.getISBN(), 1, customer);
        } catch (NonPurchasableBookException e) {
            System.out.println("Expected error caught: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    private void testInsufficientFunds() {
        System.out.println("\n4.3 Testing InsufficientFundsException:");

        Customer poorCustomer = new Customer(
                "123456",
                "Poor Customer",
                "poor@example.com",
                new Address("Country", "City", "Street", "1"),
                BigDecimal.valueOf(50)
        );

        try {
            bookStore.addBook(paperBook);
            bookStore.buyBook(paperBook.getISBN(), 1, poorCustomer);
        } catch (InSufficientFundsException e) {
            System.out.println("Expected error caught: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }

    private void testInsufficientQuantity() {
        System.out.println("\n4.4 Testing InsufficientQuantityException:");

        PaperBook limitedBook = new PaperBook(
                "978-LIMITED",
                "Limited Edition",
                LocalDate.of(2024, 1, 1),
                BigDecimal.valueOf(500),
                2
        );

        try {
            bookStore.addBook(limitedBook);
            bookStore.buyBook(limitedBook.getISBN(), 5, customer);
        } catch (InSufficientQuantityException e) {
            System.out.println("Expected error caught: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error: " + e.getMessage());
        }
    }
}