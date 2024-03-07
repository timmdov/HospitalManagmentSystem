import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddRoom extends JFrame {
    private JTextField roomIDField;
    private JTextField departmentTypeField;
    private JButton addButton;

    public AddRoom() {
        setTitle("Add Room");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));

        roomIDField = new JTextField(20);
        departmentTypeField = new JTextField(20);
        addButton = new JButton("Add");

        add(new JLabel("Room ID:"));
        add(roomIDField);
        add(new JLabel("Department Type:"));
        add(departmentTypeField);
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addRoomToDatabase();
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addRoomToDatabase() {
        String roomID = roomIDField.getText();
        String departmentType = departmentTypeField.getText();

        String jdbcURL = "jdbc:mysql://localhost:3306/HospitalManagementSystemTwo";
        String dbUsername = "root";
        String dbPassword = "root12345";

        String sql = "INSERT INTO Room (RoomID, DepartmentType) VALUES (?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);
             PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setInt(1, Integer.parseInt(roomID));
            pst.setString(2, departmentType);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Room adde ");
            } else {
                JOptionPane.showMessageDialog(this, "Failed");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid roomID");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddRoom().setVisible(true));
    }
}
