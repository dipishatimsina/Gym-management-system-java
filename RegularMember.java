package programming;

/**
 * RegularMember class extending GymMember, representing a regular gym member with additional features.
 * Tracks plan type, attendance, and eligibility for upgrades.
 * 
 * Author: Dipisha Timsina
 */
class RegularMember extends GymMember {
    private final int attendanceLimit = 30;  // Maximum attendance before member becomes eligible for upgrade
    private boolean isEligibleForUpgrade;    // Eligibility flag for upgrade
    private String removalReason;            // Reason for member removal/reset
    private String referralSource;           // Source that referred this member
    private String plan;                     // Current subscription plan
    private double price;                    // Price associated with the current plan

    /**
     * Constructor for initializing a RegularMember object.
     */
    public RegularMember(int id, String name, String location, String phone, String email, String gender, String DOB, String membershipStartDate, String referralSource) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.isEligibleForUpgrade = false;
        this.plan = "basic";           // Default plan on registration
        this.price = 6500;             // Default price for basic plan
        this.referralSource = referralSource;
        this.removalReason = "";
    }

    /**
     * Marks attendance and updates loyalty points.
     * Checks if member is now eligible for a plan upgrade.
     */
    @Override
    public void markAttendance() {
        attendance++;                  // Increase attendance count
        loyaltyPoints += 5;            // Award 5 loyalty points per visit
        if (attendance >= attendanceLimit) {
            isEligibleForUpgrade = true;
        }
    }

    /**
     * Returns the price for a specific plan type.
     */
    public double getPlanPrice(String plan) {
        switch (plan.toLowerCase()) {
            case "basic": return 6500;
            case "standard": return 12500;
            case "deluxe": return 18500;
            default: return -1;  // Invalid plan input
        }
    }

    /**
     * Attempts to upgrade the member to a new plan.
     */
    public String upgradePlan(String newPlan) {
        double newPrice = getPlanPrice(newPlan);
        if (newPrice == -1) return "Invalid plan selected.";
        if (!isEligibleForUpgrade) return "Not eligible for an upgrade.";
        if (this.plan.equalsIgnoreCase(newPlan)) return "Already subscribed to this plan.";

        this.plan = newPlan;        // Update plan
        this.price = newPrice;      // Update price
        return "Plan upgraded successfully.";
    }

    /**
     * Reverts the member to default regular member state and records removal reason.
     */
    public void revertRegularMember(String removalReason) {
        super.resetMember();            // Reset attendance, loyalty, status
        this.isEligibleForUpgrade = false;
        this.plan = "basic";            // Reset to default plan
        this.price = 6500;              // Reset to default price
        this.removalReason = removalReason;
    }

    /**
     * Displays all member information including plan details and removal reason if applicable.
     */
    @Override
    public void display() {
        super.display();
        System.out.println("Plan: " + plan);
        System.out.println("Price: " + price);
        if (!removalReason.isEmpty())
            System.out.println("Removal Reason: " + removalReason);
    }
}
