public interface ATCInterface extends StaffInterface{

    public Runway getRunway(int runwayId);

    public void addFlightToAirspace(Flight flight);

    public void addFlightToRunway(Flight flight, int runwayId);

    public void removeFlightFromAirspace(Flight flight);

    public void removeFlightFromRunway(Flight flight);

    public void addProtocol(int id, String[] actions);

    public void removeProtocol(int id);

    public void updateProtocol(int id, String[] actions);

    public Protocol executeProtocol(int id);
}
