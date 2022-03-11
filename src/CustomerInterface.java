public interface CustomerInterface {

    public void bookFlight(Flight flightToBook);

    public void cancelFlight(Flight flightToCancel);

    public String displayFlights();

    public void displayFlights(int range);
}
