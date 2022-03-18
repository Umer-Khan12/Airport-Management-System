import javax.swing.*;

public class DialogIO {

    public DialogIO(){}

    /**
     * Display a prompt and read the string entered
     * @param prompt the String to be displayed as a prompt
     * @return the String read
     */
    public String readString(String prompt) {
        return (String) JOptionPane.showInputDialog(null, prompt);
    }

    /**
     * Display a prompt and read the integer entered
     * @param prompt the String to be displayed as a prompt
     * @return the integer read
     */
    public int readInt(String prompt) {
        while (true) {
            try {
                String userInput = (String) JOptionPane.showInputDialog(null, prompt);
                return Integer.parseInt(userInput);
            }
            catch (RuntimeException exception) {
                outputString("Invalid option, try again.");
            }
        }
    }

    /**
     * Output the String parameter
     * @param outString the String to be displayed
     */
    public void outputString(String outString) {
        JOptionPane.showMessageDialog(null, outString);
    }

    /**
     * Display the list of options and read an integer that is the index of one the options.
     * Index 0 is the default option.
     * @param options an array with the options that are presented to the user
     * @return the integer specifying the array index for the option selected by the user,
     * if the cancel or X button is clicked, return -1
     */
    public int readChoice(String[] options) {
        String selection = (String) JOptionPane.showInputDialog(null,
                "Select an option", // prompt
                "Choice Selection", // window title
                JOptionPane.QUESTION_MESSAGE, // type of message
                null, // icon displayed
                options, // choices
                options[0]); // initial selection
        if (selection == null) {
            return -1; // either cancel or X button was clicked
        }
        for (int i = 0; i < options.length; i++) {
            if (selection.equals(options[i])) {
                return i;
            }
        }
        JOptionPane.showMessageDialog(null, "Illegal choice: " + selection + "\n");
        return readChoice(options);
    }

    public static void main(String[] args) {

        DialogIO dialogTest = new DialogIO();

        // test readChoice()
        String[] exampleOptions = {"[0] Quit", "[1] Add Flight", "[2] Book Flight", "[3] Cancel Flight"};
        int selectedChoice = dialogTest.readChoice(exampleOptions);
        dialogTest.outputString("Entered: " + String.valueOf(selectedChoice));

        // test readString()
        String name = dialogTest.readString("Enter a name:");
        dialogTest.outputString("Entered: " + name);

        // test readInt()
        int number = dialogTest.readInt("Enter an integer:");
        dialogTest.outputString("Entered: " + number);
    }
}
