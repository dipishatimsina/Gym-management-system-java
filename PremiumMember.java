package programming;

/**
 * PremiumMember class extending GymMember, representing a premium gym member with additional features.
 * Includes trainer assignment, payment tracking, and discount logic.
 * 
 * Author: Dipisha Timsina
 */
public class PremiumMember extends GymMember {
    private final double premiumCharge;   // Fixed premium membership fee
    private String personalTrainer;       // Assigned personal trainer
    private boolean isFullPayment;        // Flag for full payment status
    private double paidAmount;            // Amount paid by the member
    private double discountAmount;        // Discount earned after full payment

    /**
     * Constructor to initialize a PremiumMember with required details.
     */
    public PremiumMember(int id, String name, String location, String phone, String email,
                         String gender, String DOB, String membershipStartDate, String personalTrainer) {
        super(id, name, location, phone, email, gender, DOB, membershipStartDate);
        this.personalTrainer = personalTrainer;
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
        this.premiumCharge = 10000;     // Standard fee for premium plan
    }

    /**
     * Marks attendance specifically for a premium member.
     */
    @Override
    public void markAttendance() {
        System.out.println("Attendance marked for Premium Member: " + getName());
    }

    // Getter for personal trainer
    public String getPersonalTrainer() { return personalTrainer; }

    // Checks if full payment is done
    public boolean isFullPayment() { return isFullPayment; }

    // Returns the amount paid
    public double getPaidAmount() { return paidAmount; }

    // Returns any discount awarded
    public double getDiscountAmount() { return discountAmount; }

    /**
     * Allows member to pay towards the premium membership charge.
     * Ensures payment does not exceed the required amount.
     */
    public String payDueAmount(double amount) {
        if (isFullPayment) {
            return "Payment is done.";
        }

        this.paidAmount += amount;

        if (this.paidAmount > premiumCharge) {
            return "Payment exceeds the premium charge.";
        }

        if (this.paidAmount == premiumCharge) {
            this.isFullPayment = true;  // Mark as fully paid
        }

        double remainingAmount = premiumCharge - this.paidAmount;
        return "Payment successful. Remaining balance: " + remainingAmount;
    }

    /**
     * Calculates a 10% discount if the member has completed full payment.
     */
    public void calculateDiscount() {
        if (isFullPayment) {
            this.discountAmount = 0.10 * premiumCharge;
            System.out.println("Discount calculated: " + discountAmount);
        } else {
            System.out.println("No discount available.");
        }
    }

    /**
     * Reverts premium member to initial/default state.
     */
    public void revertPremiumMember() {
        super.resetMember();            // Reset attendance, loyalty, status
        this.personalTrainer = "";
        this.isFullPayment = false;
        this.paidAmount = 0;
        this.discountAmount = 0;
    }

    /**
     * Displays detailed premium member information, including payment and discount data.
     */
    @Override
    public void display() {
        super.display();
        System.out.println("Personal Trainer: " + personalTrainer);
        System.out.println("Paid Amount: " + paidAmount);
        System.out.println("Full Payment: " + isFullPayment);
        double remainingAmount = premiumCharge - paidAmount;
        System.out.println("Remaining Amount: " + remainingAmount);
        if (isFullPayment) {
            System.out.println("Discount Amount: " + discountAmount);
        }
    }
}
