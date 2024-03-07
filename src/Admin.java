import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Admin extends JFrame {
    private JMenuBar menuBar;
    private JMenu usersMenu, roomsMenu, appointmentsMenu, schedulesMenu, systemMenu;
    private JMenuItem addUser, viewUsers, addRoom, viewRooms, addAppointment, viewAppointments, addSchedule, viewSchedules, logout;

    public Admin() {
        setTitle("Admin Dashboard - Hospital Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initUI();
    }

    private void initUI() {
        menuBar = new JMenuBar();

        usersMenu = new JMenu("Users");
        addUser = new JMenuItem("Add User");
        viewUsers = new JMenuItem("View Users");
        usersMenu.add(addUser);
        usersMenu.add(viewUsers);


        roomsMenu = new JMenu("Rooms");
        addRoom = new JMenuItem("Add Room");
        viewRooms = new JMenuItem("View Rooms");
        roomsMenu.add(addRoom);
        roomsMenu.add(viewRooms);

        appointmentsMenu = new JMenu("Appointments");
        addAppointment = new JMenuItem("Add Appointment");
        viewAppointments = new JMenuItem("View Appointments");
        appointmentsMenu.add(addAppointment);
        appointmentsMenu.add(viewAppointments);

        schedulesMenu = new JMenu("Schedules");
        addSchedule = new JMenuItem("Add Schedule");
        viewSchedules = new JMenuItem("View Schedules");
        schedulesMenu.add(addSchedule);
        schedulesMenu.add(viewSchedules);

        systemMenu = new JMenu("System");
        logout = new JMenuItem("Logout");
        systemMenu.add(logout);

        menuBar.add(usersMenu);
        menuBar.add(roomsMenu);
        menuBar.add(appointmentsMenu);
        menuBar.add(schedulesMenu);
        menuBar.add(systemMenu);

        setJMenuBar(menuBar);

        setupActionListeners();
    }

    private void setupActionListeners() {
        addUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUser addUser = new AddUser();
                addUser.setVisible(true);
            }
        });

        addRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRoom addroom = new AddRoom();
                addRoom.setVisible(true);
            }
        });

        viewRooms.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RoomView viewrrom = new RoomView();
                viewrrom.setVisible(true);
            }
        });

        addSchedule.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSchedule addschedule = new AddSchedule();
                addschedule.setVisible(true);
            }
        });

        viewSchedules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewSchedule viewSchedule = new ViewSchedule();
                viewSchedule.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Admin().setVisible(true);
        });
    }
}
