import java.util.Scanner;
import control.UserController;
import control.AttendanceController;
import control.StudentController;
import control.TeacherController;
import model.User;
import model.Attendance;
import model.Student;
import model.Teacher;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserController userController = new UserController();
        AttendanceController attendanceController = new AttendanceController();
        StudentController studentController = new StudentController();
        TeacherController teacherController = new TeacherController();

        User loggedInUser = null;

        while (true) {
            System.out.println("\n--- Student Attendance Management System ---");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Mark Attendance (Teacher only)");
            System.out.println("4. View Attendance (Student only)");
            System.out.println("5. Add Student (Admin only)");
            System.out.println("6. View All Students (Admin only)");
            System.out.println("7. Add Teacher (Admin only)");
            System.out.println("8. View All Teachers (Admin only)");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    if (userController.login(email, password)) {
                        loggedInUser = userController.getUserByEmail(email);
                        System.out.println("✅ Login successful! Logged in as " + loggedInUser.getRole());
                    } else {
                        System.out.println("❌ Invalid credentials.");
                    }
                }

                case 2 -> {
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter role (student/teacher/admin): ");
                    String role = scanner.nextLine();

                    if (!role.equalsIgnoreCase("student") &&
                            !role.equalsIgnoreCase("teacher") &&
                            !role.equalsIgnoreCase("admin")) {
                        System.out.println("❌ Invalid role. Please choose from student, teacher, or admin.");
                        break;
                    }



                }

                case 3 -> {
                    if (loggedInUser == null || !loggedInUser.getRole().equalsIgnoreCase("teacher")) {
                        System.out.println("❌ Only logged-in teachers can mark attendance.");
                        break;
                    }

                    System.out.print("Enter student ID: ");
                    int studentId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter status (Present/Absent): ");
                    String status = scanner.nextLine();
                    java.sql.Date date = new java.sql.Date(System.currentTimeMillis());

                    if (attendanceController.markAttendance(studentId, date, status, loggedInUser.getId())) {
                        System.out.println("✅ Attendance marked.");
                    } else {
                        System.out.println("❌ Failed to mark attendance.");
                    }
                }

                case 4 -> {
                    if (loggedInUser == null || !loggedInUser.getRole().equalsIgnoreCase("student")) {
                        System.out.println("❌ Only logged-in students can view their attendance.");
                        break;
                    }

                    var records = attendanceController.getAttendanceByStudent(loggedInUser.getId());
                    if (records.isEmpty()) {
                        System.out.println("No attendance records found.");
                    } else {
                        System.out.println("--- Attendance Records ---");
                        for (Attendance att : records) {
                            System.out.printf("Date: %s | Status: %s | Marked By: %d%n",
                                    att.getDate(), att.getStatus(), att.getMarkedBy());
                        }
                    }
                }

                case 5 -> {
                    if (loggedInUser == null || !loggedInUser.getRole().equalsIgnoreCase("admin")) {
                        System.out.println("❌ Only admin can add a student.");
                        break;
                    }

                    System.out.print("Enter student ID: ");
                    int studentId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter user ID: ");
                    int userId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter enrollment number: ");
                    String enrollmentNo = scanner.nextLine();
                    System.out.print("Enter department: ");
                    String department = scanner.nextLine();

                    Student student = new Student(studentId, userId, enrollmentNo, department);
                    if (studentController.addStudent(student)) {
                        System.out.println("✅ Student added successfully.");
                    } else {
                        System.out.println("❌ Failed to add student.");
                    }
                }

                case 6 -> {
                    if (loggedInUser == null || !loggedInUser.getRole().equalsIgnoreCase("admin")) {
                        System.out.println("❌ Only admin can view all students.");
                        break;
                    }

                    var students = studentController.getAllStudents();
                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        System.out.println("--- All Students ---");
                        for (Student student : students) {
                            System.out.printf("ID: %d | User ID: %d | Enrollment No: %s | Department: %s%n",
                                    student.getStudentId(), student.getUserId(), student.getEnrollmentNo(), student.getDepartment());
                        }
                    }
                }

                case 7 -> {
                    if (loggedInUser == null || !loggedInUser.getRole().equalsIgnoreCase("admin")) {
                        System.out.println("❌ Only admin can add a teacher.");
                        break;
                    }

                    System.out.print("Enter teacher ID: ");
                    int teacherId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter user ID: ");
                    int userId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter subject: ");
                    String subject = scanner.nextLine();

                    Teacher teacher = new Teacher(teacherId, userId, subject);
                    if (teacherController.addTeacher(teacher)) {
                        System.out.println("✅ Teacher added successfully.");
                    } else {
                        System.out.println("❌ Failed to add teacher.");
                    }
                }

                case 8 -> {
                    if (loggedInUser == null || !loggedInUser.getRole().equalsIgnoreCase("admin")) {
                        System.out.println("❌ Only admin can view all teachers.");
                        break;
                    }

                    var teachers = teacherController.getAllTeachers();
                    if (teachers.isEmpty()) {
                        System.out.println("No teachers found.");
                    } else {
                        System.out.println("--- All Teachers ---");
                        for (Teacher teacher : teachers) {
                            System.out.printf("ID: %d | User ID: %d | Subject: %s%n",
                                    teacher.getTeacherId(), teacher.getUserId(), teacher.getSubject());
                        }
                    }
                }

                case 9 -> {
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                }

                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
}
