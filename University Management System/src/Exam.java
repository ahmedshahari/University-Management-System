import java.util.ArrayList;

public class Exam {

    private String examId;
    private String courseId;
    private String date; 
    private String startTime;
    private String endTime;
    private String instructorId;
    private ArrayList<Exam> exams;
    
    public String getExamId() {
        return examId;
    }

    public void setExamId(String examId) {
        this.examId = examId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public ArrayList<Exam> getExams() {
        return exams;
    }

    public void setExams(ArrayList<Exam> exams) {
        this.exams = exams;
    }

    public Exam() {

    }
    public Exam(String examId, String courseId, String date, String startTime, String endTime, String instructorId) {
        this.examId = examId;
        this.courseId = courseId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.instructorId = instructorId;
        this.exams = new ArrayList<>();
    }

    public void addExam(Exam exam) {
        exams.add(exam);
        System.out.println("Exam " + exam.examId + " added for course " + exam.courseId);
    }

    public void sortExamsByDate() {
        int n = exams.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (exams.get(j).getDate().compareTo(exams.get(minIndex).getDate()) < 0) {
                    minIndex = j; 
                }
            }
            Exam temp = exams.get(minIndex);
            exams.set(minIndex, exams.get(i));
            exams.set(i, temp);
        }
        System.out.println("Exams sorted by date.");
    }

    public int findExamById(String examId) {
        sortExamsByDate();
        int left = 0;
        int right = exams.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int result = exams.get(mid).getExamId().compareTo(examId);
            if (result == 0) return mid; 
            if (result < 0) left = mid + 1;
            else right = mid - 1;
        }
        return -1; 
    }

    public ArrayList<Exam> findExamsByCourseId(String courseId) {
        return findExamsByCourseIdHelper(courseId, 0);
    }

    private ArrayList<Exam> findExamsByCourseIdHelper(String courseId, int index) {
        ArrayList<Exam> courseExams = new ArrayList<>();
        if (index >= exams.size()) {
            return courseExams; 
        }
        if (exams.get(index).getCourseId().equals(courseId)) {
            courseExams.add(exams.get(index)); 
        }
        courseExams.addAll(findExamsByCourseIdHelper(courseId, index + 1));
        return courseExams;
    }

    
    public void generateExamReport() {
        sortExamsByDate(); 
        System.out.println("Exam Report:");
        for (Exam exam : exams) {
            System.out.println("Exam ID: " + exam.examId +
                               ", Course ID: " + exam.courseId +
                               ", Date: " + exam.date +
                               ", Start Time: " + exam.startTime +
                               ", End Time: " + exam.endTime +
                               ", Instructor ID: " + exam.instructorId);
        }
    }

    public int countExamsByInstructorId(String instructorId) {
        return countExamsByInstructorIdHelper(instructorId, 0);
    }

    private int countExamsByInstructorIdHelper(String instructorId, int index) {
        if (index >= exams.size()) {
            return 0; // Base case: end of the list
        }
        int count = (exams.get(index).getInstructorId().equals(instructorId) ? 1 : 0);
        return count + countExamsByInstructorIdHelper(instructorId, index + 1);
    }


    public void sortExamsByInstructorId() {
        int n = exams.size();
        for (int i = 1; i < n; i++) {
            Exam key = exams.get(i);
            int j = i - 1;
            while (j >= 0 && exams.get(j).getInstructorId().compareTo(key.getInstructorId()) > 0) {
                exams.set(j + 1, exams.get(j));
                j = j - 1;
            }
            exams.set(j + 1, key);
        }
        System.out.println("Exams sorted by instructor ID.");
    }
}
