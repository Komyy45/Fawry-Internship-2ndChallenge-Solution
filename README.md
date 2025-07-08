A bookstore management system developed for the Fawry N² Dev Slope #10 Challenge. The system manages different types of books (Paper Books, E-Books, and Showcase/Demo Books) with inventory management and purchasing capabilities.

## Project Structure

    src/
    ├── contracts/
    │   └── IPurchaseStrategy.java
    |
    ├── entities/
    │   ├── Book.java
    │   ├── PurchasableBook.java
    │   ├── PaperBook.java
    │   ├── EBook.java
    │   ├── DemoBook.java
    │   ├── BookStore.java
    │   └── Customer.java
    |
    ├── enums/
    │   └── FileType.java
    ├── exceptions/
    │   ├── BookNotFoundException.java
    │   ├── InSufficientQuantityException.java
    │   ├── InSufficientFundsException.java
    │   └── NonPurchasableBookException.java
    |
    ├── services/
    │   └── PaymentService.java
    |
    ├── value_objects/
    │   └── Address.java
    |
    ├── BookstoreFullTest.java
    └── Main.java


## Test Scenarios

The BookstoreFullTest class includes the following test scenarios:

    Adding Books to Inventory
        Add different types of books (Paper, E-Book, Demo)
        Test duplicate book additions
        Display current inventory

    Buying Books
        Purchase paper books with quantity
        Purchase e-books
        Verify stock reduction and balance updates

    Removing Outdated Books
        Remove books published before a specific date
        Display removed books count

    Error Scenarios
        Book not found exception
        Non-purchasable book exception (Demo books)
        Insufficient funds exception
        Insufficient quantity exception

## Running the Tests

![Screenshot 2025-07-08 210555](https://github.com/user-attachments/assets/56bf8a9e-b026-4ddc-a2b0-4fee379ce40a)

![Screenshot 2025-07-08 210614](https://github.com/user-attachments/assets/0489440f-066d-45a8-b523-ab326ac810c9)

![test3](https://github.com/user-attachments/assets/cd069c33-ce42-4e7e-aacb-348bb37cfc0e)

![errors](https://github.com/user-attachments/assets/ccde7f10-d5a3-4d21-8942-5c7639a61db1)
