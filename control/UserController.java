package control;

import model.User;
import model.UserDAO;

public class UserController {
    private final UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO();
    }

    public boolean registerUser(String name, String email, String password, String role) {
        // Validate role
        if (!role.equalsIgnoreCase("student") &&
                !role.equalsIgnoreCase("teacher") &&
                !role.equalsIgnoreCase("admin")) {
            System.out.println("❌ Invalid role. Role must be student, teacher, or admin.");
            return false;
        }

        // Check for duplicate email
        if (userDAO.getUserByEmail(email) != null) {
            System.out.println("❌ Email already registered.");
            return false;
        }

        User user = new User(name, email, password, role.toLowerCase());
        return userDAO.addUser(user);
    }

    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }

    public boolean login(String email, String password) {
        User user = userDAO.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return true;
        }
        return false;
    }
}
