package com.company;

import java.util.ArrayList;
import java.util.Arrays;

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
        else if (flightList.contains(flightToBook)) {
            throw new RuntimeException("Error: Cannot book flight because flight has already been booked by passenger.\n");
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
        if(flightList.size() == 0){
            System.out.println("No flights  currently booked.");
        }
        for (Flight f: flightList){
            System.out.println("-----------------------------------------------------------------------------------");
            System.out.println("Flight ID: "+f.getId()+"\nFrom: "+f.getFrom()+"\nStart: "+f.getTimeStart()+"\nEnd: "+f.getTimeEnd());
        }
    }


    public void displayFlights(String from, String to) {
        if(flightList.size() == 0){
            System.out.println("No Flights currently booked.");

        }
            for(Flight f: flightList){
                if(f.getFrom().equals(from)){
                    System.out.println("-----------------------------------------------------------------------------------");
                    System.out.println("Flight ID: "+f.getId()+"\nFrom: "+f.getFrom()+"\nStart: "+f.getTimeStart()+"\nEnd: "+f.getTimeEnd());
                }
                else if(f.getTo().equals(to)){
                    System.out.println("-----------------------------------------------------------------------------------");
                    System.out.println("Flight ID: "+f.getId()+"\nFrom: "+f.getFrom()+"\nStart: "+f.getTimeStart()+"\nEnd: "+f.getTimeEnd());
                }
                else if(f.getTo().equals(to) && f.getFrom().equals(from)){
                    System.out.println("-----------------------------------------------------------------------------------");
                    System.out.println("Flight ID: "+f.getId()+"\nFrom: "+f.getFrom()+"\nStart: "+f.getTimeStart()+"\nEnd: "+f.getTimeEnd());
                }

            }

    }

    public void displayFlights(int time){
        if(flightList.size() == 0){
            System.out.println("No Flights currently booked.");
        }
        for(Flight f: flightList){
            if(f.getTimeStart() == time){
                System.out.println("-----------------------------------------------------------------------------------");
                System.out.println("Flight ID: "+f.getId()+"\nFrom: "+f.getFrom()+"\nStart: "+f.getTimeStart()+"\nEnd: "+f.getTimeEnd());
            }
        }
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
        passenger01.bookFlight(flight02);
        passenger01.bookFlight(flight01);
        passenger01.bookFlight(flight03);



    }
}
