package control;

import model.Attendance;
import model.User;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class AttendanceApp {
    private final AttendanceController attendanceController;
    private final UserController userController;

    public AttendanceApp() {
        this.attendanceController = new AttendanceController();
        this.userController = new UserController();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter email to login:");
        String email = scanner.nextLine();
        User user = userController.getUserByEmail(email);

        if (user == null) {
            System.out.println("User not found. Please register first.");
            return;
        }

        System.out.println("Welcome, " + user.getName() + " (" + user.getRole() + ")");

        if (user.getRole().equalsIgnoreCase("Teacher")) {
            // Mark attendance
            System.out.println("Enter student ID to mark attendance for:");
            int studentId = scanner.nextInt();
            scanner.nextLine(); // consume newline

            System.out.println("Enter date (YYYY-MM-DD):");
            String dateStr = scanner.nextLine();
            Date date = Date.valueOf(dateStr);

            System.out.println("Enter status (Present/Absent):");
            String status = scanner.nextLine();

            boolean marked = attendanceController.markAttendance(studentId, date, status, user.getId());

            if (marked) {
                System.out.println("Attendance marked successfully.");
            } else {
                System.out.println("Failed to mark attendance.");
            }

        } else if (user.getRole().equalsIgnoreCase("Student")) {
            // View attendance
            List<Attendance> records = attendanceController.getAttendanceByStudent(user.getId());
            if (records.isEmpty()) {
                System.out.println("No attendance records found.");
            } else {
                System.out.println("Your Attendance Records:");
                for (Attendance att : records) {
                    System.out.println(att.getDate() + " - " + att.getStatus() + " (Marked By: " + att.getMarkedBy() + ")");
                }
            }
        } else {
            System.out.println("Invalid role. Only Student or Teacher allowed.");
        }
    }
}
