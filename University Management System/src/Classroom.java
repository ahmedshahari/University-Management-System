import java.util.ArrayList;
import java.util.HashMap;

public class Classroom {
    private String classroomId;
    private int capacity;
    private String location;
    private ArrayList<String> equipment;
    private HashMap<String, ArrayList<String>> schedule; // Key: Day, Value: List of course IDs
    private ArrayList<String> enrolledStudents;
    private String[] courseAssignments; 
    private HashMap<String, Integer> studentAttendance; // Key: Student ID, Value: Attendance count

    public Classroom() {

    }
    public Classroom(String classroomId, int capacity, String location) {
        this.classroomId = classroomId;
        this.capacity = capacity;
        this.location = location;
        this.equipment = new ArrayList<>();
        this.schedule = new HashMap<>();
        this.enrolledStudents = new ArrayList<>();
        this.courseAssignments = new String[10]; // Fixed size for simplicity
        this.studentAttendance = new HashMap<>();
    }

    public void addEquipment(String equipmentItem) {
        equipment.add(equipmentItem);
    }

    public void addSchedule(String day, String courseId) {
        schedule.putIfAbsent(day, new ArrayList<>());
        schedule.get(day).add(courseId);
    }

    public void enrollStudent(String studentId) {
        if (enrolledStudents.size() < capacity) {
            enrolledStudents.add(studentId);
            studentAttendance.put(studentId, 0);
        } else {
            System.out.println("Classroom is at full capacity.");
        }
    }

    public void assignCourse(String courseId, int index) {
        if (index >= 0 && index < courseAssignments.length) {
            courseAssignments[index] = courseId;
        } else {
            System.out.println("Invalid index for course assignment.");
        }
    }

    public void incrementAttendance(String studentId) {
        if (studentAttendance.containsKey(studentId)) {
            studentAttendance.put(studentId, studentAttendance.get(studentId) + 1);
        } else {
            System.out.println("Student ID not found in attendance records.");
        }
    }

    public int searchCourse(String courseId) {
        bubbleSortCourses();
        return binarySearchCourse(courseAssignments, courseId, 0, courseAssignments.length - 1);
    }

    private int binarySearchCourse(String[] courses, String courseId, int left, int right) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            if (courses[mid] != null && courses[mid].equals(courseId)) return mid;
            if (courses[mid] != null && courses[mid].compareTo(courseId) > 0)
                return binarySearchCourse(courses, courseId, left, mid - 1);
            return binarySearchCourse(courses, courseId, mid + 1, right);
        }
        return -1;
    }

    public void bubbleSortCourses() {
        int n = courseAssignments.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (courseAssignments[j] != null && courseAssignments[j + 1] != null) {
                    if (courseAssignments[j].compareTo(courseAssignments[j + 1]) > 0) {
                        String temp = courseAssignments[j];
                        courseAssignments[j] = courseAssignments[j + 1];
                        courseAssignments[j + 1] = temp;
                    }
                }
            }
        }
    }

    // Recursive method: Calculate the total attendance for the classroom
    public int totalAttendance() {
        return totalAttendanceRecursive(new ArrayList<>(studentAttendance.values()), 0);
    }

    private int totalAttendanceRecursive(ArrayList<Integer> attendanceList, int index) {
        if (index == attendanceList.size()) return 0;
        return attendanceList.get(index) + totalAttendanceRecursive(attendanceList, index + 1);
    }

    // Recursive method: Count the total equipment in the classroom
    public int countTotalEquipment() {
        return countTotalEquipmentRecursive(0);
    }

    private int countTotalEquipmentRecursive(int index) {
        if (index == equipment.size()) return 0;
        return 1 + countTotalEquipmentRecursive(index + 1);
    }

    public void displaySchedule(String day) {
        if (schedule.containsKey(day)) {
            System.out.println("Schedule for " + day + ": " + schedule.get(day));
        } else {
            System.out.println("No schedule available for " + day + ".");
        }
    }

    public void listEnrolledStudents() {
        System.out.println("Enrolled Students:");
        for (String student : enrolledStudents) {
            System.out.println("- " + student);
        }
    }

    public int findStudent(String studentId) {
        ArrayList<String> sortedStudents = new ArrayList<>(enrolledStudents);
        bubbleSortStudents(sortedStudents);
        return binarySearchStudent(sortedStudents.toArray(new String[0]), studentId, 0, sortedStudents.size() - 1);
    }

    private void bubbleSortStudents(ArrayList<String> students) {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (students.get(j).compareTo(students.get(j + 1)) > 0) {
                    String temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
    }

    private int binarySearchStudent(String[] students, String studentId, int left, int right) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            if (students[mid].equals(studentId)) return mid;
            if (students[mid].compareTo(studentId) > 0) return binarySearchStudent(students, studentId, left, mid - 1);
            return binarySearchStudent(students, studentId, mid + 1, right);
        }
        return -1;
    }

    public boolean isEquipmentAvailable(String equipmentItem) {
        return equipment.contains(equipmentItem);
    }

    public void listEquipment() {
        System.out.println("Equipment in Classroom " + classroomId + ":");
        for (String item : equipment) {
            System.out.println("- " + item);
        }
    }

    public String getClassroomId() { return classroomId; }
    public void setClassroomId(String classroomId) { this.classroomId = classroomId; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public ArrayList<String> getEquipment() { return equipment; }
    public void setEquipment(ArrayList<String> equipment) { this.equipment = equipment; }

    public HashMap<String, ArrayList<String>> getSchedule() { return schedule; }
    public void setSchedule(HashMap<String, ArrayList<String>> schedule) { this.schedule = schedule; }

    public ArrayList<String> getEnrolledStudents() { return enrolledStudents; }
    public void setEnrolledStudents(ArrayList<String> enrolledStudents) { this.enrolledStudents = enrolledStudents; }

    public String[] getCourseAssignments() { return courseAssignments; }
    public void setCourseAssignments(String[] courseAssignments) { this.courseAssignments = courseAssignments; }

    public HashMap<String, Integer> getStudentAttendance() { return studentAttendance; }
    public void setStudentAttendance(HashMap<String, Integer> studentAttendance) { this.studentAttendance = studentAttendance; }

    @Override
    public String toString() {
        return "Classroom ID: " + classroomId + ", Capacity: " + capacity + ", Location: " + location;
    }
}
