import java.util.ArrayList;
import java.util.HashMap;

public class Student {
    private String studentId;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private HashMap<String, Integer> grades; 
    private ArrayList<String> enrolledCourses;
    private int[] gradeArray;
    private String[] courseArray;

    public Student() {

    }
    public Student(String studentId, String firstName, String lastName, String email, int age) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.grades = new HashMap<>();
        this.enrolledCourses = new ArrayList<>();
        this.gradeArray = new int[100]; // Max 100 courses
        this.courseArray = new String[100];
    }

    
    public boolean enrollInCourse(String courseId) {
        if (enrolledCourses.size() < 100) {
            enrolledCourses.add(courseId);
            System.out.println(firstName + " " +lastName + " has enrolled in course: " + courseId);
            return true;
        } else {
            System.out.println("Maximum course limit reached for student: " + firstName + " " + lastName);
            return false;
        }
    }

    // Drop a course
    public boolean dropCourse(String courseId) {
        if (enrolledCourses.contains(courseId)) {
            enrolledCourses.remove(courseId);
            grades.remove(courseId);
            System.out.println(firstName + " " +lastName + " has dropped course: " + courseId);
            return true;
        } else {
            System.out.println(firstName + " " +lastName + " is not enrolled in course: " + courseId);
            return false;
        }
    }

    
    public void assignGrade(String courseId, int grade) {
        if (enrolledCourses.contains(courseId)) {
            grades.put(courseId, grade);
            updateGradeArray();
            System.out.println("Grade " + grade + " assigned to " + firstName + " " +lastName + " for course: " + courseId);
        } else {
            System.out.println(firstName + " " +lastName + " is not enrolled in course: " + courseId);
        }
    }

    public double calculateAverageGrade() {
        int total = 0;
        int count = 0;
        for (int grade : grades.values()) {
            total += grade;
            count++;
        }
        return count > 0 ? (double) total / count : 0.0;
    }

    public Integer findGradeByCourseId(String courseId) {
        String[] courseIds = grades.keySet().toArray(new String[0]);
        bubbleSort(courseIds);
        int index = binarySearch(courseIds, courseId, 0, courseIds.length - 1);
        return index != -1 ? grades.get(courseIds[index]) : null;
    }

    private int binarySearch(String[] array, String key, int left, int right) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            if (array[mid].equals(key)) {
                return mid;
            } else if (array[mid].compareTo(key) > 0) {
                return binarySearch(array, key, left, mid - 1);
            } else {
                return binarySearch(array, key, mid + 1, right);
            }
        }
        return -1;
    }

    private void bubbleSort(String[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    String temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    private void updateGradeArray() {
        int index = 0;
        for (String courseId : enrolledCourses) {
            if (grades.containsKey(courseId)) {
                gradeArray[index++] = grades.get(courseId);
                courseArray[index - 1] = courseId;
            }
        }
    }

    public int findHighestGrade() {
        return findHighestRecursive(gradeArray, enrolledCourses.size());
    }

    private int findHighestRecursive(int[] grades, int count) {
        if (count == 1) return grades[0];
        int maxOfRest = findHighestRecursive(grades, count - 1);
        return Math.max(grades[count - 1], maxOfRest);
    }

    public void displayStudentInfo() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + firstName + " " +lastName);
        System.out.println("Email: " + email);
        System.out.println("Age: " + age);
        System.out.println("Enrolled Courses: " + enrolledCourses);
        System.out.println("Grades: " + grades);
    }

    // Display all courses enrolled
    public void displayEnrolledCourses() {
        System.out.println(firstName + " " +lastName + "'s Enrolled Courses:");
        for (String courseId : enrolledCourses) {
            System.out.println("- " + courseId);
        }
    }

    public String getStudentId() { return studentId; }
    public void setStudentId(String studentId) { this.studentId = studentId; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public HashMap<String, Integer> getGrades() { return grades; }
    public void setGrades(HashMap<String, Integer> grades) { this.grades = grades; }

    public ArrayList<String> getEnrolledCourses() { return enrolledCourses; }
    public void setEnrolledCourses(ArrayList<String> enrolledCourses) { this.enrolledCourses = enrolledCourses; }

    @Override
    public String toString() {
        return "Student ID: " + studentId + ", Name: " + firstName + " " +lastName + ", Email: " + email + ", Age: " + age;
    }
}
