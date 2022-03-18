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
        // Test getQueue
        Flight flight2 = new Flight(2, "Toronto", "Paris", 330, 1030);
        runway.addFlight(flight2);
        ArrayList<Flight> expected = new ArrayList<>();
        expected.add(flight1);
        expected.add(flight2);
        ArrayList<Flight> result = runway.getQueue();
        if (!result.equals(expected)) {
            System.out.println("Error in getQueue: Expected " + expected + " but got " + result);
        }
    }
}
