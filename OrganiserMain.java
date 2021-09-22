import javax.swing.*;
import java.awt.*;
import java.awt.Component.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrganiserMain extends JFrame {
    JButton logoutBtn;
    JButton postEvents;
    JButton viewEvents;
    JButton editEvents;
    JButton newButton;
    JButton cancelEvents;
    DefaultTableModel tableModel;
    UserRegistration reg;
    Object[] rowData = {};
    JButton deleteButton;
    JLabel eventName;
    JLabel eventDate;
    JLabel eventTime;
    JLabel eventLocation;
    JTextField eventNameField;
    JTextField eventDateField;
    JTextField eventTimeField;
    JTextField eventLocationField;
    JButton postEvent;
    JButton updateButton;
    int rowNo = -1;

    OrganiserMain() {
        Font font1 = new Font("SansSerif", Font.PLAIN, 17);
        reg = new UserRegistration();

        JPanel mainPanel = new JPanel();
        ///main panel to contain all other panels
        mainPanel.setLayout(new GridLayout(1, 2));
        mainPanel.setBorder(BorderFactory.createTitledBorder("Main Panel"));

        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new GridLayout(1, 0));
        tablePanel.setBorder(BorderFactory.createTitledBorder("Table Panel"));
        
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
        tablePanel.add(scrollPane);

        //the datas on the jtable are extracted from the database
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

        // for showing values on the input boxes when a table data is clicked upon
        table.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    rowNo = table.getSelectedRow();
                    eventNameField.setText((String) tableModel.getValueAt(rowNo, 1));
                    eventDateField.setText((String) tableModel.getValueAt(rowNo, 2));
                    eventTimeField.setText((String) tableModel.getValueAt(rowNo, 3));
                    eventLocationField.setText((String) tableModel.getValueAt(rowNo, 4));

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

        tablePanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5)); //padding on 3 sides

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(7, 2)); //7 rows 2 columns
        inputPanel.setBorder(BorderFactory.createTitledBorder("inputPanel"));

        eventName = new JLabel("Event Name");
        eventName.setFont(font1);

        eventDate = new JLabel("Event Date");
        eventDate.setFont(font1);

        eventTime = new JLabel("Event Time");
        eventTime.setFont(font1);

        eventLocation = new JLabel("Location");
        eventLocation.setFont(font1);

        eventNameField = new JTextField();
        eventNameField.setFont(font1);

        eventDateField = new JTextField();
        eventDateField.setFont(font1);

        eventTimeField = new JTextField();
        eventTimeField.setFont(font1);

        eventLocationField = new JTextField();
        eventLocationField.setFont(font1);

        inputPanel.add(eventName);
        inputPanel.add(eventNameField);
        inputPanel.add(eventDate);
        inputPanel.add(eventDateField);
        inputPanel.add(eventTime);
        inputPanel.add(eventTimeField);
        inputPanel.add(eventLocation);
        inputPanel.add(eventLocationField);
        inputPanel.add(new JLabel());
        inputPanel.add(new JLabel()); //for the extra spaces

        inputPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        deleteButton = new JButton("Delete");
        deleteButton.setFont(font1);
        deleteButton.setBackground(Color.BLACK);
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);

        inputPanel.add(deleteButton);
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (rowNo == -1) {
                    JOptionPane.showMessageDialog(null, "No row selected :( ", "Could not delete event!",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    int delete_id = Integer.parseInt((String) tableModel.getValueAt(rowNo, 0));
                    reg.delete(delete_id);
                    new OrganiserMain();
                    dispose();
                }
            }
        });

        updateButton = new JButton("Update");
        updateButton.setFont(font1);
        updateButton.setBackground(Color.BLACK);
        updateButton.setForeground(Color.WHITE);
        updateButton.setFocusPainted(false);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (rowNo == -1) {
                    JOptionPane.showMessageDialog(null, "No row selected :( ", "Could not update event!",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    int update_id = Integer.parseInt((String) tableModel.getValueAt(rowNo, 0));
                    String event_name = eventNameField.getText();
                    String event_date = eventDateField.getText();
                    String event_time = eventTimeField.getText();
                    String event_location = eventLocationField.getText();

                    if (event_name.trim().isEmpty() || event_date.trim().isEmpty() || event_time.trim().isEmpty()
                            || event_location.trim().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Some fields are empty :( ", "Could not update event",
                                JOptionPane.ERROR_MESSAGE);
                    } else {
                        reg.update(event_name, event_date, event_time, event_location, update_id);
                        new OrganiserMain();
                        dispose();

                    }

                }
            }
        });

        inputPanel.add(updateButton);

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

        postEvent = new JButton("Post");
        postEvent.setFont(font1);
        postEvent.setBackground(Color.BLACK);
        postEvent.setForeground(Color.WHITE);
        postEvent.setFocusPainted(false);

        inputPanel.add(postEvent);
        postEvent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String event_name = eventNameField.getText();
                String event_date = eventDateField.getText();
                String event_time = eventTimeField.getText();
                String event_location = eventLocationField.getText();

                if (event_name.trim().isEmpty() || event_date.trim().isEmpty() || event_time.trim().isEmpty()
                        || event_location.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Some fields are empty :( ", "Could not post event",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    reg.post(event_name, event_date, event_time, event_location);
                    // deleteButton.doClick();
                    new OrganiserMain();
                    dispose();
                    // System.out.println(event_name + " " + event_date + " " + event_time + " " +
                    // event_location);

                }

            }
        });

        mainPanel.add(tablePanel);
        mainPanel.add(inputPanel);
        add(mainPanel);
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public static void main(String[] args) {
        new OrganiserMain();
    }
}
