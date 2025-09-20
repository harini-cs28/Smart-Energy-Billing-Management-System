public class Appliance {
    private static int applianceIdCounter = 1;
    private int applianceId;
    private String applianceName;
    private double powerRating; // in Watts
    private double usageHours; // per day

    public Appliance(String applianceName, double powerRating, double usageHours) {
        this.applianceId = applianceIdCounter++;
        this.applianceName = applianceName;
        this.powerRating = powerRating;
        this.usageHours = usageHours;
    }

    // Getters
    public int getApplianceId() { return applianceId; }
    public String getApplianceName() { return applianceName; }
    public double getPowerRating() { return powerRating; }
    public double getUsageHours() { return usageHours; }

    // Setters
    public void setApplianceName(String applianceName) { this.applianceName = applianceName; }
    public void setPowerRating(double powerRating) { this.powerRating = powerRating; }
    public void setUsageHours(double usageHours) { this.usageHours = usageHours; }

    // Calculate daily units
    public double calculateDailyUnits() {
        return (powerRating * usageHours) / 1000.0;
    }

    // Calculate monthly units (assuming 30 days)
    public double calculateMonthlyUnits() {
        return calculateDailyUnits() * 30;
    }

    @Override
    public String toString() {
        return "Appliance ID: " + applianceId + ", Name: " + applianceName + ", Power Rating: " + powerRating + "W, Usage Hours: " + usageHours;
    }
}
