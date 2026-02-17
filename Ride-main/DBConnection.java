import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/carpooling";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Madhav@321?";

    // Simple helper to open a new connection per call.
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
