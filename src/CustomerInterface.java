public interface CustomerInterface {

    public void bookFlight(Flight flightToBook);

    public void cancelFlight(Flight flightToCancel);

    public void displayFlights();

    public void displayFlights(int range);
}
