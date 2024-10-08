import java.util.HashMap;
import java.util.ArrayList;

public class Event {

    // Attributes
    private String eventId;
    private String eventName;
    private String eventDate;
    private String eventLocation;
    private int maxParticipants;
    private boolean isOnline;
    private double registrationFee;
    private ArrayList<String> participants;
    private HashMap<String, String> feedback; // Stores participant feedback
    private HashMap<String, String> organizerContacts;

    public Event() {
        
    }
    public Event(String eventId, String eventName, String eventDate, String eventLocation, int maxParticipants,
                 boolean isOnline, double registrationFee) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.maxParticipants = maxParticipants;
        this.isOnline = isOnline;
        this.registrationFee = registrationFee;
        this.participants = new ArrayList<>();
        this.feedback = new HashMap<>();
        this.organizerContacts = new HashMap<>();
    }

    public boolean addParticipant(String participantName) {
        if (participants.size() < maxParticipants) {
            participants.add(participantName);
            return true;
        }
        return false;
    }

    public boolean removeParticipant(String participantName) {
        return participants.remove(participantName);
    }

    public void sortParticipants() {
        int n = participants.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (participants.get(j).compareTo(participants.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            String temp = participants.get(minIndex);
            participants.set(minIndex, participants.get(i));
            participants.set(i, temp);
        }
    }

    public int findParticipant(String participantName) {
        return binarySearchParticipant(participants.toArray(new String[0]), participantName, 0, participants.size() - 1);
    }

    private int binarySearchParticipant(String[] participantsArray, String name, int left, int right) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            int comparison = participantsArray[mid].compareTo(name);
            if (comparison == 0) return mid;
            if (comparison > 0) return binarySearchParticipant(participantsArray, name, left, mid - 1);
            return binarySearchParticipant(participantsArray, name, mid + 1, right);
        }
        return -1;
    }

    public void registerFeedback(String participantName, String feedbackText) {
        feedback.put(participantName, feedbackText);
    }

    public String getFeedback(String participantName) {
        return feedback.getOrDefault(participantName, "No feedback available");
    }

    public void addOrganizerContact(String organizerName, String contactInfo) {
        organizerContacts.put(organizerName, contactInfo);
    }

    public String getOrganizerContact(String organizerName) {
        return organizerContacts.getOrDefault(organizerName, "Contact not available");
    }

    public double calculateRevenue() {
        return registrationFee * participants.size();
    }

    public void displayParticipants() {
        System.out.println("Participants for event " + eventName + ":");
        for (String participant : participants) {
            System.out.println(participant);
        }
    }


    public String findShortestName() {
        return findShortestNameRecursive(participants, participants.size());
    }

    private String findShortestNameRecursive(ArrayList<String> participants, int n) {
        if (n == 1) return participants.get(0);
        String previousShortest = findShortestNameRecursive(participants, n - 1);
        return participants.get(n - 1).length() < previousShortest.length() ? participants.get(n - 1) : previousShortest;
    }

    public void displayAllFeedback() {
        System.out.println("Feedback for event " + eventName + ":");
        for (String participant : feedback.keySet()) {
            System.out.println(participant + ": " + feedback.get(participant));
        }
    }

    public void sortOrganizerContacts() {
        ArrayList<String> keys = new ArrayList<>(organizerContacts.keySet());
        int n = keys.size();
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (keys.get(j).compareTo(keys.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            String temp = keys.get(minIndex);
            keys.set(minIndex, keys.get(i));
            keys.set(i, temp);
        }
        System.out.println("Organizer Contacts:");
        for (String key : keys) {
            System.out.println(key + ": " + organizerContacts.get(key));
        }
    }

    public String searchOrganizerContact(String name) {
        ArrayList<String> keys = new ArrayList<>(organizerContacts.keySet());
        keys.sort(null);
        int index = binarySearchOrganizer(keys.toArray(new String[0]), name, 0, keys.size() - 1);
        return index != -1 ? organizerContacts.get(keys.get(index)) : "Organizer not found";
    }

    private int binarySearchOrganizer(String[] names, String name, int left, int right) {
        if (right >= left) {
            int mid = left + (right - left) / 2;
            int comparison = names[mid].compareTo(name);
            if (comparison == 0) return mid;
            if (comparison > 0) return binarySearchOrganizer(names, name, left, mid - 1);
            return binarySearchOrganizer(names, name, mid + 1, right);
        }
        return -1;
    }

    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }

    public String getEventName() { return eventName; }
    public void setEventName(String eventName) { this.eventName = eventName; }

    public String getEventDate() { return eventDate; }
    public void setEventDate(String eventDate) { this.eventDate = eventDate; }

    public String getEventLocation() { return eventLocation; }
    public void setEventLocation(String eventLocation) { this.eventLocation = eventLocation; }

    public int getMaxParticipants() { return maxParticipants; }
    public void setMaxParticipants(int maxParticipants) { this.maxParticipants = maxParticipants; }

    public boolean isOnline() { return isOnline; }
    public void setOnline(boolean isOnline) { this.isOnline = isOnline; }

    public double getRegistrationFee() { return registrationFee; }
    public void setRegistrationFee(double registrationFee) { this.registrationFee = registrationFee; }

    public ArrayList<String> getParticipants() { return participants; }
    public void setParticipants(ArrayList<String> participants) { this.participants = participants; }

    public HashMap<String, String> getFeedback() { return feedback; }
    public void setFeedback(HashMap<String, String> feedback) { this.feedback = feedback; }

    public HashMap<String, String> getOrganizerContacts() { return organizerContacts; }
    public void setOrganizerContacts(HashMap<String, String> organizerContacts) { this.organizerContacts = organizerContacts; }

    @Override
    public String toString() {
        return "Event ID: " + eventId + ", Name: " + eventName + ", Date: " + eventDate + 
               ", Location: " + eventLocation + ", Max Participants: " + maxParticipants +
               ", Online: " + isOnline + ", Fee: " + registrationFee;
    }
}
