package control;

import model.User;
import control.UserController;

import java.util.Scanner;

public class UserRegistration {
    private final UserController userController;

    public UserRegistration() {
        this.userController = new UserController();
    }

    public void registerNewUser() {
        Scanner scanner = new Scanner(System.in);

        // Collect user information for registration
        System.out.println("Enter name:");
        String name = scanner.nextLine();

        System.out.println("Enter email:");
        String email = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        System.out.println("Enter role (e.g., Student, Teacher):");
        String role = scanner.nextLine();

        // Call register method from UserController
        boolean success = userController.registerUser(name, email, password, role);

        if (success) {
            System.out.println("Registration successful!");
        } else {
            System.out.println("Registration failed. Please try again.");
        }
    }
}
