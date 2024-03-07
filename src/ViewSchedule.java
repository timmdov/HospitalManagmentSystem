import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ViewSchedule extends JFrame {
    private JTable scheduleTable;
    private DefaultTableModel tableModel;

    public ViewSchedule() {
        setTitle("View Schedule");
        setSize(800, 400);
        setLayout(new BorderLayout());
        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(new String[]{"Schedule ID", "Doctor ID", "Nurse ID", "Room ID", "Start Time", "End Time", "Date"});
        scheduleTable = new JTable(tableModel);
        add(new JScrollPane(scheduleTable), BorderLayout.CENTER);
        showSchedules();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    private void showSchedules() {
        String jdbcURL = "jdbc:mysql://localhost:3306/HospitalManagementSystemTwo";
        String dbUsername = "root";
        String dbPassword = "root12345";

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);
             Statement statement = connection.createStatement()) {

            String sql = "SELECT * FROM Schedule";
            ResultSet resultSet = statement.executeQuery(sql);

            tableModel.setRowCount(0);

            while (resultSet.next()) {
                int scheduleID = resultSet.getInt("ScheduleID");
                int doctorID = resultSet.getInt("DoctorID");
                int nurseID = resultSet.getInt("NurseID");
                int roomID = resultSet.getInt("RoomID");
                String startTime = resultSet.getString("StartTime");
                String endTime = resultSet.getString("EndTime");
                String date = resultSet.getString("Date");

                tableModel.addRow(new Object[]{scheduleID, doctorID, nurseID, roomID, startTime, endTime, date});
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error");
        }
    }





    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ViewSchedule().setVisible(true);
        });
    }
}
