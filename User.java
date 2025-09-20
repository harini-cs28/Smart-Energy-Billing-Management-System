import java.util.ArrayList;

public class User {
    private static int userIdCounter = 1;
    private int userId;
    private String name;
    private String email;
    private String password;
    private ArrayList<Appliance> appliances;

    public User(String name, String email, String password) {
        this.userId = userIdCounter++;
        this.name = name;
        this.email = email;
        this.password = password;
        this.appliances = new ArrayList<>();
    }

    // Getters
    public int getUserId() { return userId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public ArrayList<Appliance> getAppliances() { return appliances; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }

    // Update profile
    public void updateProfile(String name, String email) {
        this.name = name;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User ID: " + userId + ", Name: " + name + ", Email: " + email;
    }
}
