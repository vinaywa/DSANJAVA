package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {
    private static final String INSERT_ATTENDANCE =
            "INSERT INTO attendance (student_id, date, status, marked_by) VALUES (?, ?, ?, ?)";

    private static final String SELECT_BY_STUDENT_ID =
            "SELECT * FROM attendance WHERE student_id = ?";

    // Insert attendance
    public boolean markAttendance(Attendance attendance) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_ATTENDANCE)) {

            ps.setInt(1, attendance.getStudentId());
            ps.setDate(2, attendance.getDate());
            ps.setString(3, attendance.getStatus());
            ps.setInt(4, attendance.getMarkedBy());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
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
            System.out.println("Error fetching attendance: " + e.getMessage());
        }
        return attendanceList;
    }
}
