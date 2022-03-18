public interface ATCInterface extends StaffInterface{

    public Runway getRunway(int runwayId);

    public void addFlightToAirspace(Flight flight);

    public void addFlightToRunway(Flight flight, int runwayId);

    public void removeFlightFromAirspace(Flight flight);

    public void removeFlightFromRunway(Flight flight);

    public void updateFlight();

    public void addProtocol();

    public void removeProtocol();

    public void updateProtocol();

    public void executeProtocol();

    public void viewFlightQueue(int runwayId);
}
