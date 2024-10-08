import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class Staff extends Person implements Workable {

    private Random rnd = new Random();
    private Scanner sc = new Scanner(System.in);
    private String staffId;
    private String department;
    private String position;
    private double salary;
    private boolean isPermanent;
    private String shift;
    private int yearsOfExperience;
    private ArrayList<String> assignedTasks;
    private HashMap<String, Double> taskCompletionRate;
    private HashMap<String, String> contactDetails;
    private final char[] numbers = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0'};

    public Staff(String name, int age, String address, String staffId, String department, String position, 
                 double salary, boolean isPermanent, String shift, int yearsOfExperience) {
        this.staffId = staffId;
        this.department = department;
        this.position = position;
        this.salary = salary;
        this.isPermanent = isPermanent;
        this.shift = shift;
        this.yearsOfExperience = yearsOfExperience;
        this.assignedTasks = new ArrayList<>();
        this.taskCompletionRate = new HashMap<>();
        this.contactDetails = new HashMap<>();
    }
   
    public void addTask(String task) {
        assignedTasks.add(task);
        taskCompletionRate.put(task, 0.0);
    }

    public void completeTask(String task) {
        if (assignedTasks.contains(task)) {
            taskCompletionRate.put(task, 100.0);
            System.out.println("Task " + task + " completed successfully.");
        } else {
            System.out.println("Task " + task + " not found.");
        }
    }

    public void sortTasks() {
        int n = assignedTasks.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (assignedTasks.get(j).compareTo(assignedTasks.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            String temp = assignedTasks.get(minIndex);
            assignedTasks.set(minIndex, assignedTasks.get(i));
            assignedTasks.set(i, temp);
        }
    }

    public int findTask(String task) {
        sortTasks();
        return binarySearchTask(assignedTasks.toArray(new String[0]), task, 0, assignedTasks.size() - 1);
    }

    private int binarySearchTask(String[] tasks, String task, int left, int right) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            if (tasks[mid].equals(task)) return mid;
            if (tasks[mid].compareTo(task) > 0) return binarySearchTask(tasks, task, left, mid - 1);
            return binarySearchTask(tasks, task, mid + 1, right);
        }
        return -1;
    }

    public void addContact(String type, String detail) {
        contactDetails.put(type, detail);
    }

    public String getContactDetail(String type) {
        return contactDetails.getOrDefault(type, "Contact detail not available");
    }

    public double calculateCompletionRate() {
        return calculateCompletionRateRecursive(new ArrayList<>(taskCompletionRate.values()), 0, taskCompletionRate.size()) / taskCompletionRate.size();
    }

    private double calculateCompletionRateRecursive(ArrayList<Double> rates, int index, int totalTasks) {
        if (index == totalTasks) return 0;
        return rates.get(index) + calculateCompletionRateRecursive(rates, index + 1, totalTasks);
    }

    public void listTasksWithCompletionRate() {
        System.out.println("Task Completion Rate for " + firstName + " " + lastName + ":");
        for (String task : taskCompletionRate.keySet()) {
            System.out.println(task + ": " + taskCompletionRate.get(task) + "%");
        }
    }

    public String findMinCompletionTask() {
        return findMinCompletionTaskRecursive(new ArrayList<>(taskCompletionRate.keySet()), 
                                              new ArrayList<>(taskCompletionRate.values()), 
                                              taskCompletionRate.size());
    }

    private String findMinCompletionTaskRecursive(ArrayList<String> tasks, ArrayList<Double> rates, int n) {
        if (n == 1) return tasks.get(0);
        String minTask = findMinCompletionTaskRecursive(tasks, rates, n - 1);
        return rates.get(n - 1) < taskCompletionRate.get(minTask) ? tasks.get(n - 1) : minTask;
    }

    public void displayContacts() {
        ArrayList<String> contacts = new ArrayList<>(contactDetails.keySet());
        selectionSort(contacts);
        System.out.println("Contact Details for " + firstName + " " + lastName + ":");
        for (String type : contacts) {
            System.out.println(type + ": " + contactDetails.get(type));
        }
    }

    private void selectionSort(ArrayList<String> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (list.get(j).compareTo(list.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            String temp = list.get(minIndex);
            list.set(minIndex, list.get(i));
            list.set(i, temp);
        }
    }

    public String searchContact(String type) {
        ArrayList<String> contacts = new ArrayList<>(contactDetails.keySet());
        selectionSort(contacts);
        int index = binarySearchContact(contacts.toArray(new String[0]), type, 0, contacts.size() - 1);
        return index != -1 ? contactDetails.get(contacts.get(index)) : "Contact not found";
    }

    private int binarySearchContact(String[] contacts, String type, int left, int right) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            if (contacts[mid].equals(type)) return mid;
            if (contacts[mid].compareTo(type) > 0) return binarySearchContact(contacts, type, left, mid - 1);
            return binarySearchContact(contacts, type, mid + 1, right);
        }
        return -1;
    }

    public String getStaffId() { return staffId; }
    public void setStaffId(String staffId) { this.staffId = staffId; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public boolean isPermanent() { return isPermanent; }
    public void setPermanent(boolean isPermanent) { this.isPermanent = isPermanent; }

    public String getShift() { return shift; }
    public void setShift(String shift) { this.shift = shift; }

    public int getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(int yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }

    public ArrayList<String> getAssignedTasks() { return assignedTasks; }
    public void setAssignedTasks(ArrayList<String> assignedTasks) { this.assignedTasks = assignedTasks; }

    public HashMap<String, Double> getTaskCompletionRate() { return taskCompletionRate; }
    public void setTaskCompletionRate(HashMap<String, Double> taskCompletionRate) { this.taskCompletionRate = taskCompletionRate; }

    public HashMap<String, String> getContactDetails() { return contactDetails; }
    public void setContactDetails(HashMap<String, String> contactDetails) { this.contactDetails = contactDetails; }

    @Override
    public void startWork() {
        System.out.println(firstName + " " + lastName + " starts working at: 6 Am");
    }

    @Override
    public void endWork() {
        System.out.println(firstName + " " + lastName + " ends working at: 4 Pm");
    }

    @Override
    public void getWorkHours() {
        System.out.println("Totally work hours: " + 8 + " hours");
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
    @Override
    public String toString() {
        return "Staff ID: " + staffId + ", Name: " + firstName + " " + lastName + ", Department: " + department + 
               ", Position: " + position + ", Salary: " + salary + ", Shift: " + shift + 
               ", Years of Experience: " + yearsOfExperience;
    }
}