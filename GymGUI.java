package programming;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;
/**
 * Represents a gym member with personal and membership details.
 * Contains information such as ID, name, contact details, membership plan,
 * attendance count, loyalty points, payments, and membership status.
 * 
 * Author: Dipisha Timsina
 */

class GymMember {
    String id, name, location, phone, email, gender, dob, startDate, referralSource, trainerName, removalReason, plan, status;
    int attendance = 0, loyaltyPoints = 0;
    double paidAmount = 0, planPrice = 0, premiumCharge = 0, discountAmount = 0;
    /**
     * Returns a formatted string displaying key member information.
     * 
     * @return A multi-line string with member details.
     */
    public String display() {
        return "ID: " + id + "\nName: " + name + "\nPlan: " + plan + "\nAttendance: " + attendance +
                "\nLoyalty Points: " + loyaltyPoints + "\nStatus: " + status + "\nPaid: $" + paidAmount;
    }
}

/**
 * Main GUI class for the Gym Management System.
 * Provides a Swing-based interface to add, update, and manage gym members.
 * Supports adding Regular and Premium members, attendance tracking, status changes,
 * plan upgrades, payment handling, and file persistence.
 * 
 */

  public class GymGUI {
    private JFrame frame;
    // Text fields for inputting member details
    private JTextField txtId, txtName, txtLocation, txtPhone, txtEmail, txtAttendance, txtLoyaltyPoints,
            txtReferralSource, txtPaidAmount, txtTrainerName, txtRemovalReason, txtPlanPrice, txtPremiumCharge, txtDiscountAmount;
            // Radio buttons for gender and status selection
    private JRadioButton maleButton, femaleButton, otherButton, activeButton, inactiveButton;
    // ComboBoxes for date selection (DOB and membership start date) and plans
    private JComboBox<String> dobDay, dobMonth, dobYear, startDay, startMonth, startYear, comboPlan;
    private ButtonGroup genderGroup, statusGroup;
    // List to store all gym members
    private ArrayList<GymMember> memberList = new ArrayList<>();
    private JTextArea displayArea;
    /**
     * Constructor initializes the GUI components and layout,
     * sets up event listeners for buttons.
     */

    public GymGUI() {
        frame = new JFrame("Gym Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);
        frame.setLayout(new BorderLayout());
        // Main panel with GridBagLayout for flexible layout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(mainPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Basic Info
        JPanel basicPanel = new JPanel(new GridBagLayout());
        basicPanel.setBorder(BorderFactory.createTitledBorder("Basic Information"));
        gbc.gridx = 0; gbc.gridy = 0;
        mainPanel.add(basicPanel, gbc);

        GridBagConstraints bg = new GridBagConstraints();
        bg.fill = GridBagConstraints.HORIZONTAL;
        bg.insets = new Insets(2, 2, 2, 2);

        txtId = new JTextField(15); addField(basicPanel, bg, 0, "Member ID:", txtId);
        txtName = new JTextField(15); addField(basicPanel, bg, 1, "Name:", txtName);
        txtLocation = new JTextField(15); addField(basicPanel, bg, 2, "Location:", txtLocation);
        txtPhone = new JTextField(15); addField(basicPanel, bg, 3, "Phone:", txtPhone);
        txtEmail = new JTextField(15); addField(basicPanel, bg, 4, "Email:", txtEmail);

        bg.gridx = 0; bg.gridy = 5;
        basicPanel.add(new JLabel("Gender:"), bg);
        maleButton = new JRadioButton("Male"); femaleButton = new JRadioButton("Female"); otherButton = new JRadioButton("Other");
        genderGroup = new ButtonGroup(); genderGroup.add(maleButton); genderGroup.add(femaleButton); genderGroup.add(otherButton);
        JPanel genderPanel = new JPanel(); genderPanel.add(maleButton); genderPanel.add(femaleButton); genderPanel.add(otherButton);
        bg.gridx = 1; basicPanel.add(genderPanel, bg);

        bg.gridx = 0; bg.gridy = 6;
        basicPanel.add(new JLabel("Date of Birth:"), bg);
        dobDay = new JComboBox<>(generateRange(1, 31)); dobMonth = new JComboBox<>(generateRange(1, 12)); dobYear = new JComboBox<>(generateRange(1950, 2024));
        JPanel dobPanel = new JPanel(); dobPanel.add(dobDay); dobPanel.add(dobMonth); dobPanel.add(dobYear);
        bg.gridx = 1; basicPanel.add(dobPanel, bg);

        bg.gridx = 0; bg.gridy = 7;
        basicPanel.add(new JLabel("Membership Start Date:"), bg);
        startDay = new JComboBox<>(generateRange(1, 31)); startMonth = new JComboBox<>(generateRange(1, 12)); startYear = new JComboBox<>(generateRange(2020, 2025));
        JPanel startPanel = new JPanel(); startPanel.add(startDay); startPanel.add(startMonth); startPanel.add(startYear);
        bg.gridx = 1; basicPanel.add(startPanel, bg);

        txtAttendance = new JTextField(15); addField(basicPanel, bg, 8, "Attendance:", txtAttendance);
        txtLoyaltyPoints = new JTextField(15); addField(basicPanel, bg, 9, "Loyalty Points:", txtLoyaltyPoints);

        bg.gridx = 0; bg.gridy = 10;
        basicPanel.add(new JLabel("Active Status:"), bg);
        activeButton = new JRadioButton("Active"); inactiveButton = new JRadioButton("Inactive");
        statusGroup = new ButtonGroup(); statusGroup.add(activeButton); statusGroup.add(inactiveButton);
        JPanel statusPanel = new JPanel(); statusPanel.add(activeButton); statusPanel.add(inactiveButton);
        bg.gridx = 1; basicPanel.add(statusPanel, bg);

        // Additional Info
        JPanel additionalPanel = new JPanel(new GridBagLayout());
        additionalPanel.setBorder(BorderFactory.createTitledBorder("Additional Information"));
        gbc.gridx = 1; gbc.gridy = 0;
        mainPanel.add(additionalPanel, gbc);

        GridBagConstraints ag = new GridBagConstraints();
        ag.fill = GridBagConstraints.HORIZONTAL;
        ag.insets = new Insets(2, 2, 2, 2);

        txtReferralSource = new JTextField(15); addField(additionalPanel, ag, 0, "Referral Source:", txtReferralSource);
        txtPaidAmount = new JTextField(15); addField(additionalPanel, ag, 1, "Paid Amount:", txtPaidAmount);
        txtTrainerName = new JTextField(15); addField(additionalPanel, ag, 2, "Trainer Name:", txtTrainerName);
        txtRemovalReason = new JTextField(15); addField(additionalPanel, ag, 3, "Removal Reason:", txtRemovalReason);

        ag.gridx = 0; ag.gridy = 4;
        additionalPanel.add(new JLabel("Plan (Regular Only):"), ag);
        comboPlan = new JComboBox<>(new String[]{"Basic", "Standard", "Deluxe"});
        ag.gridx = 1; additionalPanel.add(comboPlan, ag);

        txtPlanPrice = new JTextField(15); addField(additionalPanel, ag, 5, "Regular Plan Price:", txtPlanPrice);
        txtPremiumCharge = new JTextField(15); addField(additionalPanel, ag, 6, "Premium Charge:", txtPremiumCharge);
        txtDiscountAmount = new JTextField(15); addField(additionalPanel, ag, 7, "Discount Amount:", txtDiscountAmount);

        // Buttons
        JPanel buttonPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        gbc.gridx = 0; gbc.gridy = 1; gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);

        String[] btnLabels = {
                "Add Regular Member", "Add Premium Member", "Activate Membership", "Deactivate Membership",
                "Mark Attendance", "Upgrade Plan", "Calculate Discount", "Revert Regular",
                "Revert Premium", "Pay Due", "Display", "Clear Fields",
                "Save to File", "Read from File"
        };
        JButton[] buttons = new JButton[btnLabels.length];
        
        

        for (int i = 0; i < btnLabels.length; i++) {
            buttons[i] = new JButton(btnLabels[i]);
            buttonPanel.add(buttons[i]);
        }

        // Display Area
        displayArea = new JTextArea(8, 70);
        displayArea.setFont(new Font("Monospaced", Font.PLAIN, 13));
        displayArea.setEditable(false);
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        mainPanel.add(new JScrollPane(displayArea), gbc);

        // BUTTON FUNCTIONALITY
        buttons[0].addActionListener(e -> addMember("Regular"));
        buttons[1].addActionListener(e -> addMember("Premium"));
        buttons[2].addActionListener(e -> changeStatus("Active"));
        buttons[3].addActionListener(e -> changeStatus("Inactive"));
        buttons[4].addActionListener(e -> markAttendance());
        buttons[5].addActionListener(e -> upgradePlan());
        buttons[6].addActionListener(e -> calculateDiscount());
        buttons[7].addActionListener(e -> revertPlan("Regular"));
        buttons[8].addActionListener(e -> revertPlan("Premium"));
        buttons[9].addActionListener(e -> payDue());
        buttons[10].addActionListener(e -> displayMembers());
        buttons[11].addActionListener(e -> clearFields());
        buttons[12].addActionListener(e -> saveToFile());
        buttons[13].addActionListener(e -> readFromFile());

        frame.setVisible(true);
    }
    /**
     * Helper method to add a label and text field pair to a panel.
     *
     * @param panel The JPanel to add components to
     * @param gbc GridBagConstraints controlling the layout
     * @param y The vertical grid position
     * @param label The text for the JLabel
     * @param field The JTextField component
     */
    private void addField(JPanel panel, GridBagConstraints gbc, int y, String label, JTextField field) {
        gbc.gridx = 0; gbc.gridy = y;
        panel.add(new JLabel(label), gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }
     /**
     * Generates an array of strings representing a range of integers.
     * Useful for populating combo boxes for dates.
     * 
     * @param start Starting integer (inclusive)
     * @param end Ending integer (inclusive)
     * @return Array of strings from start to end
     */
    private String[] generateRange(int start, int end) {
        String[] values = new String[end - start + 1];
        for (int i = 0; i < values.length; i++) {
            values[i] = String.valueOf(start + i);
        }
        return values;
    }

private void addMember(String type) {
    String id = txtId.getText().trim();
    String name = txtName.getText().trim();
    // Check basic fields (common to all members)
    if (txtId.getText().trim().isEmpty() || 
        txtName.getText().trim().isEmpty() || 
        txtLocation.getText().trim().isEmpty() ||
        txtPhone.getText().trim().isEmpty() || 
        txtEmail.getText().trim().isEmpty()) {
        
        JOptionPane.showMessageDialog(frame, "Please fill in all basic information fields (ID, Name, Location, Phone, Email)", 
            "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    // Validate ID (only digits)
    if (!id.matches("\\d+")) {
        JOptionPane.showMessageDialog(frame, "Member ID must contain digits only.", "Invalid ID", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Validate Name (only letters and spaces)
    if (!name.matches("[a-zA-Z ]+")) {
        JOptionPane.showMessageDialog(frame, "Name must contain letters only.", "Invalid Name", JOptionPane.ERROR_MESSAGE);
        return;
    }
    // Check gender selection
    if (!maleButton.isSelected() && !femaleButton.isSelected() && !otherButton.isSelected()) {
        JOptionPane.showMessageDialog(frame, "Please select a gender", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Check date selections (remove the == 0 checks as they might not be accurate)
    if (dobDay.getSelectedItem() == null || dobMonth.getSelectedItem() == null || dobYear.getSelectedItem() == null ||
        startDay.getSelectedItem() == null || startMonth.getSelectedItem() == null || startYear.getSelectedItem() == null) {
        
        JOptionPane.showMessageDialog(frame, "Please select valid dates for both Date of Birth and Membership Start", 
            "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Type-specific validations
    if (type.equals("Regular") && txtReferralSource.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Referral source is required for regular members", 
            "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    if (type.equals("Premium") && txtTrainerName.getText().trim().isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Trainer name is required for premium members", 
            "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Numeric fields - set defaults if empty
    int attendance = 0;
    try {
        attendance = txtAttendance.getText().trim().isEmpty() ? 0 : Integer.parseInt(txtAttendance.getText().trim());
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(frame, "Attendance must be a number", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // Proceed with adding the member if validation passes
        GymMember m = new GymMember();
        m.id = txtId.getText();
        m.name = txtName.getText();
        m.location = txtLocation.getText();
        m.phone = txtPhone.getText();
        m.email = txtEmail.getText();
        m.gender = maleButton.isSelected() ? "Male" : (femaleButton.isSelected() ? "Female" : "Other");
        m.dob = dobYear.getSelectedItem() + "-" + dobMonth.getSelectedItem() + "-" + dobDay.getSelectedItem();
        m.startDate = startYear.getSelectedItem() + "-" + startMonth.getSelectedItem() + "-" + startDay.getSelectedItem();
        m.status = activeButton.isSelected() ? "Active" : "Inactive";
        m.attendance = Integer.parseInt(txtAttendance.getText().isEmpty() ? "0" : txtAttendance.getText());
        m.loyaltyPoints = Integer.parseInt(txtLoyaltyPoints.getText().isEmpty() ? "0" : txtLoyaltyPoints.getText());
        m.referralSource = txtReferralSource.getText();
        m.paidAmount = Double.parseDouble(txtPaidAmount.getText().isEmpty() ? "0" : txtPaidAmount.getText());
        m.trainerName = txtTrainerName.getText();
        m.removalReason = txtRemovalReason.getText();
        m.plan = comboPlan.getSelectedItem().toString();
        m.planPrice = Double.parseDouble(txtPlanPrice.getText().isEmpty() ? "0" : txtPlanPrice.getText());
        m.premiumCharge = type.equals("Premium") ? 50.0 : 0;
        m.discountAmount = 0;

        memberList.add(m);
        displayArea.setText(type + " Member added successfully!");

        // Show pop-up for Regular or Premium member
        if (type.equals("Regular")) {
            JOptionPane.showMessageDialog(frame, "Regular Member " + m.name + " added successfully!", "Regular Member Added", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(frame, "Premium Member " + m.name + " added successfully!", "Premium Member Added", JOptionPane.INFORMATION_MESSAGE);
        }
 }
   
 private void changeStatus(String status) {
    // Validation: Check if ID is entered
    if (txtId.getText().isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Please enter a Member ID to change the status.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Search for the member by ID
    for (GymMember m : memberList) {
        if (m.id.equals(txtId.getText())) {
            m.status = status;
            displayArea.setText("Status updated to " + status + " for " + m.name);

            // ✅ This is the missing popup line
            JOptionPane.showMessageDialog(frame,
                "Membership " + status.toLowerCase() + "d successfully for " + m.name,
                "Status Changed",
                JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }
    }

    displayArea.setText("Member not found.");
}

    
private void markAttendance() {
         // Validation: Check if ID is entered
        if (txtId.getText().isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Please enter a Member ID to mark attendance.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
     }

        // Search for the member by ID
        for (GymMember m : memberList) {
            if (m.id.equals(txtId.getText())) {
                m.attendance++;
                m.loyaltyPoints += 10;
                displayArea.setText("Attendance marked. Total: " + m.attendance);
                
                // Show pop-up
                JOptionPane.showMessageDialog(frame, "Attendance marked for " + m.name + ". Total: " + m.attendance, "Attendance Marked", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
     displayArea.setText("Member not found.");
      }

    private void upgradePlan() {
    if (txtId.getText().isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Please enter a Member ID to upgrade the plan.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    String newPlan = comboPlan.getSelectedItem().toString(); // Get selected plan

    for (GymMember m : memberList) {
        if (m.id.equals(txtId.getText())) {
            // Check if already subscribed
            if (m.plan.equalsIgnoreCase(newPlan)) {
                JOptionPane.showMessageDialog(frame, "Already subscribed to this plan.", "Plan Upgrade", JOptionPane.WARNING_MESSAGE);
                return;
            }

            // Pricing logic
            double newPrice = 0;
            switch (newPlan.toLowerCase()) {
                case "basic": newPrice = 6500; break;
                case "standard": newPrice = 12500; break;
                case "deluxe": newPrice = 18500; break;
                default:
                    JOptionPane.showMessageDialog(frame, "Invalid plan selected.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
            }

            m.plan = newPlan;
            m.planPrice = newPrice;

            displayArea.setText("Plan upgraded to " + newPlan + " for " + m.name);
            JOptionPane.showMessageDialog(frame, "Plan upgraded to " + newPlan + " for " + m.name, "Upgrade Successful", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
    }

    displayArea.setText("Member not found.");
}


private void revertPlan(String plan) {
        // Validation: Check if ID is entered
       if (txtId.getText().isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Please enter a Member ID to revert the plan.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
        }
        for (GymMember m : memberList) {
            if (m.id.equals(txtId.getText())) {
                m.plan = plan;
                m.premiumCharge = 0;
                displayArea.setText("Reverted to " + plan + " plan.");
                
                // Show pop-up
                JOptionPane.showMessageDialog(frame, "Reverted to " + plan + " plan for " + m.name, "Plan Reverted", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        displayArea.setText("Member not found.");
    }

    private void calculateDiscount() {
    if (txtId.getText().isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Please enter a Member ID to calculate the discount.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    for (GymMember m : memberList) {
        if (m.id.equals(txtId.getText())) {
            if (m.attendance >= 10) {
                m.discountAmount = 0.1 * (m.planPrice + m.premiumCharge);
                displayArea.setText("Discount of $" + m.discountAmount + " applied to " + m.name);
                JOptionPane.showMessageDialog(frame, "Discount calculated for " + m.name + ": $" + m.discountAmount, "Discount Applied", JOptionPane.INFORMATION_MESSAGE);
            } else {
                displayArea.setText("Not eligible for discount (less than 10 attendances).");
                JOptionPane.showMessageDialog(frame, "Discount not applicable. Attendance must be at least 10.", "No Discount", JOptionPane.WARNING_MESSAGE);
            }
            return;
        }
    }

    displayArea.setText("Member not found.");
}


private void payDue() {
        // Validation: Check if ID is entered
        if (txtId.getText().isEmpty()) {
        JOptionPane.showMessageDialog(frame, "Please enter a Member ID to pay due.", "Input Error", JOptionPane.ERROR_MESSAGE);
        return;
        }
        for (GymMember m : memberList) {
            if (m.id.equals(txtId.getText())) {
                double totalCost = m.planPrice + m.premiumCharge - m.discountAmount;
            m.paidAmount += totalCost;
            displayArea.setText("Payment of $" + totalCost + " received from " + m.name);
            JOptionPane.showMessageDialog(frame, "Payment complete. $" + totalCost + " added to " + m.name + "'s account.", "Payment Success", JOptionPane.INFORMATION_MESSAGE);
            return;
            }
        }
        displayArea.setText("Member not found.");
    }

    
private void displayMembers() {
    if (memberList.isEmpty()) {
        displayArea.setText("No members to display.");
        return;
    }

    StringBuilder sb = new StringBuilder("Member List:\n");
    for (GymMember m : memberList) {
        sb.append(m.display()).append("\n-------------------\n");
    }

    displayArea.setText(sb.toString());
}


private void clearFields() {
    JTextField[] fields = { txtId, txtName, txtLocation, txtPhone, txtEmail, txtAttendance,
            txtLoyaltyPoints, txtReferralSource, txtPaidAmount, txtTrainerName,
            txtRemovalReason, txtPlanPrice, txtPremiumCharge, txtDiscountAmount };

    for (JTextField field : fields) {
        field.setText("");
    }

    genderGroup.clearSelection();
    statusGroup.clearSelection();
    comboPlan.setSelectedIndex(0);
    dobDay.setSelectedIndex(0);
    dobMonth.setSelectedIndex(0);
    dobYear.setSelectedIndex(0);
    startDay.setSelectedIndex(0);
    startMonth.setSelectedIndex(0);
    startYear.setSelectedIndex(0);

    displayArea.setText("All fields cleared.");
}

private void saveToFile() {
    if (memberList.isEmpty()) {
        JOptionPane.showMessageDialog(frame, "No member data to save.", "No Data", JOptionPane.WARNING_MESSAGE);
        return;
    }
    try (PrintWriter writer = new PrintWriter(new FileWriter("MemberDetails.txt"))) {
        // Header row
        writer.printf("%-5s %-15s %-15s %-15s %-25s %-20s %-15s %-10s %-10s %-15s %-15s %-15s %-15s%n",
                "ID", "Name", "Location", "Phone", "Email", "Membership Start Date", "Plan", "Price",
                "Attendance", "Loyalty Points", "Active Status", "Full Payment", "Discount Amount", "Net Amount Paid");

        // Data rows
        for (GymMember m : memberList) {
            
            double netPaid = m.paidAmount;
            String fullPayment = (m instanceof PremiumMember) ? String.valueOf(((PremiumMember) m).isFullPayment()) : "N/A";
            double discount = (m instanceof PremiumMember) ? ((PremiumMember) m).getDiscountAmount() : 0.0;

            writer.printf("%-5s %-15s %-15s %-15s %-25s %-20s %-15s %-10.2f %-10d %-15d %-15s %-15s %-15.2f %-15.2f%n",
                    m.id, m.name, m.location, m.phone, m.email, m.startDate, m.plan, m.planPrice,
                    m.attendance, m.loyaltyPoints, m.status, fullPayment, discount, netPaid);
        }

        JOptionPane.showMessageDialog(frame, "Member details saved to MemberDetails.txt", "File Saved", JOptionPane.INFORMATION_MESSAGE);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(frame, "Error saving file: " + e.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void readFromFile() {
    try (BufferedReader reader = new BufferedReader(new FileReader("MemberDetails.txt"))) {
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            sb.append(line).append("\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        JFrame readFrame = new JFrame("Member Details from File");
        readFrame.setSize(900, 400);
        readFrame.add(scrollPane);
        readFrame.setLocationRelativeTo(frame);
        readFrame.setVisible(true);
    } catch (IOException e) {
        JOptionPane.showMessageDialog(frame, "Error reading file: " + e.getMessage(), "Read Error", JOptionPane.ERROR_MESSAGE);
    }
}



public static void main(String[] args) {
        SwingUtilities.invokeLater(GymGUI::new);
    }
}
