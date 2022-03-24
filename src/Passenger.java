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

    /**
     * method that adds a given flight to this passenger's flights
     * @param flightToBook the flight being booked
     */
    @Override
    public void bookFlight(Flight flightToBook) {
        if (flightToBook == null) {
            throw new RuntimeException("Error: Cannot book flight because flight is invalid.\n");
        }
        else if (flightList.contains(flightToBook)) {
            throw new RuntimeException("Error: Cannot book flight because flight has already been booked by passenger.\n");
        }
        else {
            this.flightList.add(flightToBook);
        }
    }

    /**
     * method that removes a given flight from this passenger's flights
     * @param flightToCancel the flight being canceled
     */
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
    public String displayFlights() {
        // Currently just returns a string representation for testing purposes
        // TODO: proper display in a gui format
        String flightsToBeDisplayed = "";
        if (this.flightList.size() == 0) {
            flightsToBeDisplayed += "No flights currently booked.";
        } else {
            for (int i=0; i < flightList.size(); i++) {
                // Each new flight is displayed on its own line
                Flight cur = flightList.get(i);
                // Add a colon between the 4 digit time string.
                String curString = cur.toString();
                flightsToBeDisplayed += curString.substring(0, curString.length()-8) + ":"
                        + curString.substring(curString.length()-8, curString.length()-3) + ":"
                        + curString.substring(curString.length()-3) + "\n";
            }
        }
        return flightsToBeDisplayed;
    }

    public String displayFlights(String from, String to) {
        String flightsToBeDisplayed = "";
        if(flightList.size() == 0){
            flightsToBeDisplayed += "No flights currently booked.";
        } else {
            for(Flight f: flightList){
                if(f.getFrom().equals(from) || f.getTo().equals(to)){
                    // Each new flight is displayed on its own line
                    // Add a colon between the 4 digit time string.
                    String curString = f.toString();
                    flightsToBeDisplayed += curString.substring(0, curString.length()-8) + ":"
                            + curString.substring(curString.length()-8, curString.length()-3) + ":"
                            + curString.substring(curString.length()-3) + "\n";
                }
            }
            if (flightsToBeDisplayed.equals("")) {
                flightsToBeDisplayed += "No flights currently booked.";
            }
        }
        return flightsToBeDisplayed;
    }

    public String displayFlights(int time){
        String flightsToBeDisplayed = "";
        if(flightList.size() == 0){
            flightsToBeDisplayed += "No flights currently booked.";
        } else {
            for(Flight f: flightList){
                if(f.getTimeStart() == time){
                    // Each new flight is displayed on its own line
                    // Add a colon between the 4 digit time string.
                    String curString = f.toString();
                    flightsToBeDisplayed += curString.substring(0, curString.length()-8) + ":"
                            + curString.substring(curString.length()-8, curString.length()-3) + ":"
                            + curString.substring(curString.length()-3) + "\n";
                }
            }
            if (flightsToBeDisplayed.equals("")) {
                flightsToBeDisplayed += "No flights currently booked.";
            }
        }
        return flightsToBeDisplayed;
    }

    public static void main(String[] args) {
        // Tests for Passenger class
        Flight flight01 = new Flight(1, "Saskatoon", "Toronto", 1400, 1600);
        Passenger passenger01 = new Passenger(1, flight01);

        // Test bookFlight with valid input
        Flight flight02 = new Flight(2, "Calgary", "Vancouver", 1200, 1330);
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

        // Test bookFlight with flight already booked by passenger
        try {
            passenger01.bookFlight(flight02);
            System.out.println("Error in bookFlight: exception was not thrown for flight already booked\n");
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
        Flight flight03 = new Flight(3, "Winnipeg", "Montreal", 1800, 1900);
        try {
            passenger01.cancelFlight(flight03);
            System.out.println("Error in cancelFlight: exception was not thrown for flight that was not booked\n");
        }
        catch (RuntimeException exception) {
            // expected
        }

        Passenger passenger02 = new Passenger(2, null);
        // Unit tests for displayFlights()
        // Test displayFlights() with an empty flightList
        String expected = "No flights currently booked.";
        String result = passenger02.displayFlights();
        if (!expected.equals(result)){
            System.out.println("Error, Expected:\n" + expected + "but got:\n" + result);
        }

        // Test displayFlights() with a single flight (and by manually adding to flightList)
        Flight flight04 = new Flight(1, "Saskatoon", "Toronto", 1100, 1300);
        passenger02.flightList.add(flight04);
        expected = "id: 1\tSaskatoon-->Toronto\t[11:00-13:00]\n";
        result = passenger02.displayFlights();
        if (!expected.equals(result)){
            System.out.println("Error, Expected:\n" + expected + "but got:\n" + result);
        }

        // Test displayFlights() with multiple flights (and by manually adding to flightList)
        Flight flight05 = new Flight(2, "Toronto", "Paris", 1500, 2330);
        passenger02.flightList.add(flight05);
        expected += "id: 2\tToronto-->Paris\t[15:00-23:30]\n";
        result = passenger02.displayFlights();
        if (!expected.equals(result)){
            System.out.println("Error, Expected:\n" + expected + "but got:\n" + result);
        }

        // Test displayFlights() with an early hour flight
        Flight flight06 = new Flight(3, "Paris", "Amsterdam", 5, 57);
        passenger02.flightList.add(flight06);
        expected += "id: 3\tParis-->Amsterdam\t[00:05-00:57]\n";
        result = passenger02.displayFlights();
        if (!expected.equals(result)){
            System.out.println("Error, Expected:\n" + expected + "but got:\n" + result);
        }

        // Integration test: use bookFlight() to add to flightList followed by displayFlight()
        Passenger passenger03 = new Passenger(3, null);
        passenger03.bookFlight(flight04);
        expected = "id: 1\tSaskatoon-->Toronto\t[11:00-13:00]\n";
        result = passenger03.displayFlights();
        if (!expected.equals(result)){
            System.out.println("Error, Expected:\n" + expected + "but got:\n" + result);
        }

        // Integration test: use cancelFlight() to remove from flightList followed by displayFlight()
        passenger03.cancelFlight(flight04);
        expected = "No flights currently booked.";
        result = passenger03.displayFlights();
        if (!expected.equals(result)){
            System.out.println("Error, Expected:\n" + expected + "but got:\n" + result);
        }

        // An example of the displayFlights() method in action (console version)
        passenger03.bookFlight(flight04);
        passenger03.bookFlight(flight05);
        passenger03.bookFlight(flight06);
        System.out.println(passenger03.displayFlights());

        // Test displayFlights with location parameters
        Passenger passenger04 = new Passenger(4, null);
        String testFlightList = passenger03.displayFlights("Paris", "Paris");
        if (testFlightList.equals("No flights currently booked.")) {
            System.out.println("Error in displayFlights: no flights displayed when flights with specified locations were booked");
        }
        testFlightList = passenger03.displayFlights("Nothing", "Nothing");
        if (!testFlightList.equals("No flights currently booked.")) {
            System.out.println("Error in displayFlights: flights displayed when no flights of specified locations were booked");
        }
        testFlightList = passenger04.displayFlights("Paris", "Paris");
        if (!testFlightList.equals("No flights currently booked.")) {
            System.out.println("Error in displayFlights: flights displayed when no flights booked");
        }

        // Test displayFlights with time parameter
        testFlightList = passenger03.displayFlights(1100);
        if (testFlightList.equals("No flights currently booked.")) {
            System.out.println("Error in displayFlights: no flights displayed when flights with specified time were booked");
        }
        testFlightList = passenger03.displayFlights(2399);
        if (!testFlightList.equals("No flights currently booked.")) {
            System.out.println("Error in displayFlights: flights displayed when no flights of specified time were booked");
        }
        testFlightList = passenger04.displayFlights(1100);
        if (!testFlightList.equals("No flights currently booked.")) {
            System.out.println("Error in displayFlights: flights displayed when no flights booked");
        }
    }
}
