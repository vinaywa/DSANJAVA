package control;

import model.User;
import model.UserDAO;

public class UserController {
    private final UserDAO userDAO;

    public UserController() {
        this.userDAO = new UserDAO();
    }

    public boolean registerUser(String name, String email, String password, String role) {
        User user = new User(name, email, password, role);
        return userDAO.addUser(user);
    }

    public User getUserByEmail(String email) {
        return userDAO.getUserByEmail(email);
    }
}
