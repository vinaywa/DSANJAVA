import java.util.Scanner;
import control.UserController;
import control.UserRegistration;
import control.AttendanceController;
import control.UserLogin;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserController userController = new UserController();

        while (true) {
            // Display menu options to the user
            System.out.println("Welcome to the Student Attendance Management System");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Mark Attendance");
            System.out.println("4. View Attendance");
            System.out.println("5. Exit");
            System.out.print("Please choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline character

            switch (choice) {
                case 1:
                    // Login functionality
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    if (userController.login(username, password)) {
                        System.out.println("Login successful!");
                    } else {
                        System.out.println("Invalid credentials. Please try again.");
                    }
                    break;

                case 2:
                    // Register functionality (Assuming you have registration logic in UserController)
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    if (userController.register(regUsername, regPassword)) {
                        System.out.println("Registration successful!");
                    } else {
                        System.out.println("Registration failed. Please try again.");
                    }
                    break;

                case 3:
                    // Mark Attendance (Assuming attendance marking functionality is in AttendanceController)
                    System.out.print("Enter Student ID: ");
                    int studentId = scanner.nextInt();
                    if (userController.markAttendance(studentId)) {
                        System.out.println("Attendance marked successfully!");
                    } else {
                        System.out.println("Failed to mark attendance. Please try again.");
                    }
                    break;

                case 4:
                    // View Attendance (Assuming you have a method to view attendance in AttendanceController)
                    System.out.print("Enter Student ID to view attendance: ");
                    int viewStudentId = scanner.nextInt();
                    userController.viewAttendance(viewStudentId);
                    break;

                case 5:
                    System.out.println("Exiting system...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
