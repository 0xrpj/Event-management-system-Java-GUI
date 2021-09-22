import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Register extends JFrame {

    JPanel panel;
    JLabel uniID;
    JTextField uniIDfield;
    JTextField namefield;
    JLabel password;
    JLabel fullName;
    JLabel emailLabel;
    JTextField email;
    JPasswordField passfield;
    JLabel userRoleLabel;
    JComboBox userRole;
    JButton registerBtn;
    UserRegistration reg;
    JButton backBtn;

    Register() {
        Font font1 = new Font("SansSerif", Font.PLAIN, 17); 
        Font font2 = new Font("Ariel", Font.PLAIN, 20);

        panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2)); //7 rows and 2 columns

        fullName = new JLabel("Full Name");
        fullName.setFont(font1);
        panel.add(fullName);
        namefield = new JTextField();
        namefield.setFont(font1);
        panel.add(namefield);

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

        emailLabel = new JLabel("Email address: ");
        emailLabel.setFont(font1);
        panel.add(emailLabel);
        email = new JTextField();
        email.setFont(font1);
        panel.add(email);

        userRoleLabel = new JLabel("User Role");
        userRoleLabel.setFont(font1);
        panel.add(userRoleLabel);

        String[] roles = { "Student", "Organiser", "Administrator" };
        userRole = new JComboBox<String>(roles);
        userRole.setFont(font1);
        panel.add(userRole);

        panel.add(new JLabel());
        panel.add(new JLabel());

        backBtn = new JButton("Back");
        backBtn.setFont(font2);
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        panel.add(backBtn);

        registerBtn = new JButton("Register");
        registerBtn.setFont(font2);
        registerBtn.setBackground(Color.BLACK);
        registerBtn.setForeground(Color.WHITE);
        panel.add(registerBtn);

        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); //for the padding on all sides

        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String uni_id = uniIDfield.getText();
                String name = namefield.getText();
                String pass = String.valueOf(passfield.getPassword());
                String e_mail = email.getText();
                String user_role = (String) userRole.getSelectedItem();

                if (uni_id.trim().isEmpty() || name.trim().isEmpty() || pass.trim().isEmpty()
                        || e_mail.trim().isEmpty()) { //trim method is used to trim out whitespaces
                    JOptionPane.showMessageDialog(null, "All fields are required!");
                } else {
                    reg.register(uni_id, name, pass, e_mail, user_role);
                    backBtn.doClick();
                }
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EEMS();
                dispose();
            }
        });

        setTitle("Register");
        setSize(500, 340);
        add(panel);
        reg = new UserRegistration();
        setLocationRelativeTo(null); //for the window to appear on the middle
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //to completely close the application on exit
        setVisible(true); 
    }

}
