import javax.swing.*;
import java.awt.*;
import java.awt.Component.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdministratorMain extends JFrame {
    JButton logoutBtn;
    JButton cancelBookEvents;
    JButton viewEvents;
    JButton editEvents;
    JButton newButton;
    JButton cancelEvents;
    DefaultTableModel tableModel;
    DefaultTableModel tableModel2;
    UserRegistration reg;
    Object[] rowData = {};
    Object[] rowBookData = {};
    JButton deleteButton;
    JLabel event_ID;
    JLabel student_ID;
    JLabel eventTime;
    JLabel eventLocation;
    JTextField event_IDField;
    JTextField student_IDField;
    JTextField eventTimeField;
    JTextField eventLocationField;
    JButton cancelBookEvent;
    JButton searchButton;
    int rowNo = -1;

    AdministratorMain(String uniID) {
        Font font1 = new Font("SansSerif", Font.PLAIN, 17);
        reg = new UserRegistration();

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1, 2));
        // mainPanel.setBorder(BorderFactory.createTitledBorder("Main Panel"));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(2, 0));
        tablePanel.setBorder(BorderFactory.createTitledBorder("Table Panel"));
        // tablePanel.add(new JLabel("All events"));

        // Table for working with data
        JTable table = new JTable();
        Object[] columnNames = { "Event id", "Name", "Date", "Time", "Location" };

        tableModel = new DefaultTableModel();
        tableModel.setColumnIdentifiers(columnNames);
        table.setModel(tableModel);

        // to make data to table uneditable
        table.setDefaultEditor(Object.class, null);

        // ScrollPane to add the table to
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("All events"));
        tablePanel.add(scrollPane);

        try {
            ResultSet resultSet = reg.getData();
            while (resultSet.next()) {
                rowData = new Object[] { resultSet.getString(1), resultSet.getString(2), resultSet.getString(3),
                        resultSet.getString(4), resultSet.getString(5) };

                tableModel.addRow(rowData);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    rowNo = table.getSelectedRow();
                    event_IDField.setText((String) tableModel.getValueAt(rowNo, 0));

                } catch (NullPointerException ex) {

                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }
        });

        tablePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));

        // tablePanel.add(new JLabel("Events that you are part of"));

        // Table for working with data
        JTable table2 = new JTable();
        Object[] columnNames2 = { "Event ID", "University ID", "Event name", "Date", "Time", "Location" };

        tableModel2 = new DefaultTableModel();
        tableModel2.setColumnIdentifiers(columnNames2);
        table2.setModel(tableModel2);

        // to make data to table uneditable
        table2.setDefaultEditor(Object.class, null);

        // ScrollPane to add the table to
        JScrollPane scrollPane2 = new JScrollPane(table2);
        scrollPane2.setBorder(BorderFactory.createTitledBorder("All bookings"));
        tablePanel.add(scrollPane2);

        try {
            ResultSet resultBookSet = reg.getAllBookingData();
            while (resultBookSet.next()) {
                rowBookData = new Object[] { resultBookSet.getString(1), resultBookSet.getString(2),
                        resultBookSet.getString(3), resultBookSet.getString(4), resultBookSet.getString(5),
                        resultBookSet.getString(6) };

                tableModel2.addRow(rowBookData);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // for showing values on the input boxes when a table data is clicked upon
        table2.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    rowNo = table2.getSelectedRow();
                    event_IDField.setText((String) tableModel2.getValueAt(rowNo, 0));

                } catch (NullPointerException ex) {

                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }
        });

        tablePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 2));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Book Events"));

        event_ID = new JLabel("Event ID");
        event_ID.setFont(font1);

        student_ID = new JLabel("Student ID");
        student_ID.setFont(font1);

        event_IDField = new JTextField();
        event_IDField.setFont(font1);

        student_IDField = new JTextField();
        student_IDField.setFont(font1);

        inputPanel.add(event_ID);
        inputPanel.add(event_IDField);
        // inputPanel.add(student_ID);
        // inputPanel.add(student_IDField);
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel());

        deleteButton = new JButton("Cancel event");
        deleteButton.setFont(font1);
        deleteButton.setBackground(Color.BLACK);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);

        inputPanel.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rowNo == -1) {
                    JOptionPane.showMessageDialog(null, "No row selected :( ", "Could not cancel event!",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    // int delete_id = Integer.parseInt((String) tableModel.getValueAt(rowNo, 0));
                    int event_id_cancel = Integer.parseInt((String) tableModel.getValueAt(rowNo, 0));
                    // reg.delete(delete_id);
                    reg.delete(event_id_cancel);
                    new AdministratorMain(uniID);
                    dispose();
                }
            }
        });

        searchButton = new JButton("Search events");
        searchButton.setFont(font1);
        searchButton.setBackground(Color.BLACK);
        searchButton.setForeground(Color.WHITE);
        searchButton.setFocusPainted(false);

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane optionPane = new JOptionPane();

                int found = 0;

                try {
                    String query = (optionPane.showInputDialog(null, "What event are you searching for? "))
                            .toLowerCase();
                    String main_query = query.replaceAll("\\s+", "");

                    int count = tableModel.getRowCount();

                    int result_row = -1;

                    for (int i = 0; i < count; i++) {
                        String name = ((String) tableModel.getValueAt(i, 1)).toLowerCase();

                        if (name.equals(main_query)) {
                            found = 1;
                            result_row = i;
                            String found_name = (String) tableModel.getValueAt(result_row, 1);
                            String found_date = (String) tableModel.getValueAt(result_row, 2);
                            String found_time = (String) tableModel.getValueAt(result_row, 3);
                            String found_location = (String) tableModel.getValueAt(result_row, 4);

                            JOptionPane.showMessageDialog(null,
                                    "Event : " + found_name + "\n" + "Date: " + found_date + "\n" + "Time : "
                                            + found_time + "\n" + "Location : " + found_location,
                                    "Successfully Found", JOptionPane.INFORMATION_MESSAGE);

                        }
                    }
                    if (result_row == -1) {
                        JOptionPane.showMessageDialog(null, "We could not find the event!", "Action Failed",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NullPointerException ex) {
                    if (found == 0) {
                        JOptionPane.showMessageDialog(null, "We could not find the event!", "Action Failed",
                                JOptionPane.ERROR_MESSAGE);
                    }
                    found = 0;
                }

            }
        });

        inputPanel.add(searchButton);

        cancelBookEvent = new JButton("Cancel booking");
        cancelBookEvent.setFont(font1);
        cancelBookEvent.setBackground(Color.BLACK);
        cancelBookEvent.setForeground(Color.WHITE);
        cancelBookEvent.setFocusPainted(false);

        inputPanel.add(cancelBookEvent);
        cancelBookEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String getEventID = event_IDField.getText();
                String getStudentID = (String) tableModel2.getValueAt(rowNo, 1);

                // String getStudentID = student_IDField.getText();

                if (getEventID.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Some fields are empty :( ", "Could not book event",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    // System.out.println(getEventID + " " + uniID);
                    reg.cancel(getEventID, getStudentID);

                    // deleteButton.doClick();
                    new AdministratorMain(uniID);
                    dispose();
                    // System.out.println(event_name + " " + event_date + " " + event_time + " "
                    // +
                    // event_location);

                }

            }
        });

        logoutBtn = new JButton("Logout");
        logoutBtn.setFont(font1);
        logoutBtn.setBackground(Color.BLACK);
        logoutBtn.setForeground(Color.WHITE);
        logoutBtn.setFocusPainted(false);

        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EEMS();
                dispose();
            }
        });

        inputPanel.add(logoutBtn);

        mainPanel.add(tablePanel);
        mainPanel.add(inputPanel);
        add(mainPanel);
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
