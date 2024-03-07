import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoomView extends JFrame {
    private JTable roomTable;

    public RoomView() {
        setTitle("View Rooms");
        setSize(400, 300);

        roomTable = new JTable();
        add(new JScrollPane(roomTable));

        showRooms();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void showRooms() {
        String jdbcURL = "jdbc:mysql://localhost:3306/HospitalManagementSystemTwo";
        String dbUsername = "root";
        String dbPassword = "root12345";

        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Room ID", "Department Type"}, 0);
        roomTable.setModel(tableModel);

        try (Connection connection = DriverManager.getConnection(jdbcURL, dbUsername, dbPassword);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Room")) {

            while (resultSet.next()) {
                int roomId = resultSet.getInt("RoomID");
                String departmentType = resultSet.getString("DepartmentType");
                tableModel.addRow(new Object[]{roomId, departmentType});
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RoomView().setVisible(true));
    }
}
