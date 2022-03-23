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
    public Runway getRunway(int runwayId) {
        for (int i=0; i < runways.size(); i++) {
            if (runways.get(i).getId() == runwayId) {
                return runways.get(i);
            }
        }
        throw new RuntimeException("Error: Runway with id " + runwayId + " doesn't exist.");
    }

    @Override
    public void addFlightToAirspace(Flight flight) {
        // Appropriate Exceptions are thrown by the Runway class
        airspace.addFlight(flight);
        // Since a flight can only be either in the airspace or on a runway, remove it from a runway if it's there
        removeFlightFromRunway(flight);
    }

    @Override
    public void addFlightToRunway(Flight flight, int runwayId) {
        Runway destinationRunway = getRunway(runwayId);
        destinationRunway.addFlight(flight);
        removeFlightFromAirspace(flight);
    }

    @Override
    public void removeFlightFromAirspace(Flight flight) {
        if (airspace.getQueue().contains(flight)) {
            airspace.removeFlight(flight);
        }
    }

    @Override
    public void removeFlightFromRunway(Flight flight) {
        for (int i=0; i < runways.size(); i++) {
            Runway cur = runways.get(i);
            if (cur.getQueue().contains(flight)) {
                cur.removeFlight(flight);
                break;
            }
        }
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

    public static void main(String[] args) {
        // Unit Tests
        ATC atc = new ATC(1, "Ken", "Air Traffic Controller");
        // Tests for getRunway
        Runway runway1 = new Runway(1);
        Runway runway2 = new Runway(2);
        ArrayList<Runway> runways = new ArrayList<>();
        runways.add(runway1);
        runways.add(runway2);
        atc.runways = runways;
        // Test invalid input
        try {
            atc.getRunway(500);
            System.out.println("Error: No exception thrown for invalid input.");
        }
        catch (RuntimeException exception) {
            // Expected
        }
        // Test valid input
        Runway resultRunway = atc.getRunway(2);
        if (!resultRunway.equals(runway2)) {
            System.out.println("Error in getRunway(): Expected " + runway2 + " but got " + resultRunway);
        }

        // Tests for addFlightToAirspace()
        // Without the flight coming from a runway
        Flight flight145 = new Flight(145, "Saskatoon", "Toronto", 8, 130);
        atc.addFlightToAirspace(flight145);
        String expected = "Airspace: [Flight145]";
        String result = atc.getAirspace().toString();
        if (!result.equals(expected)) {
            System.out.println("Error in addFlightToAirspace(): Expected " + expected + " but got " + result);
        }

        // Tests for addFlightToRunway()
        // With the flight coming from the airspace
        atc.addFlightToRunway(flight145, 1);
        expected = "Runway 1: [Flight145]";
        result = atc.getRunway(1).toString();
        if (!result.equals(expected)) {
            System.out.println("Error in addFlightToRunway(): Expected " + expected + " but got " + result);
        }
        expected = "Airspace: []";
        result = atc.getAirspace().toString();
        if (!result.equals(expected)) {
            System.out.println("Error in addFlightToRunway(): Expected " + expected + " but got " + result);
        }
        // Without the flight coming from the airspace
        Flight flight177 = new Flight(177, "Saskatoon", "Vancouver", 500, 655);
        atc.addFlightToRunway(flight177, 1);
        expected = "Runway 1: [Flight145, Flight177]";
        result = atc.getRunway(1).toString();
        if (!result.equals(expected)) {
            System.out.println("Error in addFlightToRunway(): Expected " + expected + " but got " + result);
        }

        // Test addFlightToAirspace() with the flight coming from the runway
        atc.addFlightToAirspace(flight145);
        expected = "Airspace: [Flight145]";
        result = atc.getAirspace().toString();
        if (!result.equals(expected)) {
            System.out.println("Error in addFlightToAirspace(): Expected " + expected + " but got " + result);
        }
        expected = "Runway 1: [Flight177]";
        result = atc.getRunway(1).toString();
        if (!result.equals(expected)) {
            System.out.println("Error in addFlightToAirspace(): Expected " + expected + " but got " + result);
        }

        // Tests for removeFlightFromAirspace()
        // Invalid input
        atc.removeFlightFromAirspace(flight177);
        expected = "Airspace: [Flight145]";
        result = atc.getAirspace().toString();
        if (!result.equals(expected)) {
            System.out.println("Error in removeFlightFromAirspace(): Expected " + expected + " but got " + result);
        }
        expected = "Runway 1: [Flight177]";
        result = atc.getRunway(1).toString();
        if (!result.equals(expected)) {
            System.out.println("Error in removeFlightFromAirspace(): Expected " + expected + " but got " + result);
        }
        // Valid input
        atc.removeFlightFromAirspace(flight145);
        expected = "Airspace: []";
        result = atc.getAirspace().toString();
        if (!result.equals(expected)) {
            System.out.println("Error in removeFlightFromAirspace(): Expected " + expected + " but got " + result);
        }

        // Tests for removeFlightFromRunway()
        // Invalid input
        atc.removeFlightFromRunway(flight145);
        expected = "Runway 1: [Flight177]";
        result = atc.getRunway(1).toString();
        if (!result.equals(expected)) {
            System.out.println("Error in removeFlightFromRunway(): Expected " + expected + " but got " + result);
        }
        // Valid input
        atc.removeFlightFromRunway(flight177);
        expected = "Runway 1: []";
        result = atc.getRunway(1).toString();
        if (!result.equals(expected)) {
            System.out.println("Error in removeFlightFromRunway(): Expected " + expected + " but got " + result);
        }



        // REPL or GUI
        // TODO: Implement REPL or GUI for ATC
    }
}
