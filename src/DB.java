import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/HospitalManagementSystemTwo";
    private static final String username = "root";
    private static final String password = "root12345";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(jdbcUrl, username, password);
    }
}

