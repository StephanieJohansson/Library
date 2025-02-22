import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// class for database connection
public class DatabaseConnection {
    // private final methods to get the URL, USERNAME and password for my database
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    // made a config to be able to hide my password
    private static final String PASSWORD = System.getenv("MYSQL_PASSWORD");

    // method to connect with my database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}