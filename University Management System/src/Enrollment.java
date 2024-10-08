import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class Enrollment {

   
    private String enrollmentId;
    private String studentId;
    private String courseId;
    private Date enrollmentDate;
    private String status; 
    private double grade; 
    private int credits;
    private String term; 
    private String remarks;
    private Date completionDate;
    private ArrayList<Enrollment> enrollments;

    public Enrollment(String enrollmentId, String studentId, String courseId, Date enrollmentDate, String term) {
        this.enrollmentId = enrollmentId;
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.status = "Enrolled";
        this.term = term;
        this.credits = 0;
        this.grade = 0.0;
        this.remarks = "";
        this.enrollments = new ArrayList<>();
    }

    public String getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }
    

    public void completeCourse(double finalGrade) {
        this.grade = finalGrade;
        this.status = "Completed";
        this.completionDate = new Date(); 
        System.out.println("Enrollment " + enrollmentId + " for course " + courseId + " completed with grade: " + finalGrade);
    }

    public void dropCourse() {
        this.status = "Dropped";
        System.out.println("Enrollment " + enrollmentId + " for course " + courseId + " has been dropped.");
    }

     public void sortEnrollmentsByGrade() {
        Collections.sort(enrollments, Comparator.comparingDouble(Enrollment::getGrade));
        System.out.println("Enrollments sorted by grade.");
    }

    public int findEnrollmentByStudentId(String studentId) {
        sortEnrollmentsByGrade();
        int left = 0;
        int right = enrollments.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int result = enrollments.get(mid).getStudentId().compareTo(studentId);
            if (result == 0) return mid; 
            if (result < 0) left = mid + 1;
            else right = mid - 1;
        }
        return -1;
    }

    public ArrayList<Enrollment> findTopNStudents(int n) {
        sortEnrollmentsByGrade(); 
        ArrayList<Enrollment> topStudents = new ArrayList<>();
        for (int i = 0; i < Math.min(n, enrollments.size()); i++) {
            topStudents.add(enrollments.get(enrollments.size() - 1 - i)); 
        }
        return topStudents;
    }

    public void addRemark(String remark) {
        this.remarks += remark + "\n";
        System.out.println("Remark added for enrollment " + enrollmentId + ": " + remark);
    }

    public double calculateGPA() {
        if (grade >= 90) return 4.0; // A
        else if (grade >= 80) return 3.0; // B
        else if (grade >= 70) return 2.0; // C
        else if (grade >= 60) return 1.0; // D
        else return 0.0; // F
    }

    public void updateStatus(String newStatus) {
        this.status = newStatus;
        System.out.println("Status for enrollment " + enrollmentId + " updated to: " + newStatus);
    }

    public void registerCredits(int credits) {
        if (status.equals("Completed")) {
            this.credits = credits;
            System.out.println("Credits registered for enrollment " + enrollmentId + ": " + credits + " credits");
        } else {
            System.out.println("Cannot register credits. Course " + courseId + " has not been completed.");
        }
    }

    public void viewEnrollmentDetails() {
        System.out.println("Enrollment ID: " + enrollmentId +
                           "\nStudent ID: " + studentId +
                           "\nCourse ID: " + courseId +
                           "\nEnrollment Date: " + enrollmentDate +
                           "\nStatus: " + status +
                           "\nGrade: " + grade +
                           "\nCredits: " + credits +
                           "\nTerm: " + term +
                           "\nRemarks: " + remarks +
                           "\nCompletion Date: " + completionDate);
    }

    public boolean isActive() {
        return status.equals("Enrolled") || status.equals("Waitlisted");
    }

    public void transferToAnotherTerm(String newTerm) {
        this.term = newTerm;
        this.status = "Enrolled";
        this.grade = 0.0;
        this.completionDate = null;
        System.out.println("Enrollment " + enrollmentId + " transferred to term " + newTerm);
    }

    public void updateGrade(double newGrade) {
        if (status.equals("Completed")) {
            this.grade = newGrade;
            System.out.println("Grade updated for enrollment " + enrollmentId + ": " + newGrade);
        } else {
            System.out.println("Cannot update grade. Course " + courseId + " is not yet completed.");
        }
    }

}
