import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class EEMS extends JFrame {
    JPanel panel;
    JLabel uniID;
    JTextField uniIDfield;
    JLabel password;
    JPasswordField passfield;
    JLabel userRoleLabel;
    JComboBox userRole;
    JButton loginBtn;
    JButton registerBtn;
    UserRegistration reg;

    public EEMS() {
        setTitle("EEMS");
        setSize(500, 300);
        setLocationRelativeTo(null);
        add(getPanel());
        reg = new UserRegistration();
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // loadData();
    }

    private JPanel getPanel() {
        Font font1 = new Font("SansSerif", Font.PLAIN, 17);
        Font font2 = new Font("Ariel", Font.PLAIN, 20);

        // the main container
        panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2));

        uniID = new JLabel("University ID");
        uniID.setFont(font1);
        panel.add(uniID);

        uniIDfield = new JTextField();
        uniIDfield.setFont(font1);
        panel.add(uniIDfield);

        password = new JLabel("Password");
        password.setFont(font1);
        panel.add(password);

        passfield = new JPasswordField();
        passfield.setFont(font1);
        panel.add(passfield);

        userRoleLabel = new JLabel("User Role");
        userRoleLabel.setFont(font1);
        panel.add(userRoleLabel);

        String[] roles = { "Student", "Organiser", "Administrator" };
        userRole = new JComboBox<String>(roles);
        userRole.setFont(font1);
        panel.add(userRole);

        panel.add(new JLabel());
        panel.add(new JLabel());

        loginBtn = new JButton("Login");
        loginBtn.setFont(font1);
        loginBtn.setBackground(Color.BLACK);
        loginBtn.setForeground(Color.WHITE);
        panel.add(loginBtn);

        registerBtn = new JButton("Register");
        registerBtn.setFont(font1);
        registerBtn.setBackground(Color.BLACK);
        registerBtn.setForeground(Color.WHITE);
        panel.add(registerBtn);

        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uni_id = uniIDfield.getText();
                String pass = String.valueOf(passfield.getPassword());
                String user_role = (String) userRole.getSelectedItem();
                // System.out.println(uni_id + " " + pass + " " + user_role);
                if (uni_id.trim().isEmpty() || pass.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Fields are empty :( ", "Could not log in",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    reg.verify(uni_id, pass, user_role);
                    dispose();
                }

            }
        });

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Register();
                dispose();
            }
        });

        return panel;

    }

    // public void loadData() {
    // try {
    // ResultSet resultSet = reg.getData();
    // while (resultSet.next()) {
    // System.out.println(resultSet.getString(2));
    // System.out.println(resultSet.getString(3));
    // }
    // } catch (SQLException ex) {
    // ex.printStackTrace();
    // }
    // }

    public static void main(String[] args) {
        new EEMS();
    }
}