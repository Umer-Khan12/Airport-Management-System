import java.util.ArrayList;

public class ATC extends Staff implements ATCInterface{
    private Runway airspace; // Technically not a runway but works like a runway
    private ArrayList<Runway> runways;
    private ArrayList<Protocol> protocols;

    public ATC(int id, String name, String job) {
        super(id, name, job);
        this.airspace = new Runway(-1); // Airspace will be represented with id=-1
        this.runways = new ArrayList<Runway>();
        this.protocols = new ArrayList<>();
    }

    // Accessors
    public Runway getAirspace() {return airspace;}
    public ArrayList<Runway> getRunways() {return runways;}
    public ArrayList<Protocol> getProtocols() {return protocols;}

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
    public void addProtocol(int id, String[] actions) {
        for (Protocol p : this.protocols) {
            if (p.getId() == id) {
                throw new RuntimeException("Error: Protocol with id " + id + " has already been added.");
            }
        }
        Protocol protocolToAdd = new Protocol(id);
        for (String action : actions) {
            protocolToAdd.getActionQueue().add(action);
        }
        this.protocols.add(protocolToAdd);
    }

    @Override
    public void removeProtocol(int id) {
        for (Protocol p : this.protocols) {
            if (p.getId() == id) {
                this.protocols.remove(p);
                return;
            }
        }
        throw new RuntimeException("Error: Protocol with id " + id + " does not exist.");
    }

    @Override
    public void updateProtocol(int id, String[] actions) {
        for (Protocol p : this.protocols) {
            if (p.getId() == id) {
                removeProtocol(id);
                addProtocol(id, actions);
                return;
            }
        }
        throw new RuntimeException("Error: Protocol with id " + id + " does not exist.");
    }

    @Override
    public Protocol executeProtocol(int id) {
        for (Protocol p : this.protocols) {
            if (p.getId() == id) {
                // Protocol object will be displayed using the GUI in the AirportManagementSystem class
                return p;
            }
        }
        throw new RuntimeException("Error: Protocol with id " + id + " does not exist.");
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

        // Tests for addProtocol()
        String[] actions1 = {"Direction 1", "Direction 2"};
        atc.addProtocol(1, actions1);
        if (!atc.getProtocols().get(0).getActionQueue().contains("Direction 1")) {
            System.out.println("Error in addProtocol(): action queue is not initialized correctly");
        }
        if (!atc.getProtocols().get(0).getActionQueue().contains("Direction 2")) {
            System.out.println("Error in addProtocol(): action queue is not initialized correctly");
        }
        int expectedId = 1;
        int resultId = atc.getProtocols().get(0).getId();
        if (resultId != expectedId) {
            System.out.println("Error in addProtocol(): Expected id " + expectedId + " but got " + resultId);
        }
        String[] actions3 = {"Placeholder action", "Placeholder action 2", "Placeholder action 3"};
        atc.addProtocol(3, actions3);
        if (atc.getProtocols().get(1).getActionQueue().size() != 3) {
            System.out.println("Error in addProtocol(): action queue has wrong number of actions");
        }
        try {
            atc.addProtocol(1, actions1);
            System.out.println("Error in addProtocol(): did not throw exception for duplicate id");
        }
        catch (RuntimeException exception) {
            // expected
        }

        // Tests for removeProtocol()
        try {
            atc.removeProtocol(1);
        }
        catch (RuntimeException exception) {
            System.out.println("Error in removeProtocol(): threw exception for valid input");
        }
        try {
            atc.removeProtocol(2);
            System.out.println("Error in removeProtocol(): did not throw exception for invalid input");
        }
        catch (RuntimeException exception) {
            // expected
        }

        // Tests for updateProtocol()
        String[] newActions3 = {"Remove flight from runway", "Add flight to runway"};
        try {
            atc.updateProtocol(3, newActions3);
            if (atc.getProtocols().get(0).getActionQueue().size() != 2) {
                System.out.println("Error in updateProtocol(): action queue not updated correctly");
            }
        }
        catch (RuntimeException exception) {
            System.out.println("Error in updateProtocol(): threw exception for valid input");
        }
        try {
            atc.updateProtocol(4, newActions3);
            System.out.println("Error in updateProtocol(): did not throw exception for invalid input");
        }
        catch (RuntimeException exception) {
            // expected
        }

        // Tests for executeProtocol()
        try {
            Protocol executedProtocol = atc.executeProtocol(3);
            if (executedProtocol.getId() != 3) {
                System.out.println("Error in executeProtocol(): protocol not returned correctly");
            }
        }
        catch (RuntimeException exception) {
            System.out.println("Error in executeProtocol(): threw exception for valid input");
        }
        try {
            Protocol invalidProtocol = atc.executeProtocol(5);
            System.out.println("Error in executeProtocol(): did not throw exception for invalid input");
        }
        catch (RuntimeException exception) {
            // expected
        }
    }
}
