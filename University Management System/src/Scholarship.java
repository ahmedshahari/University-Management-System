import java.util.LinkedList;

public class Scholarship {

    private String scholarshipId;
    private String scholarshipName;
    private double amount;
    private String eligibilityCriteria;
    private String deadline;
    private int duration;
    private String sponsor;
    private double gpaRequirement;
    private boolean renewable;
    private LinkedList<String> recipients;

    private LinkedList<Scholarship> scholarshipList;

    public Scholarship() {

    }
    public Scholarship(String scholarshipId, String scholarshipName, double amount, String eligibilityCriteria, 
                       String deadline, int duration, String sponsor, double gpaRequirement, boolean renewable) {
        this.scholarshipId = scholarshipId;
        this.scholarshipName = scholarshipName;
        this.amount = amount;
        this.eligibilityCriteria = eligibilityCriteria;
        this.deadline = deadline;
        this.duration = duration;
        this.sponsor = sponsor;
        this.gpaRequirement = gpaRequirement;
        this.renewable = renewable;
        this.recipients = new LinkedList<>();
        this.scholarshipList = new LinkedList<>();
    }

    public void addScholarship(Scholarship scholarship) {
        scholarshipList.add(scholarship);
        System.out.println("Scholarship " + scholarship.scholarshipName + " added successfully.");
    }

    public void addRecipient(String recipientName) {
        recipients.add(recipientName);
        System.out.println("Recipient " + recipientName + " added to scholarship " + scholarshipName);
    }

    public boolean removeRecipient(String recipientName) {
        return recipients.remove(recipientName);
    }

    public Scholarship findScholarshipById(String id) {
        Scholarship[] sortedScholarships = sortScholarshipsById();
        int index = binarySearchById(sortedScholarships, id, 0, sortedScholarships.length - 1);
        return index == -1 ? null : sortedScholarships[index];
    }

    private int binarySearchById(Scholarship[] scholarships, String id, int left, int right) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            int comparison = scholarships[mid].scholarshipId.compareTo(id);
            if (comparison == 0) {
                return mid;
            }
            if (comparison > 0) {
                return binarySearchById(scholarships, id, left, mid - 1);
            }
            return binarySearchById(scholarships, id, mid + 1, right);
        }
        return -1;
    }

    public Scholarship[] sortScholarshipsById() {
        Scholarship[] scholarshipsArray = scholarshipList.toArray(new Scholarship[0]);
        int n = scholarshipsArray.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (scholarshipsArray[j].scholarshipId.compareTo(scholarshipsArray[j + 1].scholarshipId) > 0) {
                    Scholarship temp = scholarshipsArray[j];
                    scholarshipsArray[j] = scholarshipsArray[j + 1];
                    scholarshipsArray[j + 1] = temp;
                }
            }
        }
        return scholarshipsArray;
    }

    public void printAllScholarships() {
        Scholarship[] sortedScholarships = sortScholarshipsById();
        System.out.println("List of Scholarships:");
        for (Scholarship scholarship : sortedScholarships) {
            System.out.println(scholarship);
        }
    }

    public int countScholarshipsBySponsor(String sponsorName) {
        int count = 0;
        for (Scholarship scholarship : scholarshipList) {
            if (scholarship.sponsor.equalsIgnoreCase(sponsorName)) {
                count++;
            }
        }
        return count;
    }

    public Scholarship[] sortScholarshipsByGPA() {
        Scholarship[] scholarshipsArray = scholarshipList.toArray(new Scholarship[0]);
        int n = scholarshipsArray.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (scholarshipsArray[j].gpaRequirement > scholarshipsArray[j + 1].gpaRequirement) {
                    Scholarship temp = scholarshipsArray[j];
                    scholarshipsArray[j] = scholarshipsArray[j + 1];
                    scholarshipsArray[j + 1] = temp;
                }
            }
        }
        return scholarshipsArray;
    }

    public LinkedList<Scholarship> getRenewableScholarships() {
        LinkedList<Scholarship> renewableList = new LinkedList<>();
        for (Scholarship scholarship : scholarshipList) {
            if (scholarship.renewable) {
                renewableList.add(scholarship);
            }
        }
        return renewableList;
    }


    public String getScholarshipId() { return scholarshipId; }
    public void setScholarshipId(String scholarshipId) { this.scholarshipId = scholarshipId; }

    public String getScholarshipName() { return scholarshipName; }
    public void setScholarshipName(String scholarshipName) { this.scholarshipName = scholarshipName; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getEligibilityCriteria() { return eligibilityCriteria; }
    public void setEligibilityCriteria(String eligibilityCriteria) { this.eligibilityCriteria = eligibilityCriteria; }

    public String getDeadline() { return deadline; }
    public void setDeadline(String deadline) { this.deadline = deadline; }

    public int getDuration() { return duration; }
    public void setDuration(int duration) { this.duration = duration; }

    public String getSponsor() { return sponsor; }
    public void setSponsor(String sponsor) { this.sponsor = sponsor; }

    public double getGpaRequirement() { return gpaRequirement; }
    public void setGpaRequirement(double gpaRequirement) { this.gpaRequirement = gpaRequirement; }

    public boolean isRenewable() { return renewable; }
    public void setRenewable(boolean renewable) { this.renewable = renewable; }

    public LinkedList<String> getRecipients() { return recipients; }
    public void setRecipients(LinkedList<String> recipients) { this.recipients = recipients; }

    @Override
    public String toString() {
        return "Scholarship ID: " + scholarshipId + ", Name: " + scholarshipName + 
               ", Amount: " + amount + ", GPA Requirement: " + gpaRequirement +
               ", Renewable: " + renewable + ", Sponsor: " + sponsor + 
               ", Recipients: " + recipients;
    }
}
