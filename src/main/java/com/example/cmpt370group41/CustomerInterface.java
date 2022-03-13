package com.example.cmpt370group41;

public interface CustomerInterface {

    public void bookFlight(Flight flightToBook);

    public void cancelFlight(Flight flightToCancel);

    public String displayFlights();
}
