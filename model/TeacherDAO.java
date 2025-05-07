package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherDAO {
    private static final String INSERT_TEACHER = "INSERT INTO teachers (user_id, subject) VALUES (?, ?)";
    private static final String SELECT_ALL = "SELECT * FROM teachers";

    public boolean addTeacher(Teacher teacher) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_TEACHER)) {

            ps.setInt(1, teacher.getUserId());
            ps.setString(2, teacher.getSubject());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error inserting teacher: " + e.getMessage());
            return false;
        }
    }

    public List<Teacher> getAllTeachers() {
        List<Teacher> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Teacher t = new Teacher(
                        rs.getInt("teacher_id"),
                        rs.getInt("user_id"),
                        rs.getString("subject")
                );
                list.add(t);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching teachers: " + e.getMessage());
        }
        return list;
    }
}
