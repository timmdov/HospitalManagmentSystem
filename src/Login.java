import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.sql.*;

public class Login extends JFrame {

    private JTextField userText;
    private JPasswordField passwordText;
    private JButton loginButton;

    public Login() {
        setTitle("Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Username:"));
        userText = new JTextField(20);
        add(userText);

        add(new JLabel("Password:"));
        passwordText = new JPasswordField(20);
        add(passwordText);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userText.getText();
                String password = new String(passwordText.getPassword());

                String userType = validateLogin(username, password);
                if (userType != null) {
                    switch (userType) {
                        case "Admin":
                            new Admin().setVisible(true);
                            break;
                        case "Doctor":
                            new Doctor().setVisible(true);
                            break;
                        case "Nurse":
                            new Nurse().setVisible(true);
                            break;
                        case "Patient":
                            new Patient().setVisible(true);
                            break;
                    }
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid");
                }
            }
        });
        add(loginButton);
    }

    private String validateLogin(String username, String password) {
        try {
            String hashedPassword = hashPassword(password);

            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/HospitalManagementSystemTwo",
                    "root",
                    "root12345");
            String sql = "SELECT UserType FROM User WHERE Username = ? AND Password = ?";
            PreparedStatement pst = conn.prepareStatement(sql);
            pst.setString(1, username);
            pst.setString(2, hashedPassword);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getString("UserType");
            }

            conn.close();
        } catch (SQLException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(password.getBytes(StandardCharsets.UTF_8));
        byte[] digest = md.digest();
        return String.format("%064x", new BigInteger(1, digest));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }
}
