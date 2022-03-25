import java.util.ArrayList;

public class AirportManagementSystem {
    DialogIO gui;

    public AirportManagementSystem() {
        gui = new DialogIO();
    }

    public static void main(String[] args) {

        int userType = -2;
        int passengerAction;
        int staffAction;
        int adminAction;
        int atcAction;

        String[] userTypeChoices = {"Sign in as:",
        "Passenger",
        "Staff Member",
        "Administrator",
        "Air Traffic Controller",
        "Exit"};

        String[] passengerActionChoices = {"Select a Task:",
        "Book a Flight",
        "Cancel a Flight",
        "Display All Booked Flights",
        "Display Booked Flights by Location",
        "Display Booked Flights by Departure Time",
        "Sign Out"};

        String[] staffActionChoices = {"Select a Task:",
        "View Flight Information",
        "View Incoming Flights",
        "View Outgoing Flights",
        "Change Passenger's Flight",
        "Sign Out"};

        String[] adminActionChoices = {"Select a Task:",
        "View Flight Information",
        "View Incoming Flights",
        "View Outgoing Flights",
        "Change Passenger's Flight",
        "View Staff List",
        "Add Staff",
        "Remove Staff",
        "View Finances",
        "Update Finances",
        "Add Flight To Database",
        "Remove Flight From Database",
        "Update Flight In Database",
        "Sign Out"};

        String[] atcActionChoices = {"Select a Task:",
        "View Flight Information",
        "View Incoming Flights",
        "View Outgoing Flights",
        "Change Passenger's Flight",
        "Add Flight to Airspace",
        "Add Flight to Runway",
        "Remove Flight from Airspace",
        "Remove Flight from Runway",
        "Add Protocol",
        "Remove Protocol",
        "Update Protocol",
        "Execute Protocol",
        "Sign Out"};

        // initialize user types
        Passenger passenger = new Passenger(1);
        Admin admin = new Admin(1, "Sophie", "Administrator");
        ATC atc = new ATC(1, "Ken", "Air Traffic Controller");
        // ATC is initialized with one runway
        Runway runway = new Runway(1);
        atc.getRunways().add(runway);

        // initialize flight database with some demo flights
        ArrayList<Flight> database = new ArrayList<>();
        Flight demoFlight1 = new Flight(1, "Saskatoon", "Vancouver", 1300, 1500);
        Flight demoFlight2 = new Flight(2, "Regina", "Montreal", 1800, 2000);
        Flight demoFlight3 = new Flight(3, "Calgary", "Winnipeg", 1100, 1200);
        Flight demoFlight4 = new Flight(4, "Edmonton", "Ottawa", 700, 900);
        database.add(demoFlight1);
        database.add(demoFlight2);
        database.add(demoFlight3);
        database.add(demoFlight4);

        AirportManagementSystem ams = new AirportManagementSystem();
        ams.gui.outputString("Welcome to the Airport Management System");

        while (userType != -1 && userType != userTypeChoices.length - 1) {
            try {
                userType = ams.gui.readChoice(userTypeChoices);
                // sign in as passenger
                if (userType == 1) {
                    passengerAction = -2;
                    while (passengerAction != -1 && passengerAction != passengerActionChoices.length - 1) {
                        passengerAction = ams.gui.readChoice(passengerActionChoices);
                        // book a flight
                        if (passengerAction == 1) {
                            int flightToAdd = ams.gui.readInt("Enter the ID of the flight to book:");
                            boolean flightBooked = false;
                            for (Flight flight : database) {
                                if (flight.getId() == flightToAdd) {
                                    try {
                                        passenger.bookFlight(flight);
                                        ams.gui.outputString("Flight booked.");
                                    }
                                    catch (RuntimeException exception) {
                                        ams.gui.outputString("You have already booked this flight.");
                                    }
                                    flightBooked = true;
                                    break;
                                }
                            }
                            if (!flightBooked) {
                                ams.gui.outputString("Flight with ID " + flightToAdd + " does not exist.");
                            }
                        }
                        // cancel a flight
                        else if (passengerAction == 2) {
                            int flightToCancel = ams.gui.readInt("Enter the ID of the flight to cancel:");
                            boolean flightCancelled = false;
                            for (Flight flight : database) {
                                if (flight.getId() == flightToCancel) {
                                    try {
                                        passenger.cancelFlight(flight);
                                        ams.gui.outputString("Flight cancelled.");
                                    }
                                    catch (RuntimeException exception) {
                                        ams.gui.outputString("You have not booked this flight.");
                                    }
                                    flightCancelled = true;
                                    break;
                                }
                            }
                            if (!flightCancelled) {
                                ams.gui.outputString("Flight with ID " + flightToCancel + " does not exist.");
                            }
                        }
                        // display all booked flights
                        else if (passengerAction == 3) {
                            ams.gui.outputString(passenger.displayFlights());
                        }
                        // display booked flights by location
                        else if (passengerAction == 4) {
                            String fromLocation = ams.gui.readString("Enter the location you a going from:");
                            String toLocation = ams.gui.readString("Enter the location you are going to:");
                            String matchingFlights = passenger.displayFlights(fromLocation, toLocation);
                            if (matchingFlights.equals("No flights currently booked.")) {
                                ams.gui.outputString("No flights currently booked with matching locations either from "
                                        + fromLocation + " or to " + toLocation + ".");
                            }
                            else {
                                ams.gui.outputString("Displaying flights with matching locations either from "
                                        + fromLocation + " or to " + toLocation + ":\n" + matchingFlights);
                            }
                        }
                        // display booked flights by departure time
                        else if (passengerAction == 5) {
                            int departureTime = ams.gui.readInt("Enter the time of departure:");
                            String matchingFlights = passenger.displayFlights(departureTime);
                            if (matchingFlights.equals("No flights currently booked.")) {
                                ams.gui.outputString("No flights currently booked with departure time "
                                        + departureTime + ".");
                            }
                            else {
                                ams.gui.outputString("Displaying flights with departure time "
                                        + departureTime + ":\n" + matchingFlights);
                            }
                        }
                        // sign out
                        else if (passengerAction == -1 || passengerAction == passengerActionChoices.length - 1) {
                            // do nothing
                        }
                        else {
                            ams.gui.outputString("Invalid option, try again.");
                        }
                    }
                }
                // sign in as staff member
                else if (userType == 2) {
                    staffAction = -2;
                    while (staffAction != -1 && staffAction != staffActionChoices.length - 1) {
                        staffAction = ams.gui.readChoice(staffActionChoices);
                        // view flight information
                        if (staffAction == 1) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // view incoming flights
                        else if (staffAction == 2) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // view outgoing flights
                        else if (staffAction == 3) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // change passenger's flight
                        else if (staffAction == 4) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // sign out
                        else if (staffAction == -1 || staffAction == staffActionChoices.length - 1) {
                            // do nothing
                        }
                        else {
                            ams.gui.outputString("Invalid option, try again.");
                        }
                    }
                }
                // sign in as administrator
                else if (userType == 3) {
                    adminAction = -2;
                    while (adminAction != -1 && adminAction != adminActionChoices.length - 1) {
                        adminAction = ams.gui.readChoice(adminActionChoices);
                        // view flight information
                        if (adminAction == 1) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // view incoming flights
                        else if (adminAction == 2) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // view outgoing flights
                        else if (adminAction == 3) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // change passenger's flight
                        else if (adminAction == 4) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // view staff list
                        else if (adminAction == 5) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // add staff
                        else if (adminAction == 6) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // remove staff
                        else if (adminAction == 7) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // view finances
                        else if (adminAction == 8) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // update finances
                        else if (adminAction == 9) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // add flight to database
                        else if (adminAction == 10) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // remove flight from database
                        else if (adminAction == 11) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // update flight in database
                        else if (adminAction == 12) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // sign out
                        else if (adminAction == -1 || adminAction == adminActionChoices.length - 1) {
                            // do nothing
                        }
                        else {
                            ams.gui.outputString("Invalid option, try again.");
                        }
                    }
                }
                // sign in as air traffic controller
                else if (userType == 4) {
                    atcAction = -2;
                    while (atcAction != -1 && atcAction != atcActionChoices.length - 1) {
                        atcAction = ams.gui.readChoice(atcActionChoices);
                        // view flight information
                        if (atcAction == 1) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // view incoming flights
                        else if (atcAction == 2) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // view outgoing flights
                        else if (atcAction == 3) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // change passenger's flight
                        else if (atcAction == 4) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // add flight to airspace
                        else if (atcAction == 5) {
                            int flightToAdd = ams.gui.readInt("Enter the ID of the flight to add to airspace:");
                            boolean flightAdded = false;
                            for (Flight flight : database) {
                                if (flight.getId() == flightToAdd) {
                                    try {
                                        atc.addFlightToAirspace(flight);
                                        ams.gui.outputString("Flight added to airspace.");
                                    }
                                    catch (RuntimeException exception) {
                                        ams.gui.outputString("Flight is already in airspace.");
                                    }
                                    flightAdded = true;
                                    break;
                                }
                            }
                            if (!flightAdded) {
                                ams.gui.outputString("Flight with ID " + flightToAdd + " does not exist.");
                            }
                        }
                        // add flight to runway, currently only implemented for cases with one runway
                        else if (atcAction == 6) {
                            int flightToAdd = ams.gui.readInt("Enter the ID of the flight to add to runway:");
                            boolean flightAdded = false;
                            for (Flight flight : database) {
                                if (flight.getId() == flightToAdd) {
                                    try {
                                        atc.addFlightToRunway(flight, 1);
                                        ams.gui.outputString("Flight added to runway.");
                                    }
                                    catch (RuntimeException exception) {
                                        ams.gui.outputString("Flight is already on runway.");
                                    }
                                    flightAdded = true;
                                    break;
                                }
                            }
                            if (!flightAdded) {
                                ams.gui.outputString("Flight with ID " + flightToAdd + " does not exist.");
                            }
                        }
                        // remove flight from airspace
                        else if (atcAction == 7) {
                            int flightToRemove = ams.gui.readInt("Enter the ID of the flight to remove from airspace:");
                            boolean flightRemoved = false;
                            for (Flight flight : database) {
                                if (flight.getId() == flightToRemove) {
                                    atc.removeFlightFromAirspace(flight);
                                    ams.gui.outputString("Flight removed from airspace.");
                                    flightRemoved = true;
                                    break;
                                }
                            }
                            if (!flightRemoved) {
                                ams.gui.outputString("Flight with ID " + flightToRemove + " does not exist.");
                            }
                        }
                        // remove flight from runway
                        else if (atcAction == 8) {
                            int flightToRemove = ams.gui.readInt("Enter the ID of the flight to remove from runway:");
                            boolean flightRemoved = false;
                            for (Flight flight : database) {
                                if (flight.getId() == flightToRemove) {
                                    atc.removeFlightFromRunway(flight);
                                    ams.gui.outputString("Flight removed from runway.");
                                    flightRemoved = true;
                                    break;
                                }
                            }
                            if (!flightRemoved) {
                                ams.gui.outputString("Flight with ID " + flightToRemove + " does not exist.");
                            }
                        }
                        // add protocol
                        else if (atcAction == 9) {
                            int protocolID = ams.gui.readInt("Enter the ID of the protocol to add:");
                            // check for duplicate ID
                            boolean validID = true;
                            for (Protocol p : atc.getProtocols()) {
                                if (p.getId() == protocolID) {
                                    validID = false;
                                    break;
                                }
                            }
                            if (validID) {
                                int numberActions = ams.gui.readInt("Enter the number of actions the protocol has:");
                                String[] actions = new String[numberActions];
                                for (int i = 1; i <= numberActions; i++) {
                                    actions[i - 1] = ams.gui.readString("Enter action " + i + " out of " + numberActions + ":");
                                }
                                atc.addProtocol(protocolID, actions);
                                ams.gui.outputString("Protocol added.");
                            }
                            else {
                                ams.gui.outputString("Protocol with ID " + protocolID + " already exists.");
                            }
                        }
                        // remove protocol
                        else if (atcAction == 10) {
                            int protocolRemoveID = ams.gui.readInt("Enter the ID of the protocol to remove:");
                            try {
                                atc.removeProtocol(protocolRemoveID);
                                ams.gui.outputString("Protocol removed.");
                            }
                            catch (RuntimeException exception) {
                                ams.gui.outputString("Protocol with ID " + protocolRemoveID + " does not exist.");
                            }
                        }
                        // update protocol
                        else if (atcAction == 11) {
                            int protocolUpdateID = ams.gui.readInt("Enter the ID of the protocol to update:");
                            try {
                                atc.removeProtocol(protocolUpdateID);
                                int newNumberActions = ams.gui.readInt("Enter the number of actions the protocol has:");
                                String[] newActions = new String[newNumberActions];
                                for (int i = 1; i <= newNumberActions; i++) {
                                    newActions[i - 1] = ams.gui.readString("Enter action " + i + " out of " + newNumberActions + ":");
                                }
                                atc.addProtocol(protocolUpdateID, newActions);
                                ams.gui.outputString("Protocol updated.");
                            }
                            catch (RuntimeException exception) {
                                ams.gui.outputString("Protocol with ID " + protocolUpdateID + " does not exist.");
                            }
                        }
                        // execute protocol
                        else if (atcAction == 12) {
                            int protocolExecuteID = ams.gui.readInt("Enter the ID of the protocol to execute:");
                            try {
                                Protocol protocolExecute = atc.executeProtocol(protocolExecuteID);
                                for (String actionExecute : protocolExecute.getActionQueue()) {
                                    ams.gui.outputString(actionExecute);
                                }
                                ams.gui.outputString("Protocol executed.");
                            }
                            catch (RuntimeException exception) {
                                ams.gui.outputString("Protocol with ID " + protocolExecuteID + " does not exist.");
                            }
                        }
                        // sign out
                        else if (atcAction == -1 || atcAction == atcActionChoices.length - 1) {
                            // do nothing
                        }
                        else {
                            ams.gui.outputString("Invalid option, try again.");
                        }
                    }
                }
                // exit the application
                else if (userType == -1 || userType == userTypeChoices.length - 1) {
                    // do nothing
                }
                else {
                    ams.gui.outputString("Invalid option, try again.");
                }
            }
            catch (RuntimeException exception) {
                ams.gui.outputString(exception.getMessage());
            }
        }
    }
}
