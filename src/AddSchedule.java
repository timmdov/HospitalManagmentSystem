import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddSchedule extends JFrame {
    private JTextField scheduleIDField, doctorIDField, nurseIDField, roomIDField, startTimeField, endTimeField, dateField;
    private JButton addButton;

    public AddSchedule() {
        setTitle("Add Schedule");
        setSize(400, 300);
        setLayout(new GridLayout(8, 2));

        scheduleIDField = new JTextField(20);
        doctorIDField = new JTextField(20);
        nurseIDField = new JTextField(20);
        roomIDField = new JTextField(20);
        startTimeField = new JTextField(20);
        endTimeField = new JTextField(20);
        dateField = new JTextField(20);
        addButton = new JButton("Add");

        add(new JLabel("Schedule ID:"));
        add(scheduleIDField);
        add(new JLabel("Doctor ID:"));
        add(doctorIDField);
        add(new JLabel("Nurse ID:"));
        add(nurseIDField);
        add(new JLabel("Room ID:"));
        add(roomIDField);
        add(new JLabel("Start Time:"));
        add(startTimeField);
        add(new JLabel("End Time:"));
        add(endTimeField);
        add(new JLabel("Date:"));
        add(dateField);
        add(addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addScheduleToDatabase();
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void addScheduleToDatabase() {
        String jdbcURL = "jdbc:mysql://localhost:3306/HospitalManagementSystemTwo";
        String dbUsername = "root";
        String dbPassword = "root12345";

        String sql = "INSERT INTO Schedule (ScheduleID, DoctorID, NurseID, RoomID, StartTime, EndTime, Date) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);
             PreparedStatement pst = connection.prepareStatement(sql)) {

            pst.setInt(1, Integer.parseInt(scheduleIDField.getText()));
            pst.setInt(2, Integer.parseInt(doctorIDField.getText()));
            pst.setInt(3, Integer.parseInt(nurseIDField.getText()));
            pst.setInt(4, Integer.parseInt(roomIDField.getText()));
            pst.setString(5, startTimeField.getText());
            pst.setString(6, endTimeField.getText());
            pst.setString(7, dateField.getText());

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Schedule added");
            } else {
                JOptionPane.showMessageDialog(this, "Failed");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "iNVALID id");
        }
    }

    public static void main(String[]args) {
        SwingUtilities.invokeLater(() -> {
            new AddSchedule().setVisible(true);
        });
    }
}
