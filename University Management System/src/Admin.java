import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Random;

public class Admin extends Person implements Workable{
    // complete her the first and last names
    private Scanner sc = new Scanner(System.in);
    private Random rnd = new Random();
    private ArrayList<Course> courses;
    private HashMap<String, Student> studentRecords;
    private ArrayList<String> loggedActivities;
    private int maxCourses;
    private final char[] numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};
    public Admin() {

    }
    public Admin(String id, String firstName, String lastName, int maxCourses) {
        this.id= id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.maxCourses = maxCourses;
    }

    public boolean addCourse(Course course) {
        if (courses.size() < maxCourses) {
            courses.add(course);
            logActivity("Course added: " + course.getCourseName());
            return true;
        } else {
            System.out.println("Maximum number of courses reached.");
            return false;
        }
    }

    public boolean removeCourse(String courseId) {
        for (Course course : courses) {
            if (course.getCourseId().equals(courseId)) {
                courses.remove(course);
                logActivity("Course removed: " + course.getCourseName());
                return true;
            }
        }
        System.out.println("Course not found.");
        return false;
    }

    public void addStudentRecord(Student student) {
        studentRecords.put(String.valueOf(student.getStudentId()), student);
        logActivity("Student added: " + student.getFirstName() + " " + student.getLastName());
    }

    public boolean removeStudentRecord(String studentId) {
        if (studentRecords.containsKey(studentId)) {
            studentRecords.remove(studentId);
            logActivity("Student removed: " + studentId);
            return true;
        } else {
            System.out.println("Student not found.");
            return false;
        }
    }

    public Student searchStudentById(String studentId) {
        String[] studentIds = studentRecords.keySet().toArray(new String[0]);
        selectionSort(studentIds);
        int index = binarySearch(studentIds, studentId, 0, studentIds.length - 1);
        return index != -1 ? studentRecords.get(studentIds[index]) : null;
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

    private void selectionSort(String[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            String temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    private void logActivity(String activity) {
        loggedActivities.add(activity);
        System.out.println(activity);
    }

    public void displayCourses() {
        System.out.println("Courses managed by administrator " + firstName + " " + lastName + ":");
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    public void displayStudentRecords() {
        System.out.println("Student Records:");
        for (Student student : studentRecords.values()) {
            System.out.println(student);
        }
    }

    public int countTotalStudents() {
        return studentRecords.size();
    }

    public double getAverageGrade(String studentId) {
        Student student = studentRecords.get(studentId);
        if (student != null) {
            return student.calculateAverageGrade();
        }
        return -1;
    }

    
    public String getAdminId() { return id; }
    public void setAdminId(String adminId) { this.id = adminId; }

    public String getFirstName() { return firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public ArrayList<Course> getCourses() { return courses; }
    public void setCourses(ArrayList<Course> courses) { this.courses = courses; }

    public HashMap<String, Student> getStudentRecords() { return studentRecords; }
    public void setStudentRecords(HashMap<String, Student> studentRecords) { this.studentRecords = studentRecords; }

    public ArrayList<String> getLoggedActivities() { return loggedActivities; }
    public void setLoggedActivities(ArrayList<String> loggedActivities) { this.loggedActivities = loggedActivities; }

    public int getMaxCourses() { return maxCourses; }
    public void setMaxCourses(int maxCourses) { this.maxCourses = maxCourses; }

    @Override
    public String toString() {
        return "Administrator ID: " + id + ", Name: " + firstName + " " + lastName + ", Max Courses: " + maxCourses;
    }
    @Override
    public void startWork() {
        System.out.println(firstName + " " + lastName + " starts working at: 6 Am");
    }
    @Override
    public void endWork() {
        System.out.println(firstName + " " + lastName + " ends working at: 4 pm");
    }
    @Override
    public void getWorkHours() {
        System.out.println("Totally work hours: " + 4 + " hours");
    }
    @Override
    void updateContactInfo() {
        System.out.println("Updating contact info process");
        System.out.println("Please input the new phone number");
        String phoneNumber = sc.nextLine();
        this.setPhoneNumber(phoneNumber);
        System.out.println("Please input the new email: ");
        String email = sc.nextLine();
        this.setEmail(email);
        System.out.println("Please input the new address details: ");
        System.out.println("City: ");
        String City = sc.nextLine();
        this.address.setCity(City);
        System.out.println("New district: ");
        String district = sc.nextLine();
        this.address.setDistrict(district);
        System.out.println("New street: ");
        String street = sc.nextLine();
        this.address.setStreet(street);
        System.out.println("New postal code: ");
        int postalCode = sc.nextInt();
        this.address.setPostalCode(postalCode);
        System.out.println("New house number: ");
        int houseNumber = sc.nextInt();
        this.address.setHouseNumber(houseNumber);
    }
    @Override
    boolean isAdult() {
        return this.age > 18;
    }
    @Override
    String generatedId() {
        String id = "";
        char[] nameLetters = (firstName+lastName).toCharArray();
        for (int i = 0; i < 3; i++) {
            id += numbers[rnd.nextInt(numbers.length)];
            id += nameLetters[rnd.nextInt(nameLetters.length)];
        }
        return id;
    }
}
