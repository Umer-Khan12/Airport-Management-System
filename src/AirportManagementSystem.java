import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class AirportManagementSystem {
    DialogIO gui;

    public AirportManagementSystem() {
        gui = new DialogIO();
    }

    /**
     * Method to open up the interface a customer would see upon signing in
     * @param userID the user's id, obtained from sign in page
     */
    private void customerSignIn(int userID) {
        //arbitrary user data (would load the correct data here via a db)
        Passenger customerUser = new Passenger(userID);
        customerUser.flightList.add(new Flight(4, "AP1", "AP2", 1000, 1300));
        customerUser.flightList.add(new Flight(5, "AP2", "AP3", 1430, 1745));
        //arbitrary flight data, code to get flight data from db would go here
        //(would need to remove duplicate flights already found in a Passenger's booked flights)
        ArrayList<Flight> flights = new ArrayList<>();
        flights.add(new Flight(1, "A", "B", 800, 1240));
        flights.add(new Flight(2, "A", "C", 2200, 300));
        flights.add(new Flight(3, "A", "G", 1600, 1820));
        flights.add(new Flight(6, "G", "Z", 1910, 2200));
        
        Font defaultFont = new Font("Arial", Font.PLAIN, 20);
        JFrame frame = new JFrame("Customer Page");
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        JButton button;
        GridBagConstraints c = new GridBagConstraints();
        
        //setting up frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        
        //setting up buttons
        button = new JButton("Book A Flight");
        button.setFont(defaultFont);
        button.addActionListener(new ActionListener() {
            //display a list of flights as options, call bookFlight on the options when clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                //setting up dialog
                JDialog bookingDialog = new JDialog(frame, "Available Flight List", true);
                JPanel bookingPanel = new JPanel();
                JScrollPane scroll = new JScrollPane(bookingPanel);
                
                bookingDialog.setLayout(new BorderLayout());
                bookingDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                bookingDialog.add(scroll);
                
                bookingPanel.setLayout(new GridBagLayout());
                
                //generate options in the dialog here
                //currently only generating a list of buttons from the given available flight list
                c.fill = GridBagConstraints.HORIZONTAL;
                c.ipadx = 20;  //horizontal padding
                c.ipady = 0;   //vertical padding
                c.insets = new Insets(0,0,0,0);  //no padding
                c.weightx = 1.5;
                c.gridx = 0;
                c.gridy = 0;
                for (int x=0; x<flights.size(); x++) {
                    //label for flight info, button for booking
                    final Flight F = flights.get(x);//ref to flight for use on button clicks
                    JButton b = new JButton("Book");
                    b.setFont(defaultFont);
                    b.addActionListener(new ActionListener() {//call bookflight here
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //booking selected flight
                            customerUser.bookFlight(F);
                            JOptionPane.showMessageDialog(bookingDialog, "Flight "+F.getId()+" booked.");
                            flights.remove(F);//remove F from list of available flights for this user
                            //code to get payment from user goes here
                            
                            bookingDialog.dispose();//close booking dialog
                        }
                    });
                    
                    JLabel label = new JLabel(" "+flights.get(x).toString()+": ", SwingConstants.CENTER);
                    label.setFont(defaultFont);
                    //flight info on left
                    c.gridwidth = 5;
                    c.gridx = 0;
                    bookingPanel.add(label, c);
                    //button on right
                    c.gridwidth = 1;
                    c.gridx = 5;
                    bookingPanel.add(b, c);
                    c.gridy++;
                }
                bookingDialog.pack();
                bookingDialog.setVisible(true);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 340;  //horizontal padding
        c.ipady = 40;   //vertical padding
        c.weightx = 0.5;
        c.gridwidth = 3;//horizontal size of button = 3 buttons
        c.gridx = 0;    //column 0
        c.gridy = 0;    //row 0
        panel.add(button, c);
        
        button = new JButton("Cancel A Flight");
        button.setFont(defaultFont);
        button.addActionListener(new ActionListener() {
            //display a list of flights as options, call bookFlight on the options when clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                //if no flights to cancel
                if (customerUser.flightList.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "There are no booked flights to cancel.");
                    return;
                }
                //setting up dialog
                JDialog cancellingDialog = new JDialog(frame, "Cancelling Flight", true);
                JPanel cancelPanel = new JPanel();
                JScrollPane scroll = new JScrollPane(cancelPanel);
                
                cancellingDialog.setLayout(new BorderLayout());
                cancellingDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                cancellingDialog.add(scroll);
                
                cancelPanel.setLayout(new GridBagLayout());
                
                //generate options in the dialog here
                //currently only generating a list of buttons from the given available flight list
                c.fill = GridBagConstraints.HORIZONTAL;
                c.ipadx = 20;  //horizontal padding
                c.ipady = 0;   //vertical padding
                c.insets = new Insets(0,0,0,0);  //no padding
                c.weightx = 1.5;
                c.gridx = 0;
                c.gridy = 0;
                for (int x=0; x<customerUser.flightList.size(); x++) {
                    //label for flight info, button for booking
                    final Flight F = customerUser.flightList.get(x);//ref to flight for use on button clicks
                    JButton b = new JButton("Cancel");
                    b.setFont(defaultFont);
                    b.addActionListener(new ActionListener() {//call cancelFlight here
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //cancelling selected flight
                            customerUser.cancelFlight(F);
                            JOptionPane.showMessageDialog(cancellingDialog, "Flight "+F.getId()+" cancelled.");
                            flights.add(F);//remove F from list of available flights for this user
                            //refunding code goes here
                            
                            cancellingDialog.dispose();//close booking dialog
                        }
                    });
                    
                    JLabel label = new JLabel(" "+customerUser.flightList.get(x).toString()+": ", SwingConstants.CENTER);
                    label.setFont(defaultFont);
                    //flight info on left
                    c.gridwidth = 5;
                    c.gridx = 0;
                    cancelPanel.add(label, c);
                    //button on right
                    c.gridwidth = 1;
                    c.gridx = 5;
                    cancelPanel.add(b, c);
                    c.gridy++;
                }
                cancellingDialog.pack();
                cancellingDialog.setVisible(true);
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 340;  //horizontal padding
        c.ipady = 40;   //vertical padding
        c.weightx = 0.5;
        c.gridwidth = 3;//horizontal size of button = 3 buttons
        c.gridx = 0;    //column 0
        c.gridy = 1;    //row 1
        panel.add(button, c);

        button = new JButton("Sign Out");
        button.setFont(defaultFont);
        button.addActionListener(new ActionListener() {
            //return to login state here
            @Override
            public void actionPerformed(ActionEvent e) {
                //(need whoever's doing the login page to be done before anything could be done here)
                
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 340;  //horizontal padding
        c.ipady = 40;   //vertical padding
        c.weightx = 0.5;
        c.gridwidth = 3;//horizontal size of button = 3 buttons
        c.gridx = 0;    //column 0
        c.gridy = 2;    //row 2
        panel.add(button, c);

        button = new JButton("Exit");
        button.setFont(defaultFont);
        button.addActionListener(new ActionListener() {
            //Quit the application here
            //unsure if this part is neccessary when exiting the window also closes the program
            @Override
            public void actionPerformed(ActionEvent e) {
                //could add a prompt asking if user is sure or a prompt to show dialog to user before ending
                frame.dispose();
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 340;  //horizontal padding
        c.ipady = 20;   //vertical padding
        c.weightx = 0.0;
        c.insets = new Insets(10,0,0,0); //add a little bit of padding at the top to seperate from rest of options
        c.gridwidth = 3;//horizontal size of button = 3 buttons
        c.gridx = 0;    //column 0
        c.gridy = 3;    //row 3
        panel.add(button, c);
        
        button = new JButton("Display Flights (DEBUG, Unsure what to do here still)");
        button.setFont(defaultFont);
        button.addActionListener(new ActionListener() {
            //Display list of flight?
            @Override
            public void actionPerformed(ActionEvent e) {
                //TODO
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 340;  //horizontal padding
        c.ipady = 0;    //no vertical padding
        c.insets = new Insets(0,0,10,0);  //bottom padding
        c.gridwidth = 2;   //2 columns wide
        c.gridx = 0;       //column 0
        c.gridy = 4;       //row 4
        panel.add(button, c);
        
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
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
