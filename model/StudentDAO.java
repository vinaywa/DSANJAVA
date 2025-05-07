package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    private static final String INSERT_STUDENT = "INSERT INTO students (user_id, enrollment_no, department) VALUES (?, ?, ?)";
    private static final String SELECT_STUDENT_BY_ID = "SELECT * FROM students WHERE student_id = ?";

    public boolean addStudent(Student student) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_STUDENT)) {

            ps.setInt(1, student.getUserId());  // Foreign key to the user table
            ps.setString(2, student.getEnrollmentNo());
            ps.setString(3, student.getDepartment());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error adding student: " + e.getMessage());
            return false;
        }
    }

    public Student getStudentById(int studentId) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_STUDENT_BY_ID)) {

            ps.setInt(1, studentId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getInt("student_id"),
                            rs.getInt("user_id"),
                            rs.getString("enrollment_no"),
                            rs.getString("department")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching student by ID: " + e.getMessage());
        }
        return null;
    }
}

