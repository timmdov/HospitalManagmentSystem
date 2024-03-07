import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Patient extends JFrame {
    private JMenuBar menuBar;
    private JMenu appointmentMenu, searchDoctorMenu, bookMeetingMenu, cancelMeetingMenu;
    private JMenuItem filterAppointment, searchByExpertise, searchByAvailability;
    private JMenuItem logout;

    public Patient() {
        setTitle("Patient Dashboard - Hospital Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        menuBar = new JMenuBar();




        appointmentMenu = new JMenu("Appointments");
        filterAppointment = new JMenuItem("Filter Appointment");


        appointmentMenu.add(filterAppointment);

        searchDoctorMenu = new JMenu("Search Doctor");
        searchByExpertise = new JMenuItem("Search by expertise");


        searchByAvailability = new JMenuItem("Search by Availability");
        searchDoctorMenu.add(searchByExpertise);
        searchDoctorMenu.add(searchByAvailability);

        bookMeetingMenu = new JMenu("Book an appointment");

        cancelMeetingMenu = new JMenu("Cancel an appointment");

        logout = new JMenuItem("Logout");

        menuBar.add(appointmentMenu);
        menuBar.add(searchDoctorMenu);
        menuBar.add(bookMeetingMenu);
        menuBar.add(cancelMeetingMenu);
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
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Nurse().setVisible(true);
        });
    }
}
