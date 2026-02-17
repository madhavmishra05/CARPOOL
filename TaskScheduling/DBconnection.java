import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/TaskScheduling";

    private static final String USER = "postgres";
    private static final String PASSWORD = "Madhav@321?";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}