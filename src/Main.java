import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        Connection connection = null;
        try {
            connection = DB.getConnection();
            System.out.println("Connection is Good");
        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

            SwingUtilities.invokeLater(() -> {
                WelcomePage welcomePage = new WelcomePage();
                welcomePage.setVisible(true);
            });


    }
}




