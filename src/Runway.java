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

    public static void main(String[] args) {}
}
