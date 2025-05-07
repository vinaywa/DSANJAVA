package control;

import model.User;
import java.util.Scanner;

public class UserLogin {
    private final UserController userController;

    public UserLogin() {
        this.userController = new UserController();
    }

    public void loginUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter email:");
        String email = scanner.nextLine();

        System.out.println("Enter password:");
        String password = scanner.nextLine();

        User user = userController.getUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful! Welcome " + user.getName() + " (" + user.getRole() + ")");
            // You can return the user or store the session info if needed
        } else {
            System.out.println("Invalid email or password.");
        }
    }
}
