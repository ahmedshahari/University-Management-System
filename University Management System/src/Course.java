import java.util.ArrayList;
import java.util.HashMap;

public class Course {

    private String courseId;
    private String courseName;
    private int credits;
    private ArrayList<String> enrolledStudents;
    private HashMap<String, Integer> studentGrades;
    private int[] gradeArray;
    private String[] topicArray;
    private int maxStudents;
    
    public Course() {
        
    }
    public Course(String courseId, String courseName, int credits, int maxStudents) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credits = credits;
        this.maxStudents = maxStudents;
        this.enrolledStudents = new ArrayList<>();
        this.studentGrades = new HashMap<>();
        this.gradeArray = new int[maxStudents];
        this.topicArray = new String[maxStudents];
    }
    
    public boolean addStudent(String studentId) {
        if (enrolledStudents.size() < maxStudents) {
            enrolledStudents.add(studentId);
            System.out.println("Student " + studentId + " enrolled successfully.");
            return true;
        } else {
            System.out.println("Course is full. Cannot enroll more students.");
            return false;
        }
    }
    
    public boolean removeStudent(String studentId) {
        if (enrolledStudents.contains(studentId)) {
            enrolledStudents.remove(studentId);
            studentGrades.remove(studentId);
            System.out.println("Student " + studentId + " removed successfully.");
            return true;
        } else {
            System.out.println("Student " + studentId + " not found in course.");
            return false;
        }
    }

    public void addTopic(String topic) {
        for (int i = 0; i < topicArray.length; i++) {
            if (topicArray[i] == null) {
                topicArray[i] = topic;
                System.out.println("Topic " + topic + " added.");
                return;
            }
        }
        System.out.println("Topic list is full.");
    }
    
    public void sortStudentsByName() {
        for (int i = 0; i < enrolledStudents.size() - 1; i++) {
            for (int j = 0; j < enrolledStudents.size() - i - 1; j++) {
                if (enrolledStudents.get(j).compareTo(enrolledStudents.get(j + 1)) > 0) {
                    String temp = enrolledStudents.get(j);
                    enrolledStudents.set(j, enrolledStudents.get(j + 1));
                    enrolledStudents.set(j + 1, temp);
                }
            }
        }
    }

    public int findStudentById(String studentId) {
        sortStudentsByName();
        return binarySearch(enrolledStudents.toArray(new String[0]), studentId, 0, enrolledStudents.size() - 1);
    }
    
    private int binarySearch(String[] students, String studentId, int left, int right) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            if (students[mid].equals(studentId)) return mid;
            if (students[mid].compareTo(studentId) > 0) return binarySearch(students, studentId, left, mid - 1);
            return binarySearch(students, studentId, mid + 1, right);
        }
        return -1;
    }

    public void assignGrade(String studentId, int grade) {
        if (enrolledStudents.contains(studentId)) {
            studentGrades.put(studentId, grade);
            updateGradeArray();
            System.out.println("Grade " + grade + " assigned to student " + studentId);
        } else {
            System.out.println("Student " + studentId + " not found in the course.");
        }
    }

    public double calculateAverageGrade() {
        return calculateAverageRecursive(gradeArray, 0, studentGrades.size()) / (double) studentGrades.size();
    }

    private double calculateAverageRecursive(int[] grades, int index, int count) {
        if (index == count) return 0;
        return grades[index] + calculateAverageRecursive(grades, index + 1, count);
    }

    private void updateGradeArray() {
        int index = 0;
        for (String studentId : enrolledStudents) {
            if (studentGrades.containsKey(studentId)) {
                gradeArray[index++] = studentGrades.get(studentId);
            }
        }
    }

    public void sortGradesDescending() {
        for (int i = 0; i < studentGrades.size() - 1; i++) {
            for (int j = 0; j < studentGrades.size() - i - 1; j++) {
                if (gradeArray[j] < gradeArray[j + 1]) {
                    int temp = gradeArray[j];
                    gradeArray[j] = gradeArray[j + 1];
                    gradeArray[j + 1] = temp;
                }
            }
        }
    }

    public int findHighestGrade() {
        return findHighestRecursive(gradeArray, studentGrades.size());
    }

    private int findHighestRecursive(int[] grades, int count) {
        if (count == 1) return grades[0];
        int maxOfRest = findHighestRecursive(grades, count - 1);
        return Math.max(grades[count - 1], maxOfRest);
    }

    public void displayTopics() {
        System.out.println("Topics for " + courseName + ":");
        for (String topic : topicArray) {
            if (topic != null) {
                System.out.println("- " + topic);
            }
        }
    }

    public void displayStudents() {
        System.out.println("Enrolled students in " + courseName + ":");
        for (String studentId : enrolledStudents) {
            System.out.println("- " + studentId);
        }
    }

    public void findStudentsAboveGrade(int threshold) {
        System.out.println("Students with grades above " + threshold + ":");
        for (String studentId : studentGrades.keySet()) {
            if (studentGrades.get(studentId) > threshold) {
                System.out.println("- " + studentId);
            }
        }
    }

    // Getters and Setters
    public String getCourseId() { return courseId; }
    public void setCourseId(String courseId) { this.courseId = courseId; }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }

    public int getCredits() { return credits; }
    public void setCredits(int credits) { this.credits = credits; }

    public ArrayList<String> getEnrolledStudents() { return enrolledStudents; }
    public void setEnrolledStudents(ArrayList<String> enrolledStudents) { this.enrolledStudents = enrolledStudents; }

    public HashMap<String, Integer> getStudentGrades() { return studentGrades; }
    public void setStudentGrades(HashMap<String, Integer> studentGrades) { this.studentGrades = studentGrades; }

    public int[] getGradeArray() { return gradeArray; }
    public void setGradeArray(int[] gradeArray) { this.gradeArray = gradeArray; }

    public String[] getTopicArray() { return topicArray; }
    public void setTopicArray(String[] topicArray) { this.topicArray = topicArray; }

    public int getMaxStudents() { return maxStudents; }
    public void setMaxStudents(int maxStudents) { this.maxStudents = maxStudents; }

    @Override
    public String toString() {
        return "Course ID: " + courseId + ", Name: " + courseName + ", Credits: " + credits +
               ", Max Students: " + maxStudents + ", Enrolled Students: " + enrolledStudents.size();
    }
}
