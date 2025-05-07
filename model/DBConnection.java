package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/attendance_system";
    private static final String USER = "root"; // or 'sams_user' if you created one
    private static final String PASSWORD = "Vinay@123"; // change this

    private static Connection connection = null;

    // Private constructor to prevent instantiation
    private DBConnection() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                // Load JDBC Driver (optional in modern versions)
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✅ Connected to MySQL!");
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println("❌ Failed to connect to DB: " + e.getMessage());
            }
        }
        return connection;
    }
}
