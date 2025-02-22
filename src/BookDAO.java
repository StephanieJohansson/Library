import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// class that handles databaseoperations for books (DAO=data access object)
public class BookDAO {
    // method to get all the books from database
    public List<Book> getAllBooks() throws SQLException {
        // creates a list to store the books
        List<Book> books = new ArrayList<>();
        // choosing all the books from the tabell in my database
        String sql = "SELECT * FROM books";

        // statement to handle the database connection and prepared statement
        try (Connection conn = DatabaseConnection.getConnection();
             // preparing the SQL statement to execute the query
             PreparedStatement stmt = conn.prepareStatement(sql);
             // execute SQL query and store result
             ResultSet rs = stmt.executeQuery()) {

            // loops through the result to create the bookobject
            while (rs.next()) {
                books.add(new Book(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getBoolean("available")
                ));
            }
        }
        // returning the list with books
        return books;
    }

    // method to add a book into database
    public void addBook(Book book) throws SQLException {
        // SQL statement to insert the new book into my books table
        String sql = "INSERT INTO books (title, author, available) VALUES (?, ?, ?)";

        // same as above to connect with the database, and execute the update to insert the new book to the database
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book.getTitle());
            stmt.setString(2, book.getAuthor());
            stmt.setBoolean(3, book.isAvailable());
            stmt.executeUpdate();
        }
    }

    // method to delete a book with its ID from the database
    public void deleteBook(int id) throws SQLException {
        // SQL statement to delete the book from the books table based on its ID
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // method to update the availability status of the books in the database
    public void updateBookAvailability(int id, boolean available) throws SQLException {
        // SQL statement to update the status of the books based on their ID
        String sql = "UPDATE books SET available = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBoolean(1, available);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }
}