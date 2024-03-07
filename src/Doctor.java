import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Doctor extends JFrame {
    private JMenuBar menuBar;
    private JMenu scheduleMenu, patientMenu, appointmentMenu;
    private JMenuItem viewSchedule, viewPatients, manageAppointments, logout;

    public Doctor() {
        setTitle("Doctor Dashboard - Hospital Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        menuBar = new JMenuBar();

        scheduleMenu = new JMenu("Schedule");
        viewSchedule = new JMenuItem("View Schedule");
        scheduleMenu.add(viewSchedule);

        patientMenu = new JMenu("Patients");
        viewPatients = new JMenuItem("View Patients");
        patientMenu.add(viewPatients);

        appointmentMenu = new JMenu("Appointments");
        manageAppointments = new JMenuItem("Manage Appointments");
        appointmentMenu.add(manageAppointments);

        logout = new JMenuItem("Logout");
        logout.addActionListener(e -> {
        });

        menuBar.add(scheduleMenu);
        menuBar.add(patientMenu);
        menuBar.add(appointmentMenu);
        menuBar.add(logout);

        setJMenuBar(menuBar);
    }

    private void setupActionListeners() {
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Login().setVisible(true);
            }
        });

        viewSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewSchedule viewSchedule = new ViewSchedule();
                viewSchedule.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Doctor().setVisible(true);
        });
    }
}
