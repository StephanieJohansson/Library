import java.sql.SQLException;
import java.util.List;

// main class for the app
public class LibraryApp {
    // static databaseoperation objects to integrate with the database
    private static final BookDAO bookDAO = new BookDAO();
    private static final LoanDAO loanDAO = new LoanDAO();

    // main method to run the app
    public static void main(String[] args) {
        // create an instance of LibraryUI and sends with LibraryApp
        LibraryUI libraryUI = new LibraryUI(new LibraryApp());
        // starting interface(UI)
        libraryUI.start();
    }

    // method to get all the books from database
    public List<Book> getAllBooks() throws SQLException {
        return bookDAO.getAllBooks();
    }

    // method to loan a book asking you for a name and the book ID
    public void loanBook(String userName, int bookId) throws SQLException {
        // updates the book availability when loaned
        bookDAO.updateBookAvailability(bookId, false);
        // registers the loan in the database
        loanDAO.loanBook(userName, bookId);
    }

    // method to return a book with your loan ID
    public void returnBook(int loanId) throws SQLException {
        // get the book ID from the loan
        int bookId = loanDAO.getBookIdByLoanId(loanId);
        // registers the book as returned
        loanDAO.returnBook(loanId);
        // update books availability
        bookDAO.updateBookAvailability(bookId, true);
    }

    // method to get users loan from database
    public List<String> getUserLoans(String userName) throws SQLException {
        return loanDAO.getUserLoans(userName);
    }

    // method to add book in database
    public void addBook(Book book) throws SQLException {
        bookDAO.addBook(book);
    }

    // method to delete book in database
    public void deleteBook(int bookId) throws SQLException {
        bookDAO.deleteBook(bookId);
    }

    // method to list all loans registered from database
    public List<String> getAllLoans() throws SQLException {
        return loanDAO.getAllLoans();
    }
}
