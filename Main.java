import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<User> users = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static User loggedInUser = null;
    private static final double TARIFF = 8.0; // ₹8 per kWh

    public static void main(String[] args) {
        while (true) {
            if (loggedInUser == null) {
                showMainMenu();
            } else {
                showUserMenu();
            }
        }
    }

    private static void showMainMenu() {
        System.out.println("\nSmart Energy Billing Management System");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        int choice = Integer.parseInt(scanner.nextLine());

        switch (choice) {
            case 1:
                registerUser();
                break;
            case 2:
                loginUser();
                break;
            case 3:
                System.out.println("Exiting... Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

    private static void registerUser() {
        System.out.print("Enter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        if (getUserByEmail(email) != null) {
            System.out.println("Email already registered. Try logging in.");
            return;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User newUser = new User(name, email, password);
        users.add(newUser);
        System.out.println("Registration successful! You can now login.");
    }

    private static void loginUser() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            loggedInUser = user;
            System.out.println("Login successful! Welcome, " + loggedInUser.getName());
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    private static User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equalsIgnoreCase(email)) {
                return user;
            }
        }
        return null;
    }

private static void showUserMenu() {
        System.out.println("\nUser Menu - " + loggedInUser.getName());
        System.out.println("1. Manage Appliances");
        System.out.println("2. View Consumption");
        System.out.println("3. Generate Bill");
        System.out.println("4. Update Profile");
        System.out.println("5. Logout");
        System.out.print("Choose an option: ");
        String input = scanner.nextLine();
        int choice;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            return;
        }

        switch (choice) {
            case 1:
                manageAppliancesMenu();
                break;
            case 2:
                viewConsumption();
                break;
            case 3:
                generateBill();
                break;
            case 4:
                updateProfile();
                break;
            case 5:
                loggedInUser = null;
                System.out.println("Logged out successfully.");
                break;
            default:
                System.out.println("Invalid choice. Try again.");
        }
    }

private static void manageAppliancesMenu() {
        while (true) {
            System.out.println("\nAppliance Management");
            System.out.println("1. Add Appliance");
            System.out.println("2. Update Appliance");
            System.out.println("3. Delete Appliance");
            System.out.println("4. List Appliances");
            System.out.println("5. Back");
            System.out.print("Choose an option: ");
            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    addAppliance();
                    break;
                case 2:
                    updateAppliance();
                    break;
                case 3:
                    deleteAppliance();
                    break;
                case 4:
                    listAppliances();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void addAppliance() {
        System.out.print("Enter appliance name: ");
        String name = scanner.nextLine();
        System.out.print("Enter power rating (Watts): ");
        double powerRating = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter usage hours per day: ");
        double usageHours = Double.parseDouble(scanner.nextLine());

        Appliance appliance = new Appliance(name, powerRating, usageHours);
        loggedInUser.getAppliances().add(appliance);
        System.out.println("Appliance added successfully.");
    }

    private static void updateAppliance() {
        listAppliances();
        System.out.print("Enter appliance ID to update: ");
        int id = Integer.parseInt(scanner.nextLine());
        Appliance appliance = getApplianceById(id);
        if (appliance == null) {
            System.out.println("Appliance not found.");
            return;
        }
        System.out.print("Enter new appliance name (" + appliance.getApplianceName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            appliance.setApplianceName(name);
        }
        System.out.print("Enter new power rating (" + appliance.getPowerRating() + "): ");
        String powerInput = scanner.nextLine();
        if (!powerInput.isEmpty()) {
            appliance.setPowerRating(Double.parseDouble(powerInput));
        }
        System.out.print("Enter new usage hours (" + appliance.getUsageHours() + "): ");
        String usageInput = scanner.nextLine();
        if (!usageInput.isEmpty()) {
            appliance.setUsageHours(Double.parseDouble(usageInput));
        }
        System.out.println("Appliance updated successfully.");
    }

    private static void deleteAppliance() {
        listAppliances();
        System.out.print("Enter appliance ID to delete: ");
        int id = Integer.parseInt(scanner.nextLine());
        Appliance appliance = getApplianceById(id);
        if (appliance == null) {
            System.out.println("Appliance not found.");
            return;
        }
        loggedInUser.getAppliances().remove(appliance);
        System.out.println("Appliance deleted successfully.");
    }

    private static void listAppliances() {
        ArrayList<Appliance> appliances = loggedInUser.getAppliances();
        if (appliances.isEmpty()) {
            System.out.println("No appliances found.");
            return;
        }
        System.out.println("Appliances:");
        for (Appliance appliance : appliances) {
            System.out.println(appliance);
        }
    }

    private static Appliance getApplianceById(int id) {
        for (Appliance appliance : loggedInUser.getAppliances()) {
            if (appliance.getApplianceId() == id) {
                return appliance;
            }
        }
        return null;
    }

    private static void viewConsumption() {
        ArrayList<Appliance> appliances = loggedInUser.getAppliances();
        if (appliances.isEmpty()) {
            System.out.println("No appliances found.");
            return;
        }
        System.out.println("\nConsumption Details:");
        double totalDailyUnits = 0;
        double totalMonthlyUnits = 0;
        for (Appliance appliance : appliances) {
            double dailyUnits = appliance.calculateDailyUnits();
            double monthlyUnits = appliance.calculateMonthlyUnits();
            totalDailyUnits += dailyUnits;
            totalMonthlyUnits += monthlyUnits;
            System.out.printf("Appliance: %s, Daily Units: %.2f kWh, Monthly Units: %.2f kWh%n",
                    appliance.getApplianceName(), dailyUnits, monthlyUnits);
        }
        System.out.printf("Total Daily Units: %.2f kWh%n", totalDailyUnits);
        System.out.printf("Total Monthly Units: %.2f kWh%n", totalMonthlyUnits);
    }

    private static void generateBill() {
        ArrayList<Appliance> appliances = loggedInUser.getAppliances();
        if (appliances.isEmpty()) {
            System.out.println("No appliances found. Cannot generate bill.");
            return;
        }
        double totalMonthlyUnits = 0;
        for (Appliance appliance : appliances) {
            totalMonthlyUnits += appliance.calculateMonthlyUnits();
        }
        double amountDue = totalMonthlyUnits * TARIFF;
        System.out.println("\nMonthly Bill Summary:");
        System.out.println("User: " + loggedInUser.getName());
        System.out.printf("Total Units Consumed: %.2f kWh%n", totalMonthlyUnits);
        System.out.printf("Amount Due: ₹%.2f%n", amountDue);
    }

    private static void updateProfile() {
        System.out.print("Enter new name (" + loggedInUser.getName() + "): ");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            loggedInUser.setName(name);
        }
        System.out.print("Enter new email (" + loggedInUser.getEmail() + "): ");
        String email = scanner.nextLine();
        if (!email.isEmpty()) {
            if (getUserByEmail(email) != null && !email.equalsIgnoreCase(loggedInUser.getEmail())) {
                System.out.println("Email already in use by another user.");
            } else {
                loggedInUser.setEmail(email);
            }
        }
        System.out.println("Profile updated successfully.");
    }
}
