public interface ATCInterface extends StaffInterface{

    public void addFlightToAirspace();

    public void addFlightToRunway();

    public void removeFlight();

    public void updateFlight();

    public void addProtocol();

    public void removeProtocol();

    public void updateProtocol();

    public void executeProtocol();

    public void viewFlightQueue(Runway runway);
}
