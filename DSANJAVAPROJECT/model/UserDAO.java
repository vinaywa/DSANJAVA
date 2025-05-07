package model;

import java.sql.*;

public class UserDAO {
    private static final String INSERT_USER = "INSERT INTO users (name, email, password, role) VALUES (?, ?, ?, ?)";
    private static final String SELECT_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";

    public boolean addUser(User user) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_USER)) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getRole());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding user: " + e.getMessage());
            return false;
        }
    }

    public User getUserByEmail(String email) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_USER_BY_EMAIL)) {

            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String emailFromDB = rs.getString("email");
                    String password = rs.getString("password");
                    String role = rs.getString("role");

                    if (role == null) {
                        role = "User";
                    }

                    return new User(id, name, emailFromDB, password, role);
                } else {
                    System.out.println("No user found with the provided email.");
                    return null;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching user by email: " + e.getMessage());
            return null;
        }
    }
}
