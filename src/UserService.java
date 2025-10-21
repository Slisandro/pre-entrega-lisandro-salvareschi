import java.util.ArrayList;

public class UserService {
    private final ArrayList<User> users = new ArrayList<>();

    public UserService() {
        User admin = new User("Admin", "admin", "admin", "admin");
        User user = new User("User", "user", "user", "user");
        users.add(admin);
        users.add(user);
    }

    public void registerUser(String name, String username, String password) {
        for (User u : users) {
            if (u.username.equals(username)) {
                System.out.println("⚠️ Username already exists.");
                return;
            }
        }

        User newUser = new User(name, username, password, "user");
        users.add(newUser);
        System.out.println("✅ User registered successfully!\n");
    }

    // Login
    public User loginUser(String username, String password) {
        for (User u : users) {
            if (u.username.equals(username)) {
                if (u.checkPassword(password)) {
                    return u;
                } else {
                    System.out.println("❌ Incorrect password.");
                    return null;
                }
            }
        }
        System.out.println("❌ User not found.");
        return null;
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
