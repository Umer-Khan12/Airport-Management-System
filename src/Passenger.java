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
    public void bookFlight() {

    }

    @Override
    public void cancelFlight() {

    }

    @Override
    public String displayFlights() {
        // Currently just prints and returns a string representation for testing purposes
        // TODO: proper display in a gui format
        String flightsToBeDisplayed = "";
        if (this.flightList.size() == 0) {
            flightsToBeDisplayed += "No flights currently booked.";
        } else {
            for (int i=0; i < flightList.size(); i++) {
                Flight cur = flightList.get(i);
                flightsToBeDisplayed += "Flight Number "+cur.id+" - "+cur.from+"->"+cur.to+" "
                                        +cur.timeStart+"-"+cur.timeEnd+".\n";
            }
        }
        System.out.println(flightsToBeDisplayed);
        return flightsToBeDisplayed;
    }

    @Override
    public void displayFlights(int range) {

    }

    public static void main(String[] args) {

    }
}
