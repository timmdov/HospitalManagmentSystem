import javax.swing.*;
import java.awt.*;

public class WelcomePage extends JFrame {

    public WelcomePage() {
        setTitle("Welcome to Hospital Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("/welcomepage.jpeg"));
                Image backgroundImage = backgroundIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
            }
        };
        backgroundPanel.setLayout(new FlowLayout());
        backgroundPanel.setPreferredSize(new Dimension(400, 300));

        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");

        loginButton.addActionListener(e -> openLoginPage());
        registerButton.addActionListener(e -> openRegisterPage());

        backgroundPanel.add(loginButton);
        backgroundPanel.add(registerButton);

        setContentPane(backgroundPanel);

        pack();
        setLocationRelativeTo(null);
    }

    private void openLoginPage() {
        Login login = new Login();
        login.setVisible(true);
    }

    private void openRegisterPage() {
        Register register = new Register();
        register.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new WelcomePage().setVisible(true);
        });
    }
}
