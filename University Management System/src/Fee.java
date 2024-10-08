import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Fee {
    
    private String feeId;
    private String studentId;
    private double amount;
    private Date dueDate;
    private String status;
    private String feeType; 
    private double penalty;
    private Date paidDate;
    private String paymentMethod; 
    private String feeDescription;
    private ArrayList<Fee> fees;
    public Fee() {

    }
    public Fee(String feeId, String studentId, double amount, Date dueDate, String feeType, String feeDescription) {
        this.feeId = feeId;
        this.studentId = studentId;
        this.amount = amount;
        this.dueDate = dueDate;
        this.feeType = feeType;
        this.feeDescription = feeDescription;
        this.status = "Unpaid";
        this.penalty = 0.0;
        this.fees = new ArrayList<>();
    }
    public void addPaymentMethod(String method) {
        this.paymentMethod = method;
    }

    public void removePaymentMethod() {
        this.paymentMethod = null;
    }

    public String checkPaymentStatus() {
        return this.status;
    }

    public String getFeeId() {
        return feeId;
    }

    public void setFeeId(String feeId) {
        this.feeId = feeId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public Date getPaidDate() {
        return paidDate;
    }

    public void setPaidDate(Date paidDate) {
        this.paidDate = paidDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getFeeDescription() {
        return feeDescription;
    }

    public void setFeeDescription(String feeDescription) {
        this.feeDescription = feeDescription;
    }
    public void addFee(Fee fee) {
        fees.add(fee);
        System.out.println("Fee " + fee.feeId + " added for student " + fee.studentId);
    }
    public void sortFeesByAmount() {
        Collections.sort(fees, Comparator.comparingDouble(Fee::getAmount));
        System.out.println("Fees sorted by amount.");
    }
    public int findFeeById(String feeId) {
        sortFeesByAmount(); 
        int left = 0;
        int right = fees.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int result = fees.get(mid).getFeeId().compareTo(feeId);
            if (result == 0) return mid; 
            if (result < 0) left = mid + 1;
            else right = mid - 1;
        }
        return -1; 
    }
    
    public double findTotalFeesPaidByStudent(String studentId) {
        double totalFees = 0.0;
        for (Fee fee : fees) {
            if (fee.getStudentId().equals(studentId) && fee.getStatus().equals("Paid")) {
                totalFees += fee.getAmount();
            }
        }
        return totalFees;
    }
    public String generateFeeDetails() {
        return "Fee ID: " + this.feeId + "\nStudent ID: " + this.studentId + "\nAmount: " + this.amount +
               "\nDue Date: " + this.dueDate + "\nStatus: " + this.status + "\nType: " + this.feeType +
               "\nPenalty: " + this.penalty + "\nDescription: " + this.feeDescription;
    }
    public void applyPenalty(double penaltyAmount) {
        this.penalty += penaltyAmount;
    }
    public void markAsPaid(String paymentMethod) {
        this.status = "Paid";
        this.paidDate = new Date(); 
        this.paymentMethod = paymentMethod;
    }
    public void sendReminder() {
        if (!status.equals("Paid")) {
            System.out.println("Reminder: Fee is due. Amount: " + amount + ", Due Date: " + dueDate);
        }
    }
    public void viewFeeDetails() {
        System.out.println(generateFeeDetails());
    }
    public void updateDueDate(Date newDueDate) {
        this.dueDate = newDueDate;
    }

    public void calculatePenalty() {
        long daysOverdue = (new Date().getTime() - dueDate.getTime()) / (1000 * 60 * 60 * 24);
        if (daysOverdue > 0) {
            this.penalty = daysOverdue * 5; // Assuming $5 penalty per overdue day
        } else {
            this.penalty = 0;
        }
    }
}
