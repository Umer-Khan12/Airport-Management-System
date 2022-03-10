import java.util.ArrayList;

public class Passenger implements CustomerInterface{
    public int ticket;
    public Flight flight;
    public ArrayList<Flight> flightList;

    public Passenger(int ticket, Flight flight) {
        this.ticket = ticket;
        this.flight = flight;
        this.flightList = new ArrayList<>();
    }

    @Override
    public void bookFlight(Flight flightToBook) {
        if (flightToBook == null) {
            throw new RuntimeException("Error: Cannot book flight because flight is invalid.\n");
        }
        else {
            this.flightList.add(flightToBook);
        }
    }

    @Override
    public void cancelFlight(Flight flightToCancel) {
        if (flightToCancel == null) {
            throw new RuntimeException("Error: Cannot cancel flight because flight is invalid.\n");
        }
        else if (!flightList.contains(flightToCancel)) {
            throw new RuntimeException("Error: Cannot cancel flight because flight was not booked.\n");
        }
        else {
            this.flightList.remove(flightToCancel);
        }
    }

    @Override
    public void displayFlights() {

    }

    @Override
    public void displayFlights(int range) {

    }

    public static void main(String[] args) {
        // Tests for Passenger class

        Flight flight01 = new Flight(0001, "Saskatoon", "Toronto", 0400, 0600);
        Passenger passenger01 = new Passenger(0001, flight01);

        // Test bookFlight with valid input
        Flight flight02 = new Flight(0002, "Calgary", "Vancouver", 1200, 1330);
        try {
            passenger01.bookFlight(flight02);
        }
        catch (RuntimeException exception) {
            System.out.println("Error in bookFlight: exception was thrown for valid input\n");
        }

        // Test bookFlight with invalid input
        try {
            passenger01.bookFlight(null);
            System.out.println("Error in bookFlight: exception was not thrown for invalid input\n");
        }
        catch (RuntimeException exception) {
            // expected
        }

        // Test cancelFlight with valid input
        try {
            passenger01.cancelFlight(flight02);
        }
        catch (RuntimeException exception) {
            System.out.println("Error in cancelFlight: exception was thrown for valid input\n");
        }

        // Test cancelFlight with invalid input
        try {
            passenger01.cancelFlight(null);
            System.out.println("Error in cancelFlight: exception was not thrown for invalid input\n");
        }
        catch (RuntimeException exception) {
            // expected
        }

        // Test cancelFlight with a flight not booked
        Flight flight03 = new Flight(0003, "Winnipeg", "Montreal", 1800, 1900);
        try {
            passenger01.cancelFlight(flight03);
            System.out.println("Error in cancelFlight: exception was not thrown for flight that was not booked\n");
        }
        catch (RuntimeException exception) {
            // expected
        }
    }
}
