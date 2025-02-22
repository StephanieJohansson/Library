import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// class that handles databaseoperations for loans
public class LoanDAO {
    // method to loan a book
    public void loanBook(String userName, int bookId) throws SQLException {
        // SQL statement to insert the loan into the database
        String sql = "INSERT INTO loans (user_name, book_id, loan_date) VALUES (?, ?, CURDATE())";

        // statement to handle the database connection and prepared statement
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // setting the statements
            stmt.setString(1, userName);
            stmt.setInt(2, bookId);
            // updates the database
            stmt.executeUpdate();
        }
    }

    // method to return a book
    public void returnBook(int loanId) throws SQLException {
        // SQL statement to update the returndate on a loan
        String sql = "UPDATE loans SET return_date = CURDATE() WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, loanId);
            stmt.executeUpdate();
        }
    }

    // method to get bookID by loanID
    public int getBookIdByLoanId(int loanId) throws SQLException {
        // SQL statement to get the bookID from a loan
        String sql = "SELECT book_id FROM loans WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, loanId);
            // run statement and get result
            try (ResultSet rs = stmt.executeQuery()) {
                // if there's a result (a book) then return it
                if (rs.next()) {
                    return rs.getInt("book_id");
                    // it there's no result print message
                } else {
                    throw new SQLException("Lån-ID:t finns inte.");
                }
            }
        }
    }

    // method to get all the loans from a specific user by the chosen name
    public List<String> getUserLoans(String userName) throws SQLException {
        // arraylist to store all loans
        List<String> loans = new ArrayList<>();
        // SQL statement to get loans from a user
        String sql = "SELECT books.title, loans.loan_date FROM loans JOIN books ON loans.book_id = books.id " +
                "WHERE loans.user_name = ? AND loans.return_date IS NULL";


        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, userName);
            // run the statement and get result
            try (ResultSet rs = stmt.executeQuery()) {
                // loops through result and adding the loan to the list
                while (rs.next()) {
                    loans.add(rs.getString("title") + " (Lånad: " + rs.getDate("loan_date") + ")");
                }
            }
        }
        // return the list of loans
        return loans;
    }

    // method to get all the loans from database
    public List<String> getAllLoans() throws SQLException {
        //arraylist to store the loans
        List<String> loans = new ArrayList<>();
        String sql = "SELECT books.title, loans.user_name, loans.loan_date, loans.return_date " +
                "FROM loans JOIN books ON loans.book_id = books.id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            // loops through the result and adding it to the list
            while (rs.next()) {
                loans.add(rs.getString("title") + " - " + rs.getString("user_name") +
                        " (Lånad: " + rs.getDate("loan_date") + ", Återlämnad: " + rs.getDate("return_date") + ")");
            }
        }
        // return the list with title, username, loandate and returndate
        return loans;
    }
}