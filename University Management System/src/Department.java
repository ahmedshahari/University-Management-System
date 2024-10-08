import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Department {
    private String departmentId;
    private String departmentName;//
    private ArrayList<String> courses;         
    private LinkedList<String> facultyMembers; 
    private String[] students;                
    private ArrayList<Integer> courseCredits; 
    
    public Department() {

    }
    public Department(String departmentId, String departmentName, int studentCapacity) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.courses = new ArrayList<>();
        this.facultyMembers = new LinkedList<>();
        this.students = new String[studentCapacity]; // Fixed size for simplicity
        this.courseCredits = new ArrayList<>();
    }
    
    public void addCourse(String courseId, int credits) {
        courses.add(courseId);
        courseCredits.add(credits);
    }

    public void addFacultyMember(String facultyName) {
        facultyMembers.add(facultyName);
    }
    
    public void enrollStudent(String studentName, int index) {
        if (index >= 0 && index < students.length) {
            students[index] = studentName;
        } else {
            System.out.println("Invalid index for student enrollment.");
        }
    }
    
    public int searchCourse(String courseId) {
        bubbleSortCourses();
        return binarySearchCourse(courses, courseId, 0, courses.size() - 1);
    }
    
    private int binarySearchCourse(ArrayList<String> courses, String courseId, int left, int right) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            if (courses.get(mid).equals(courseId)) return mid;
            if (courses.get(mid).compareTo(courseId) > 0)
                return binarySearchCourse(courses, courseId, left, mid - 1);
            return binarySearchCourse(courses, courseId, mid + 1, right);
        }
        return -1;
    }
    
    public void bubbleSortCourses() {
        int n = courses.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (courses.get(j).compareTo(courses.get(j + 1)) > 0) {
                    String temp = courses.get(j);
                    courses.set(j, courses.get(j + 1));
                    courses.set(j + 1, temp);
                }
            }
        }
    }
    
    public int totalCredits() {
        return totalCreditsRecursive(courseCredits, 0);
    }
    
    private int totalCreditsRecursive(ArrayList<Integer> credits, int index) {
        if (index == credits.size()) return 0;
        return credits.get(index) + totalCreditsRecursive(credits, index + 1);
    }

    public int countTotalStudents() {
        return countTotalStudentsRecursive(0);
    }
    
    private int countTotalStudentsRecursive(int index) {
        if (index == students.length) return 0;
        if (students[index] != null) return 1 + countTotalStudentsRecursive(index + 1);
        return countTotalStudentsRecursive(index + 1);
    }
    
    public void listCourses() {
        System.out.println("Courses offered by the " + departmentName + " department:");
        for (String course : courses) {
            System.out.println("- " + course);
        }
    }
    
    public void listFacultyMembers() {
        System.out.println("Faculty members in the " + departmentName + " department:");
        for (String faculty : facultyMembers) {
            System.out.println("- " + faculty);
        }
    }
    
    public void listStudents() {
        System.out.println("Students enrolled in the " + departmentName + " department:");
        for (String student : students) {
            if (student != null) System.out.println("- " + student);
        }
    }
    
    public int findFacultyMember(String facultyName) {
        ArrayList<String> sortedFaculty = new ArrayList<>(facultyMembers);
        bubbleSortFaculty(sortedFaculty);
        return binarySearchFaculty(sortedFaculty.toArray(new String[0]), facultyName, 0, sortedFaculty.size() - 1);
    }
    
    private void bubbleSortFaculty(ArrayList<String> facultyList) {
        int n = facultyList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (facultyList.get(j).compareTo(facultyList.get(j + 1)) > 0) {
                    String temp = facultyList.get(j);
                    facultyList.set(j, facultyList.get(j + 1));
                    facultyList.set(j + 1, temp);
                }
            }
        }
    }
    
    private int binarySearchFaculty(String[] faculty, String facultyName, int left, int right) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            if (faculty[mid].equals(facultyName)) return mid;
            if (faculty[mid].compareTo(facultyName) > 0) return binarySearchFaculty(faculty, facultyName, left, mid - 1);
            return binarySearchFaculty(faculty, facultyName, mid + 1, right);
        }
        return -1;
    }

    public double averageCourseCredits() {
        int total = totalCredits();
        return (double) total / courseCredits.size();
    }
    
    public boolean isCourseAvailable(String courseId) {
        return courses.contains(courseId);
    }
    
    public boolean isFacultyAvailable(String facultyName) {
        return facultyMembers.contains(facultyName);
    }
    
    public String getDepartmentId() { return departmentId; }
    public void setDepartmentId(String departmentId) { this.departmentId = departmentId; }

    public String getDepartmentName() { return departmentName; }
    public void setDepartmentName(String departmentName) { this.departmentName = departmentName; }

    public ArrayList<String> getCourses() { return courses; }
    public void setCourses(ArrayList<String> courses) { this.courses = courses; }

    public LinkedList<String> getFacultyMembers() { return facultyMembers; }
    public void setFacultyMembers(LinkedList<String> facultyMembers) { this.facultyMembers = facultyMembers; }

    public String[] getStudents() { return students; }
    public void setStudents(String[] students) { this.students = students; }

    public ArrayList<Integer> getCourseCredits() { return courseCredits; }
    public void setCourseCredits(ArrayList<Integer> courseCredits) { this.courseCredits = courseCredits; }
    
    @Override
    public String toString() {
        return "Department ID: " + departmentId + ", Name: " + departmentName + ", Courses: " + courses.size() + ", Faculty Members: " + facultyMembers.size();
    }
}
