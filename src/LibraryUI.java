import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

// main class for the library user interface to handle interactions and communicates with LibraryApp
public class LibraryUI {
    // scanner to read input
    private static final Scanner scanner = new Scanner(System.in);
    private final LibraryApp libraryApp;

    // main method
    public LibraryUI(LibraryApp libraryApp) {
        this.libraryApp = libraryApp;
    }

    public void start() {
        // while loop to run the app til the user wants to quit
        while (true) {
            // showing the menu and reading user choice(1-8)
            showMenu();
            int choice = scanner.nextInt();
            scanner.nextLine();

            // a swith inside a tryCatch with a SQLException to handle users choice
            try {
                switch (choice) {
                    case 1:
                        listAllBooks();
                        break;
                    case 2:
                        loanBook();
                        break;
                    case 3:
                        returnBook();
                        break;
                    case 4:
                        listUserLoans();
                        break;
                    case 5:
                        addBook();
                        break;
                    case 6:
                        deleteBook();
                        break;
                    case 7:
                        listAllLoans();
                        break;
                    case 8:
                        System.out.println("Avslutar...");
                        return;
                    default:
                        System.out.println("Ogiltigt val.");
                }
            } catch (SQLException e) {
                // catching and printing eventual SQL errors
                System.out.println("Ett fel uppstod vid kommunikationen med databasen: " + e.getMessage());
            }
        }
    }

    // method with the menu shown to the users to choose from
    private void showMenu() {
        System.out.println("1. Lista alla böcker");
        System.out.println("2. Låna en bok");
        System.out.println("3. Lämna tillbaka en bok");
        System.out.println("4. Lista dina lån");
        System.out.println("5. Lägg till en bok (admin)");
        System.out.println("6. Ta bort en bok (admin)");
        System.out.println("7. Lista alla lån (admin)");
        System.out.println("8. Avsluta");
        System.out.print("Välj ett alternativ: ");
    }

    // method for listing all the books added to the database
    // with and SQLException that throws if something goes wrong with communicating with the database
    private void listAllBooks() throws SQLException {
        // get the list of all the books
        List<Book> books = libraryApp.getAllBooks();
        // iterate though the list and print them out
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // method to loan a book asking the user for a name and the book ID of choice
    private void loanBook() throws SQLException {
        System.out.print("Ange ditt användarnamn: ");
        String userName = scanner.nextLine();
        System.out.print("Ange bok-ID: ");
        int bookId = scanner.nextInt();
        // cleaning the scanner afterward
        scanner.nextLine();

        // calling the method to loan the book with the input from above and confirm with message
        libraryApp.loanBook(userName, bookId);
        System.out.println("Boken är nu utlånad.");
    }

    // method to return a book, asking the user for the ID of the book that they want to return
    private void returnBook() throws SQLException {
        System.out.print("Ange lån-ID: ");
        // reads the input and cleaning scanner
        int loanId = scanner.nextInt();
        scanner.nextLine();

        // calling the method to return the book with the said ID and confirm with message
        libraryApp.returnBook(loanId);
        System.out.println("Boken är nu återlämnad.");
    }

    // method to list all the loans from inputted name/user
    private void listUserLoans() throws SQLException {
        System.out.print("Ange ditt användarnamn: ");
        String userName = scanner.nextLine();

        // get the list of loaned books with inputted name
        List<String> loans = libraryApp.getUserLoans(userName);
        // iterate through list and print out all found
        for (String loan : loans) {
            System.out.println(loan);
        }
    }

    // method to add a book with a title and author
    private void addBook() throws SQLException {
        System.out.print("Ange titel: ");
        String title = scanner.nextLine();
        System.out.print("Ange författare: ");
        String author = scanner.nextLine();

        // creating the new book and adding it to the library and confirm with message
        libraryApp.addBook(new Book(0, title, author, true));
        System.out.println("Boken har lagts till.");
    }

    // method to deleta a book with the ID of the book they want to delete
    private void deleteBook() throws SQLException {
        System.out.print("Ange bok-ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();

        // calling the method to delete the said book from the library and confirm with message
        libraryApp.deleteBook(bookId);
        System.out.println("Boken har tagits bort.");
    }

    // method to list all loans in library/databse
    private void listAllLoans() throws SQLException {
        // get the list of all the loans
        List<String> loans = libraryApp.getAllLoans();
        // iterate through list and print them all out
        for (String loan : loans) {
            System.out.println(loan);
        }
    }
}
