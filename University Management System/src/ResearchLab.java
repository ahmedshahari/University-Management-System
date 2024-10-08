import java.util.ArrayList;
import java.util.HashMap;

public class ResearchLab {
    private String labId;
    private String labName;
    private int capacity;
    private ArrayList<String> equipment;
    private HashMap<String, ArrayList<String>> schedule; // Key: Day, Value: List of projects
    private ArrayList<String> researchers;
    private String[] projects; // Fixed-size array for simplicity
    private HashMap<String, Integer> researcherPublications; // Key: Researcher ID, Value: Publication count

    public ResearchLab() {
        
    }
    public ResearchLab(String labId, String labName, int capacity) {
        this.labId = labId;
        this.labName = labName;
        this.capacity = capacity;
        this.equipment = new ArrayList<>();
        this.schedule = new HashMap<>();
        this.researchers = new ArrayList<>();
        this.projects = new String[10]; // Fixed size for simplicity
        this.researcherPublications = new HashMap<>();
    }

    public void addEquipment(String equipmentItem) {
        equipment.add(equipmentItem);
    }

    public void addProjectToSchedule(String day, String projectId) {
        schedule.putIfAbsent(day, new ArrayList<>());
        schedule.get(day).add(projectId);
    }

    public void addResearcher(String researcherId) {
        if (researchers.size() < capacity) {
            researchers.add(researcherId);
            researcherPublications.put(researcherId, 0); // Initializing with zero publications
        } else {
            System.out.println("Lab is at full capacity.");
        }
    }

    public void assignProject(String projectId, int index) {
        if (index >= 0 && index < projects.length) {
            projects[index] = projectId;
        } else {
            System.out.println("Invalid index for project assignment.");
        }
    }

    public void incrementPublication(String researcherId) {
        if (researcherPublications.containsKey(researcherId)) {
            researcherPublications.put(researcherId, researcherPublications.get(researcherId) + 1);
        } else {
            System.out.println("Researcher ID not found.");
        }
    }

    public int searchProject(String projectId) {
        bubbleSortProjects();
        return binarySearchProject(projects, projectId, 0, projects.length - 1);
    }

    private int binarySearchProject(String[] projects, String projectId, int left, int right) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            if (projects[mid] != null && projects[mid].equals(projectId)) return mid;
            if (projects[mid] != null && projects[mid].compareTo(projectId) > 0)
                return binarySearchProject(projects, projectId, left, mid - 1);
            return binarySearchProject(projects, projectId, mid + 1, right);
        }
        return -1;
    }

    public void bubbleSortProjects() {
        int n = projects.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (projects[j] != null && projects[j + 1] != null) {
                    if (projects[j].compareTo(projects[j + 1]) > 0) {
                        String temp = projects[j];
                        projects[j] = projects[j + 1];
                        projects[j + 1] = temp;
                    }
                }
            }
        }
    }

    public int totalPublications() {
        return totalPublicationsRecursive(new ArrayList<>(researcherPublications.values()), 0);
    }

    private int totalPublicationsRecursive(ArrayList<Integer> publicationList, int index) {
        if (index == publicationList.size()) return 0;
        return publicationList.get(index) + totalPublicationsRecursive(publicationList, index + 1);
    }

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

    public void listResearchers() {
        System.out.println("Researchers in Lab " + labName + ":");
        for (String researcher : researchers) {
            System.out.println("- " + researcher);
        }
    }

    public int findResearcher(String researcherId) {
        ArrayList<String> sortedResearchers = new ArrayList<>(researchers);
        bubbleSortResearchers(sortedResearchers);
        return binarySearchResearcher(sortedResearchers.toArray(new String[0]), researcherId, 0, sortedResearchers.size() - 1);
    }

    private void bubbleSortResearchers(ArrayList<String> researchers) {
        int n = researchers.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (researchers.get(j).compareTo(researchers.get(j + 1)) > 0) {
                    String temp = researchers.get(j);
                    researchers.set(j, researchers.get(j + 1));
                    researchers.set(j + 1, temp);
                }
            }
        }
    }

    private int binarySearchResearcher(String[] researchers, String researcherId, int left, int right) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            if (researchers[mid].equals(researcherId)) return mid;
            if (researchers[mid].compareTo(researcherId) > 0) return binarySearchResearcher(researchers, researcherId, left, mid - 1);
            return binarySearchResearcher(researchers, researcherId, mid + 1, right);
        }
        return -1;
    }

    public boolean isEquipmentAvailable(String equipmentItem) {
        return equipment.contains(equipmentItem);
    }

    public void listEquipment() {
        System.out.println("Equipment in Lab " + labName + ":");
        for (String item : equipment) {
            System.out.println("- " + item);
        }
    }

    
    public String getLabId() { return labId; }
    public void setLabId(String labId) { this.labId = labId; }

    public String getLabName() { return labName; }
    public void setLabName(String labName) { this.labName = labName; }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public ArrayList<String> getEquipment() { return equipment; }
    public void setEquipment(ArrayList<String> equipment) { this.equipment = equipment; }

    public HashMap<String, ArrayList<String>> getSchedule() { return schedule; }
    public void setSchedule(HashMap<String, ArrayList<String>> schedule) { this.schedule = schedule; }

    public ArrayList<String> getResearchers() { return researchers; }
    public void setResearchers(ArrayList<String> researchers) { this.researchers = researchers; }

    public String[] getProjects() { return projects; }
    public void setProjects(String[] projects) { this.projects = projects; }

    public HashMap<String, Integer> getResearcherPublications() { return researcherPublications; }
    public void setResearcherPublications(HashMap<String, Integer> researcherPublications) { this.researcherPublications = researcherPublications; }

    @Override
    public String toString() {
        return "Research Lab ID: " + labId + ", Name: " + labName + ", Capacity: " + capacity;
    }
}
