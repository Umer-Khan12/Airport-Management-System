import java.util.ArrayList;

public class Runway {
    private int id;
    private ArrayList<Flight> queue;

    public Runway (int id) {
        this.id = id;
        this.queue = new ArrayList<Flight>();
    }

    // Accessors
    public int getId() {return id;}
    public ArrayList<Flight> getQueue() {return queue;}

    public void addFlight(Flight flight) {
        if (flight == null) {
            throw new RuntimeException("Error: Flight to be added is invalid.");
        }
        else if (queue.contains(flight)) {
            throw new RuntimeException("Error: The flight is already added to the runway queue.");
        }
        else {
            this.queue.add(flight);
        }
    }

    public void removeFlight(Flight flight) {
        if (flight == null) {
            throw new RuntimeException("Error: Flight to be removed is invalid.");
        }
        else if (!queue.contains(flight)) {
            throw new RuntimeException("Error: Tried to remove a flight that doesn't exist on the runway.");
        }
        else {
            this.queue.remove(flight);
        }
    }

    @Override
    public String toString() {
        String s = "Runway "+this.id+": [";
        if (this.queue.isEmpty()) {return s+"]";}
        else{
            for (int i=0; i < this.queue.size(); i++) {
                if (i==this.queue.size()-1) {
                    s += "Flight" + this.queue.get(i).getId() + "]";
                } else {
                    s += "Flight" + this.queue.get(i).getId() + ", ";
                }
            }
        }
        return s;
    }

    public static void main(String[] args) {
        // Unit Tests
        Runway runway = new Runway(1);
        // Test getId()
        if (runway.getId() != 1){
            System.out.println("Error in getId(): Expected 1 but got " + runway.getId());
        }
        // Test addFlight()
        // With valid input
        Flight flight1 = new Flight(1, "Saskatoon", "Toronto", 25, 130);
        try {
            runway.addFlight(flight1);
        }
        catch (RuntimeException exception) {
            System.out.println("Error in addFlight(): Exception thrown for valid input.");
        }
        // With invalid input
        try {
            runway.addFlight(null);
            System.out.println("Error in addFlight(): Exception not thrown for invalid input.");
        }
        catch (RuntimeException exception) {
            // Expected
        }
        // Test getQueue()
        Flight flight2 = new Flight(2, "Toronto", "Paris", 330, 1030);
        runway.addFlight(flight2);
        ArrayList<Flight> expected = new ArrayList<>();
        expected.add(flight1);
        expected.add(flight2);
        ArrayList<Flight> result = runway.getQueue();
        if (!result.equals(expected)) {
            System.out.println("Error in getQueue: Expected " + expected + " but got " + result);
        }
        // Test removeFlight()
        // With valid input
        try {
            runway.removeFlight(flight2);
        }
        catch (RuntimeException exception) {
            System.out.println("Error in removeFlight(): Exception thrown for valid input.");
        }
        // With invalid input
        try {
            runway.removeFlight(null);
            System.out.println("Error in removeFlight(): Exception not thrown for invalid input.");
        }
        catch (RuntimeException exception) {
            // Expected
        }
        // Test with getQueue() to see if the queue has been updated as it should have
        expected.remove(flight2);
        result = runway.getQueue();
        if (!result.equals(expected)) {
            System.out.println("Error in getQueue: Expected " + expected + " but got " + result);
        }
        // Test toString() with nonempty runway
        String expectedString = "Runway 1: [Flight1]";
        String resultString = runway.toString();
        if (!resultString.equals(expectedString)) {
            System.out.println("Error in toString(): Expected " + expectedString + " but got " + resultString);
        }
        // Test toString() with empty runway
        runway.removeFlight(flight1);
        expectedString = "Runway 1: []";
        resultString = runway.toString();
        if (!resultString.equals(expectedString)) {
            System.out.println("Error in toString(): Expected " + expectedString + " but got " + resultString);
        }
    }
}
