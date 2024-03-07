import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Nurse extends JFrame {
    private JMenuBar menuBar;
    private JMenu patientMenu, scheduleMenu;
    private JMenuItem viewPatients, viewSchedule;
    private JMenuItem logout;

    public Nurse() {
        setTitle("Nurse Dashboard - Hospital Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        menuBar = new JMenuBar();

        patientMenu = new JMenu("Patients");
        viewPatients = new JMenuItem("View Patients");


        patientMenu.add(viewPatients);

        scheduleMenu = new JMenu("Schedule");
        viewSchedule = new JMenuItem("View Schedule");
        scheduleMenu.add(viewSchedule);

        logout = new JMenuItem("Logout");

        menuBar.add(patientMenu);
        menuBar.add(scheduleMenu);
        menuBar.add(logout);

        setJMenuBar(menuBar);

        setupActionListeners();
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
            new Nurse().setVisible(true);
        });
    }
}
