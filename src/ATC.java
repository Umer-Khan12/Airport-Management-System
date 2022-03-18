import java.util.ArrayList;

public class ATC extends Staff implements ATCInterface{
    private Runway airspace; // Technically not a runway but works like a runway
    private ArrayList<Runway> runways;

    public ATC(int id, String name, String job) {
        super(id, name, job);
        this.airspace = new Runway(-1); // Airspace will be represented with id=-1
        this.runways = new ArrayList<Runway>();
    }

    // Accessors
    public Runway getAirspace() {return airspace;}
    public ArrayList<Runway> getRunways() {return runways;}

    @Override
    public void addFlightToAirspace(Flight flight) {
        // Appropriate Exceptions are thrown by the Runway class
        airspace.addFlight(flight);
        // Since a flight can only be either in the airspace or on a runway, remove it from a runway if it's there
        removeFlightFromRunway(flight);
    }

    @Override
    public void addFlightToRunway(Flight flight, int runwayId) {

    }

    @Override
    public void removeFlightFromAirspace(Flight flight) {

    }

    @Override
    public void removeFlightFromRunway(Flight flight) {

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
    public void viewFlightQueue(int runwayId) {

    }

    public static void main(String[] args) {
        // Unit Tests
        // Tests for addFlightToAirspace()
        ATC atc = new ATC(1, "Ken", "Air Traffic Controller");
        Flight flight145 = new Flight(145, "Saskatoon", "Toronto", 8, 130);
        atc.addFlightToAirspace(flight145);
        String expected = "Airspace: [Flight145]";
        String result = atc.getAirspace().toString();
        if (!result.equals(expected)) {
            System.out.println("Error in addFlightToAirspace(): Expected " + expected + " but got " + result);
        }

        // REPL
        // TODO: Implement REPL for ATC
    }
}
