import java.util.ArrayList;
import java.util.Stack;

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

    public String timeIntToString(int timeInt) {
        // Helper method for converting from 4 digit int=abcd to String="ab:cd"
        Stack<Integer> s = new Stack<Integer>();
        String timeString = "";
        while (timeInt > 0){
            s.push(timeInt % 10);
            timeInt /= 10;
        }
        for (int i=0; i < 4; i++){
            timeString += s.pop();
            if (i == 1) {
                timeString += ":";
            }
        }
        return timeString;
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
                Flight cur = flightList.get(i);
                flightsToBeDisplayed += "Flight Number "+cur.id+" - "+cur.from+"->"+cur.to+" "
                                        +timeIntToString(cur.timeStart)+"-"+timeIntToString(cur.timeEnd)+".\n";
            }
        }
        return flightsToBeDisplayed;
    }

    @Override
    public void displayFlights(int range) {

    }

    public static void main(String[] args) {
        Passenger passenger = new Passenger(1, null);
        // Unit test for timeIntToString()
        int time1 = 1645;
        String expected = "16:45";
        String result = passenger.timeIntToString(time1);
        if (!expected.equals(result)){
            System.out.println("Error, Expected:\n" + expected + "but got:\n" + result);
        }

        // Unit tests for displayFlights()
        // Test displayFlights() with an empty flightList
        expected = "No flights currently booked.";
        result = passenger.displayFlights();
        if (!expected.equals(result)){
            System.out.println("Error, Expected:\n" + expected + "but got:\n" + result);
        }

        // Test displayFlights() with a single flight (and by manually adding to flightList)
        Flight flight01 = new Flight(1, "Saskatoon", "Toronto", 1100, 1300);
        passenger.flightList.add(flight01);
        expected = "Flight Number 1 - Saskatoon->Toronto 11:00-13:00.\n";
        result = passenger.displayFlights();
        if (!expected.equals(result)){
            System.out.println("Error, Expected:\n" + expected + "but got:\n" + result);
        }

        // Test displayFlights() with multiple flights (and by manually adding to flightList)
        Flight flight02 = new Flight(2, "Toronto", "Paris", 1500, 2330);
        passenger.flightList.add(flight02);
        expected += "Flight Number 2 - Toronto->Paris 15:00-23:30.\n";
        result = passenger.displayFlights();
        if (!expected.equals(result)){
            System.out.println("Error, Expected:\n" + expected + "but got:\n" + result);
        }
    }
}
