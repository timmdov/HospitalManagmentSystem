import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class AddUser extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeComboBox;
    private JTextField nameField;
    private JButton addButton;

    public AddUser() {
        setTitle("Add User");
        setSize(350, 200);
        setLayout(new GridLayout(5, 2));

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        userTypeComboBox = new JComboBox<>(new String[]{"Doctor", "Nurse", "Patient"});
        nameField = new JTextField(20);
        addButton = new JButton("Add");

        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("User Type:"));
        add(userTypeComboBox);
        add(new JLabel("Name:"));
        add(nameField);
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String userType = (String) userTypeComboBox.getSelectedItem();
                String name = nameField.getText();

                String hashedPassword = hashPassword(password);

                if (addUserToDatabase(username, hashedPassword, userType, name)) {
                    JOptionPane.showMessageDialog(null, "User added");
                } else {
                    JOptionPane.showMessageDialog(null, "Error"); }
            }
        });
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes(StandardCharsets.UTF_8));
            BigInteger number = new BigInteger(1, hash);
            StringBuilder hexString = new StringBuilder(number.toString(16));
            while (hexString.length() < 32) {
                hexString.insert(0, '0');
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private boolean addUserToDatabase(String username, String hashedPassword, String userType, String name) {
        int userID = 0;
        try {
            userID = generateUniqueUserID();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO User (UserID, Username, Password, UserType, Name) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystemTwo",
                "root",
                "root12345");
             PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setInt(1, userID);
            pst.setString(2, username);
            pst.setString(3, hashedPassword);
            pst.setString(4, userType);
            pst.setString(5, name);

            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private int generateUniqueUserID() throws SQLException {
        int uniqueID = 7;
        String jdbcURL = "jdbc:mysql://localhost:3306/HospitalManagementSystemTwo";
        String dbUsername = "root";
        String dbPassword = "root12345";

        String sql = "SELECT MAX(UserID) as MaxID FROM User";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);
             PreparedStatement pst = connection.prepareStatement(sql)) {
            ResultSet resultSet = pst.executeQuery();

            if (resultSet.next()) {
                uniqueID = resultSet.getInt("MaxID") + 1;
            }
        }

        return uniqueID;
    }



        public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AddUser().setVisible(true);
        });
    }
}

