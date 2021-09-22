import java.sql.*;
import javax.swing.JOptionPane;
import Helpers.DBUtils;

public class UserRegistration {
    private Connection con;
    private String foundpass;

    public UserRegistration() {
        try {
            con = DBUtils.getdbConnection();
            System.out.println("Connected to database");
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "We're having trouble connecting to the database!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    ResultSet getData() {
        try {
            String select = "SELECT * FROM events";
            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "We're having trouble fetching data from the database!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    ResultSet getBookingData(String uniID) {
        try {
            String select = "select booking.event_id,event_name, event_date, event_time, event_location from booking inner join events on booking.event_id=events.event_id where student_id=?";
            PreparedStatement statement = con.prepareStatement(select);
            statement.setString(1, uniID);
            return statement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "We're having trouble fetching data from the database!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    ResultSet getAllBookingData() {
        try {
            String select = "select booking.event_id,student_id,event_name, event_date, event_time, event_location from booking inner join events on booking.event_id=events.event_id";
            PreparedStatement statement = con.prepareStatement(select);
            return statement.executeQuery();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "We're having trouble fetching data from the database!", "Error",
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }
    }

    void verify(String uniID, String password, String role) {
        try {
            String querypass = "SELECT password from data WHERE id= ? && role =? ";
            PreparedStatement statement = con.prepareStatement(querypass);
            statement.setString(1, uniID);
            statement.setString(2, role);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next() == false) {
                JOptionPane.showMessageDialog(null, "Cannot verify credentials :( ", "Could not log in",
                        JOptionPane.ERROR_MESSAGE);
                new EEMS();
            } else {
                do {
                    foundpass = resultSet.getString(1);

                    if (foundpass.equals(password)) {
                        System.out.println("Password match");

                        if (role.equals("Student")) {
                            new StudentMain(uniID);
                        } else if (role.equals("Organiser")) {
                            new OrganiserMain();
                        } else if (role.equals("Administrator")) {
                            new AdministratorMain(uniID);
                        }
                    }
                } while (resultSet.next());
            }

            {

            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "You cannot log in. :( ", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    void register(String uniID, String name, String password, String email, String role) {
        System.out.println(uniID + " " + name + " " + password + " " + email + " " + role);
        try {

            String insert = "insert into data(id,name,password,email,role) values(?,?,?,?,?) ";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setString(1, uniID);
            statement.setString(2, name);
            statement.setString(3, password);
            statement.setString(4, email);
            statement.setString(5, role);

            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "you are successfully registered! :) ", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "We're having trouble inserting data to the database!\n Possible Causes:\n -Duplication of university ID.\n -Database connectivity problem.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    void post(String event_name, String event_date, String event_time, String event_location) {
        try {
            String insert = "insert into events(event_name,event_date,event_time,event_location) values(?,?,?,?) ";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setString(1, event_name);
            statement.setString(2, event_date);
            statement.setString(3, event_time);
            statement.setString(4, event_location);

            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Event was successfully posted! :) ", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "We're having trouble inserting data to the database!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    void delete(int id) {

        try {
            String insert = "delete from events where event_id = ? ";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setInt(1, id);

            statement.executeUpdate();
            System.out.println("ID i have is " + id);

            JOptionPane.showMessageDialog(null, "Event was successfully deleted! :) ", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            statement.close();

        } catch (SQLIntegrityConstraintViolationException ex) {
            try {
                String insert = "delete from booking where event_id = ?";
                PreparedStatement statement = con.prepareStatement(insert);
                statement.setInt(1, id);
                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "Event was successfully deleted! :) ", "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                statement.close();

                try {
                    String insert1 = "delete from events where event_id = ? ";
                    PreparedStatement statement1 = con.prepareStatement(insert1);
                    statement1.setInt(1, id);

                    statement1.executeUpdate();
                    System.out.println("ID i have is " + id);

                    statement1.close();

                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "We're having trouble deleting data from the database!",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "We're having trouble cancelling the data!", "Error",
                        JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "We're having trouble deleting data from the database!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    void update(String event_name, String event_date, String event_time, String event_location, int id) {
        try {
            String update = "UPDATE events SET event_name=?,event_date=?,event_time=?,event_location=? WHERE event_id=?";
            PreparedStatement statement = con.prepareStatement(update);
            statement.setString(1, event_name);
            statement.setString(2, event_date);
            statement.setString(3, event_time);
            statement.setString(4, event_location);
            statement.setInt(5, id);

            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Event was successfully updated! :) ", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "We're having trouble updating data to the database!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    void bookEvent(String event_id, String student_id) {
        try {
            String insert = "insert into booking values(?,?) ";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setString(1, student_id);
            statement.setString(2, event_id);

            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Event was successfully booked! :) ", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "We're having trouble booking event in the database!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }

    }

    void cancel(String event_id, String student_id) {

        try {
            String insert = "delete from booking where event_id = ? && student_id=?";
            PreparedStatement statement = con.prepareStatement(insert);
            statement.setString(1, event_id);
            statement.setString(2, student_id);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Event was successfully cancelled! :) ", "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "We're having trouble cancelling the data!", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}