package control;

import model.Student;
import model.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentController {

    public boolean addStudent(Student student) {
        String query = "INSERT INTO students (student_id, user_id, enrollment_no, department) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, student.getStudentId());
            stmt.setInt(2, student.getUserId());
            stmt.setString(3, student.getEnrollmentNo());
            stmt.setString(4, student.getDepartment());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error adding student: " + e.getMessage());
            return false;
        }
    }

    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("student_id"),
                        rs.getInt("user_id"),
                        rs.getString("enrollment_no"),
                        rs.getString("department")
                );
                students.add(student);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching students: " + e.getMessage());
        }

        return students;
    }
}
