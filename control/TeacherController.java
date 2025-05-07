package control;

import model.Teacher;
import model.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeacherController {

    public boolean addTeacher(Teacher teacher) {
        String query = "INSERT INTO teachers (teacher_id, user_id, subject) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, teacher.getTeacherId());
            stmt.setInt(2, teacher.getUserId());
            stmt.setString(3, teacher.getSubject());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error adding teacher: " + e.getMessage());
            return false;
        }
    }

    public List<Teacher> getAllTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        String query = "SELECT * FROM teachers";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Teacher teacher = new Teacher(
                        rs.getInt("teacher_id"),
                        rs.getInt("user_id"),
                        rs.getString("subject")
                );
                teachers.add(teacher);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching teachers: " + e.getMessage());
        }

        return teachers;
    }
}
