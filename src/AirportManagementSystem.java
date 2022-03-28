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
     * @param parentFrame a parent frame to attach the dialog to, can be null
     */
    private void customerSignIn(int userID, JFrame parentFrame) {
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
        JDialog dialog = new JDialog(parentFrame, "Customer Page", true);
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        JButton button;
        GridBagConstraints c = new GridBagConstraints();
        
        //setting up dialog
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setLayout(new BorderLayout());
        
        //setting up buttons
        button = new JButton("Book A Flight");
        button.setFont(defaultFont);
        button.addActionListener(new ActionListener() {
            //display a list of flights as options, call bookFlight on the options when clicked
            @Override
            public void actionPerformed(ActionEvent e) {
                //setting up dialog
                JDialog bookingDialog = new JDialog(dialog, "Available Flight List", true);
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
                JDialog cancellingDialog = new JDialog(dialog, "Cancelling Flight", true);
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
            //Currently only exists the dialog, which would then let main continue
            @Override
            public void actionPerformed(ActionEvent e) {
                //could add a prompt asking if user is sure or a prompt to show dialog to user before ending
                dialog.dispose();
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
        
        dialog.add(panel);
        dialog.pack();
        dialog.setVisible(true);
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

        AirportManagementSystem ams = new AirportManagementSystem();
        ams.gui.outputString("Welcome to the Airport Management System");

        while (userType != -1 && userType != userTypeChoices.length - 1) {
            try {
                userType = ams.gui.readChoice(userTypeChoices);
                // sign in as passenger
                if (userType == 1) {
                    passengerAction = -2;
                    ams.customerSignIn(999, null); //different UI
                    /*
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
                    */
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
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // add flight to runway
                        else if (atcAction == 6) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // remove flight from airspace
                        else if (atcAction == 7) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // remove flight from runway
                        else if (atcAction == 8) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // add protocol
                        else if (atcAction == 9) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // remove protocol
                        else if (atcAction == 10) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // update protocol
                        else if (atcAction == 11) {
                            // not implemented yet
                            ams.gui.outputString("Feature not implemented yet.");
                        }
                        // execute protocol
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
