package programming;

/**
 * Abstract class representing a gym member with common attributes and methods.
 * This class serves as the base for different types of gym members (e.g., regular, premium).
 * 
 * @author Dipisha Timsina
 */
public abstract class GymMember {
    // Unique identifier for the gym member
    protected int id;

    // Member's full name
    protected String name;

    // Location/address of the member
    protected String location;

    // Contact phone number
    protected String phone;

    // Email address of the member
    protected String email;

    // Gender of the member
    protected String gender;

    // Date of birth of the member
    protected String DOB;

    // Date when the membership started
    protected String membershipStartDate;

    // Number of times the member has attended
    protected int attendance;

    // Accumulated loyalty points
    protected double loyaltyPoints;

    // Whether the member is currently active
    protected boolean activeStatus;

    /**
     * Constructor to initialize a GymMember with personal and membership details.
     * Attendance and loyaltyPoints are initialized to 0, and membership is inactive by default.
     */
    public GymMember(int id, String name, String location, String phone, String email,
                     String gender, String DOB, String membershipStartDate) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.phone = phone;
        this.email = email;
        this.gender = gender;
        this.DOB = DOB;
        this.membershipStartDate = membershipStartDate;
        this.attendance = 0; // Initial attendance count is 0
        this.loyaltyPoints = 0; // Initial loyalty points is 0
        this.activeStatus = false; // Member is inactive by default
    }

    /**
     * Gets the gender of the member.
     * @return gender as String
     */
    public String getGender() {
        return gender;
    }

    /**
     * Gets the name of the member.
     * @return name as String
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the ID of the member.
     * @return member ID as int
     */
    public int getId() {
        return id;
    }

    /**
     * Abstract method to mark attendance.
     * Implementation must be provided by subclasses.
     */
    public abstract void markAttendance();

    /**
     * Checks if the member's membership is active.
     * @return true if active, false otherwise
     */
    public boolean isActive() {
        return activeStatus;
    }

    /**
     * Activates the member's membership.
     */
    public void activateMembership() {
        activeStatus = true;
    }

    /**
     * Deactivates the member's membership if it is active.
     * Prints a message if the membership is already inactive.
     */
    public void deactivateMembership() {
        if (activeStatus) {
            activeStatus = false; // Deactivate if currently active
        } else {
            // Inform user that membership is already inactive
            System.out.println("Membership is already inactive.");
        }
    }

    /**
     * Resets the member's status, attendance, and loyalty points.
     * Useful for clearing data when reusing a member instance.
     */
    public void resetMember() {
        activeStatus = false;
        attendance = 0;
        loyaltyPoints = 0;
    }

    /**
     * Displays all relevant member information to the console.
     */
    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Location: " + location);
        System.out.println("Phone: " + phone);
        System.out.println("Email: " + email);
        System.out.println("Gender: " + gender);
        System.out.println("DOB: " + DOB);
        System.out.println("Membership Start Date: " + membershipStartDate);
        System.out.println("Attendance: " + attendance);
        System.out.println("Loyalty Points: " + loyaltyPoints);
        System.out.println("Active Status: " + activeStatus);
    }
}
