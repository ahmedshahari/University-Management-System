import java.util.ArrayList;
import java.util.Date;

public class Schedule {

    private String scheduleId;
    private String courseId;
    private String professorId;
    private String roomId;
    private Date timeSlot;
    private String dayOfWeek;
    private int duration;       // in minutes
    private Date startTime;
    private Date endTime;
    private String classTime;
    private String instructorId;
    private String scheduleType; 
    private ArrayList<Schedule> schedules;

    public Schedule() {

    }
    public Schedule(String scheduleId, String courseId, String professorId, String roomId, Date timeSlot, String dayOfWeek, int duration, Date startTime,String instructorId, String classTime, Date endTime, String scheduleType) {
        this.scheduleId = scheduleId;
        this.courseId = courseId;
        this.professorId = professorId;
        this.roomId = roomId;
        this.timeSlot = timeSlot;
        this.dayOfWeek = dayOfWeek;
        this.duration = duration;
        this.classTime = classTime;
        this.instructorId = instructorId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.scheduleType = scheduleType;
        this.schedules = new ArrayList<>();
    }
    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getProfessorId() {
        return professorId;
    }

    public void setProfessorId(String professorId) {
        this.professorId = professorId;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public Date getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(Date timeSlot) {
        this.timeSlot = timeSlot;
    }

    public String getClassTime() {
        return this.classTime;
    }

    public void setClassTime(String classTime) {
        this.classTime = classTime;
    }
    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getScheduleType() {
        return scheduleType;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(String instructorId) {
        this.instructorId = instructorId;
    }

    public void setScheduleType(String scheduleType) {
        this.scheduleType = scheduleType;
    }

    public void createSchedule() {
        System.out.println("Schedule created for " + scheduleType + ": " + courseId + " on " + dayOfWeek + " at " + timeSlot);
    }
    public void addSchedule(Schedule schedule) {
        schedules.add(schedule);
        System.out.println("Schedule " + schedule.scheduleId + " added for course " + schedule.courseId);
    }
    public void sortSchedulesByClassTime() {
        int n = schedules.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (schedules.get(j).getClassTime().compareTo(schedules.get(j + 1).getClassTime()) > 0) {
                    Schedule temp = schedules.get(j);
                    schedules.set(j, schedules.get(j + 1));
                    schedules.set(j + 1, temp);
                }
            }
        }
        System.out.println("Schedules sorted by class time.");
    }
    public int findScheduleById(String scheduleId) {
        sortSchedulesByClassTime(); 
        int left = 0;
        int right = schedules.size() - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int result = schedules.get(mid).getScheduleId().compareTo(scheduleId);
            if (result == 0) return mid; 
            if (result < 0) left = mid + 1;
            else right = mid - 1;
        }
        return -1; 
    }
    public void sortSchedulesByInstructorId() {
        int n = schedules.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (schedules.get(j).getInstructorId().compareTo(schedules.get(minIndex).getInstructorId()) < 0) {
                    minIndex = j; 
                }
            }
            Schedule temp = schedules.get(minIndex);
            schedules.set(minIndex, schedules.get(i));
            schedules.set(i, temp);
        }
        System.out.println("Schedules sorted by instructor ID.");
    }
    public ArrayList<Schedule> findSchedulesByCourseId(String courseId) {
        ArrayList<Schedule> courseSchedules = new ArrayList<>();
        for (Schedule schedule : schedules) {
            if (schedule.getCourseId().equals(courseId)) {
                courseSchedules.add(schedule);
            }
        }
        return courseSchedules;
    }
    public void modifySchedule(Date newTimeSlot, String newDayOfWeek, int newDuration) {
        this.timeSlot = newTimeSlot;
        this.dayOfWeek = newDayOfWeek;
        this.duration = newDuration;
        System.out.println("Schedule modified for " + scheduleId + ": New time - " + newTimeSlot + ", Day - " + newDayOfWeek);
    }
    public void cancelClass() {
        System.out.println("Schedule " + scheduleId + " for " + courseId + " has been canceled.");
    }
    public void rescheduleClass(Date newStartTime, Date newEndTime) {
        this.startTime = newStartTime;
        this.endTime = newEndTime;
        System.out.println("Schedule " + scheduleId + " rescheduled to start at " + newStartTime + " and end at " + newEndTime);
    }
    public void getScheduleDetails() {
        System.out.println("Schedule ID: " + scheduleId +
                           "\nCourse ID: " + courseId +
                           "\nProfessor ID: " + professorId +
                           "\nRoom ID: " + roomId +
                           "\nTime Slot: " + timeSlot +
                           "\nDay of Week: " + dayOfWeek +
                           "\nDuration: " + duration + " minutes" +
                           "\nStart Time: " + startTime +
                           "\nEnd Time: " + endTime +
                           "\nSchedule Type: " + scheduleType);
    }
    
    public void notifyStudents() {
        System.out.println("Notifying students about schedule " + scheduleId + " for " + courseId + " on " + dayOfWeek);
    }
    public void notifyInstructor() {
        System.out.println("Notifying instructor " + professorId + " about schedule " + scheduleId);
    }

    public boolean checkAvailability(String roomId, Date timeSlot) {
        System.out.println("Checking availability for room " + roomId + " at " + timeSlot);
        return true; 
    }
    public void updateTimeSlot(Date newTimeSlot) {
        this.timeSlot = newTimeSlot;
        System.out.println("Time slot updated for " + scheduleId + ": New time slot is " + newTimeSlot);
    }
    public void trackChanges() {
        System.out.println("Changes to schedule " + scheduleId + " have been recorded.");
    }
    
}
