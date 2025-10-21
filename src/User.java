public class User {
    String name;
    String username;
    String password;
    String role;

    public User(String name, String username, String password, String role) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public boolean checkPassword(String input) {
        return this.password.equals(input);
    }

    @Override
    public String toString() {
        return name + " - @" + username;
    }
}
