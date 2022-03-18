import java.util.ArrayList;

public class Runway {
    public int id;
    public ArrayList<Flight> queue;

    public Runway (int id) {
        this.id = id;
        this.queue = new ArrayList<>();
    }

    public static void main(String[] args) {}
}
