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
        "Display Flights",
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
        "Sign Out"};

        String[] atcActionChoices = {"Select a Task:",
        "View Flight Information",
        "View Incoming Flights",
        "View Outgoing Flights",
        "Change Passenger's Flight",
        "Add Flight",
        "Remove Flight",
        "Update Flight",
        "Add Protocol",
        "Remove Protocol",
        "Update Protocol",
        "Execute Protocol",
        "View Flight Queue",
        "Sign Out"};

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
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // cancel a flight
                        else if (passengerAction == 2) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // display flights
                        else if (passengerAction == 3) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
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
                        // add flight
                        else if (atcAction == 5) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // remove flight
                        else if (atcAction == 6) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // update flight
                        else if (atcAction == 7) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // add protocol
                        else if (atcAction == 8) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // remove protocol
                        else if (atcAction == 9) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // update protocol
                        else if (atcAction == 10) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // execute protocol
                        else if (atcAction == 11) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // view flight queue
                        else if (atcAction == 12) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
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
