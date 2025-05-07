package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/attendance_system";
    private static final String USER = "root";
    private static final String PASSWORD = "Vinay@123";

    private DBConnection() {} // prevent instantiation

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Optional in modern JDBC
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found: " + e.getMessage());
        }
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/attendance_system", "root", "Vinay@123");
    }
}
