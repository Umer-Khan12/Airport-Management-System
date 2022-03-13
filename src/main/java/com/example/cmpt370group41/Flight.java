package com.example.cmpt370group41;

public class Flight {
    private int id;
    private String from;
    private String to;
    private int timeStart;
    private int timeEnd;

    public Flight(int id, String from, String to, int timeStart, int timeEnd) {
        if (id<0||!isTimeValid(timeStart)||!isTimeValid(timeEnd)) {
            throw new IllegalArgumentException("Error: Flight constructor received invalid values");
        }
        this.id = id;
        this.from = from;
        this.to = to;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    //accessors
    public int getId() {return id;}
    public String getFrom() {return from;}
    public String getTo() {return to;}
    public int getTimeStart() {return timeStart;}
    public int getTimeEnd() {return timeEnd;}

    //mutators
    public void setTimeStart(int timeStart) {
        if (!isTimeValid(timeStart)) {throw new IllegalArgumentException("Error: Tried to set a flight object's start time to an invalid time.\n");}
        this.timeStart = timeStart;
    }
    public void setTimeEnd(int timeEnd) {
        if (!isTimeValid(timeEnd)) {throw new IllegalArgumentException("Error: Tried to set a flight object's end time to an invalid time.\n");}
        this.timeEnd = timeEnd;
    }

    /**
     * helper method to check if a given 24hour time is valid
     * @param t the given time (24h format)
     * @return true if valid, false otherwise
     */
    private boolean isTimeValid(int t) {
        //if hour mark is off, then use %100 to check if minute mark is below 60
        if (t<0||t>2359||t%100>59) {return false;}
        return true;
    }

    @Override
    public String toString() {
        //adding leading zeros if times are between midnight and noon
        return "id: "+id+"\t"+from+"-->"+to+"\t["+String.format("%04d", timeStart)+"-"+String.format("%04d", timeEnd)+"]";
    }

    public static void main(String[] args) {
        Flight f = new Flight(0, "Airport A", "Airport B", 2300, 100);
        int invalidTimeList[] = {60, 199, -1600, 1678, 2400, 2360, 2500};
        if (!f.toString().equals("id: 0\tAirport A-->Airport B\t[2300-0100]")) {System.err.println("Error: toString returned unexpected string");}

        //time setting testing
        for (int x=0; x<invalidTimeList.length; x++) {
            try {
                f.setTimeStart(invalidTimeList[x]);
                System.err.println("Error: setTimeStart did not throw an exception when given an invalid time ("+invalidTimeList[x]+")\n");
            } catch (IllegalArgumentException e) {}
            try {
                f.setTimeEnd(invalidTimeList[x]);
                System.err.println("Error: setTimeEnd did not throw an exception when given an invalid time ("+invalidTimeList[x]+")\n");
            } catch (IllegalArgumentException e) {}
        }

        //try constructing invalid Flight objects
        try {
            Flight negativeID = new Flight(-42, "One", "Two", 0, 0);
            System.err.println("Error: Flight constructor didn't raise an error when given a negative id value.");
        } catch (IllegalArgumentException e) {}
        try {
            Flight invalidTimes = new Flight(-42, "One", "Two", 72, 0);
            System.err.println("Error: Flight constructor didn't raise an error when given a invalid starting time.");
        } catch (IllegalArgumentException e) {}
        try {
            Flight invalidTimes = new Flight(-42, "One", "Two", 2000, 2292);
            System.err.println("Error: Flight constructor didn't raise an error when given a invalid ending time.");
        } catch (IllegalArgumentException e) {}
    }
}
