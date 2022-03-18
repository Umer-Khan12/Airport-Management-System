import java.util.ArrayList;

public class ATC extends Staff implements ATCInterface{
    private Runway airspace; // Technically not a runway but works like a runway
    private ArrayList<Runway> runways;

    public ATC(int id, String name, String job) {
        super(id, name, job);
    }

    @Override
    public void addFlightToAirspace() {

    }

    @Override
    public void addFlightToRunway() {

    }

    @Override
    public void removeFlight() {

    }

    @Override
    public void updateFlight() {

    }

    @Override
    public void addProtocol() {

    }

    @Override
    public void removeProtocol() {

    }

    @Override
    public void updateProtocol() {

    }

    @Override
    public void executeProtocol() {

    }

    @Override
    public void viewFlightQueue(Runway runway) {

    }

    public static void main(String[] args) {}
}
