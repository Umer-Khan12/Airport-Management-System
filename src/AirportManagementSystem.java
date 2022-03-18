public class AirportManagementSystem {
    DialogIO gui;

    public AirportManagementSystem() {
        gui = new DialogIO();
        gui.outputString("Welcome to the Airport Management System");
    }

    public static void main(String[] args) {

        int userType = -1;
        String[] userTypeChoices = {"Sign in as:",
        "Passenger",
        "Staff Member",
        "Administrator",
        "Air Traffic Controller",
        "Exit"};

        AirportManagementSystem ams = new AirportManagementSystem();

        while (userType != 5) {
            try {
                userType = ams.gui.readChoice(userTypeChoices);
                // sign in as passenger
                if (userType == 1) {
                    // not yet implemented
                }
                // sign in as staff member
                else if (userType == 2) {
                    // not yet implemented
                }
                // sign in as administrator
                else if (userType == 3) {
                    // not yet implemented
                }
                // sign in as air traffic controller
                else if (userType == 4) {
                    // not yet implemented
                }
                // exit the application
                else if (userType == 5) {
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
