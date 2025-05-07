package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {
    private static final String INSERT_ATTENDANCE =
            "INSERT INTO attendance (student_id, date, status, marked_by) VALUES (?, ?, ?, ?)";

    private static final String SELECT_BY_STUDENT_ID =
            "SELECT * FROM attendance WHERE student_id = ?";

    private static final String CHECK_STUDENT_EXISTS =
            "SELECT 1 FROM students WHERE student_id = ?";

    private static final String CHECK_TEACHER_EXISTS =
            "SELECT 1 FROM teachers WHERE teacher_id = ?";

    // Insert attendance
    public boolean markAttendance(Attendance attendance) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement psInsert = conn.prepareStatement(INSERT_ATTENDANCE);
             PreparedStatement psCheckStudent = conn.prepareStatement(CHECK_STUDENT_EXISTS);
             PreparedStatement psCheckTeacher = conn.prepareStatement(CHECK_TEACHER_EXISTS)) {

            // Check if student exists
            psCheckStudent.setInt(1, attendance.getStudentId());
            ResultSet rsStudent = psCheckStudent.executeQuery();
            if (!rsStudent.next()) {
                System.out.println("❌ Student not found with ID: " + attendance.getStudentId());
                return false; // Student does not exist
            }

            // Check if teacher exists
            psCheckTeacher.setInt(1, attendance.getMarkedBy());
            ResultSet rsTeacher = psCheckTeacher.executeQuery();
            if (!rsTeacher.next()) {
                System.out.println("❌ Teacher not found with ID: " + attendance.getMarkedBy());
                return false; // Teacher does not exist
            }

            // Debugging: Log the values being inserted
            System.out.println("Inserting attendance: Student ID = " + attendance.getStudentId() +
                    ", Date = " + attendance.getDate() +
                    ", Status = " + attendance.getStatus() +
                    ", Marked By (Teacher ID) = " + attendance.getMarkedBy());

            // Insert attendance
            psInsert.setInt(1, attendance.getStudentId());
            psInsert.setDate(2, attendance.getDate());
            psInsert.setString(3, attendance.getStatus());
            psInsert.setInt(4, attendance.getMarkedBy());

            int rowsAffected = psInsert.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Attendance successfully marked.");
                return true;
            } else {
                System.out.println("❌ Failed to mark attendance.");
                return false;
            }
        } catch (SQLException e) {
            // Handle SQL error with a clear message
            System.out.println("Error inserting attendance: " + e.getMessage());
            return false;
        }
    }

    // Get attendance for a specific student
    public List<Attendance> getAttendanceByStudent(int studentId) {
        List<Attendance> attendanceList = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BY_STUDENT_ID)) {

            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                Date date = rs.getDate("date");
                String status = rs.getString("status");
                int markedBy = rs.getInt("marked_by");

                attendanceList.add(new Attendance(id, studentId, date, status, markedBy));
            }
        } catch (SQLException e) {
            // Handle SQL error with a clear message
            System.out.println("Error fetching attendance: " + e.getMessage());
        }
        return attendanceList;
    }
}
