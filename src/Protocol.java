import java.util.ArrayList;

public class Protocol {
    private int id;
    private ArrayList<String> actionQueue;

    public Protocol(int id) {
        this.id = id;
        this.actionQueue = new ArrayList<>();
    }

    // Accessors
    public int getId() {return id;}
    public ArrayList<String> getActionQueue() {return actionQueue;}

    public static void main(String[] args) {}
}
