package control;

import model.Attendance;
import model.AttendanceDAO;

import java.sql.Date;
import java.util.List;

public class AttendanceController {
    private final AttendanceDAO attendanceDAO;

    public AttendanceController() {
        this.attendanceDAO = new AttendanceDAO();
    }

    // Method to mark attendance
    public boolean markAttendance(int studentId, Date date, String status, int markedBy) {
        Attendance attendance = new Attendance(0, studentId, date, status, markedBy);
        return attendanceDAO.markAttendance(attendance);
    }

    // Method to get all attendance records of a student
    public List<Attendance> getAttendanceByStudent(int studentId) {
        return attendanceDAO.getAttendanceByStudent(studentId);
    }
}
