public class Passenger implements CustomerInterface{
    public int ticket;
    public Flight flight;

    public Passenger(int ticket, Flight flight) {
        this.ticket = ticket;
        this.flight = flight;
    }

    @Override
    public void bookFlight() {

    }

    @Override
    public void cancelFlight() {

    }

    @Override
    public void displayFlights() {

    }

    @Override
    public void displayFlights(int range) {

    }

    public void main(String[] args) {}
}
