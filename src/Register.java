import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;



public class Register extends JFrame implements ActionListener {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> userTypeCombo;
    private JTextField nameField;
    private JButton registerButton;

    public Register() {
        setTitle("Register New User");
        setSize(300, 200);
        setLayout(new GridLayout(5, 2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JLabel("Username:"));
        usernameField = new JTextField();
        add(usernameField);

        add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        add(passwordField);

        add(new JLabel("User Type:"));
        String[] userTypes = {"Doctor", "Nurse", "Patient" };
        userTypeCombo = new JComboBox<>(userTypes);
        add(userTypeCombo);

        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        registerButton = new JButton("Register");
        registerButton.addActionListener(this);
        add(registerButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        String userType = userTypeCombo.getSelectedItem().toString();
        String name = nameField.getText();

        if (registerUser(username, password, userType, name)) {
            JOptionPane.showMessageDialog(this, "User registered good");
        } else {
            JOptionPane.showMessageDialog(this, "Failed.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean registerUser(String username, String password, String userType, String name) {

        String hashedPassword = password;

        String sql = "INSERT INTO User (Username, Password, UserType, Name) VALUES (?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalManagement",
                "root",
                "root12345");
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, hashedPassword);
            stmt.setString(3, userType);
            stmt.setString(4, name);
            int result = stmt.executeUpdate();
            return result > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Register register = new Register();
            register.setVisible(true);
        });
    }
}

