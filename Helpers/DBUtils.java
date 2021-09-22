package Helpers;

import java.sql.*;

public class DBUtils {
    public static Connection getdbConnection() throws SQLException {
        String connectionString = "jdbc:mysql://" + Config.dbHost + ":" + Config.dbPort + "/" + Config.dbName;

        //DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

        return DriverManager.getConnection(connectionString, Config.dbUser, Config.dbPass);
    }
}