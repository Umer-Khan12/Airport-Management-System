import java.util.ArrayList;
public class Admin extends Staff implements AdminInterface{
    private ArrayList<Staff> staffList; //note: could use hashmap instead with id as the key?
    public Admin(int id, String name, String job) {
        super(id, name, job);
        staffList = new ArrayList<>();
    }
    
    /**
     * Given an id, returns the staff object if it exists
     * @param id staff id
     * @return the staff object if found, null otherwise
     */
    public Staff getSpecificStaff(int id) {
        //id should be unique, so a case where staffList has two Staff with the same id should be impossible
        for (Staff staff : staffList) {
            if (staff.id == id) {
                return staff;
            }
        }
        return null;
    }
    
    @Override
    public void viewStaffList() {
        for (int x=0;x<staffList.size();x++) {
            if (x==0) {System.out.println("ID  Name - Title");}
            System.out.println(staffList.get(x).id+". "+staffList.get(x).name+" - "+staffList.get(x).jobName);
        }
    }

    @Override
    public void addStaff(int newID, String newName, String newJob) {
        staffList.add(new Staff(newID, newName, newJob));
    }

    @Override
    public void removeStaff(Staff staff) {
        if (staffList.contains(staff)) {
            staffList.remove(staff);
        }
    }
    
    @Override
    public void viewFinances() {
        
    }
    
    @Override
    public void updateFinances() {
        
    }
    
    @Override
    public void viewFlight(int id) {
        
    }
    
    @Override
    public void viewCurrentlyIncoming() {
        
    }
    
    @Override
    public void viewCurrentlyOutgoing() {
        
    }
    
    @Override
    public void changePassengersFlight() {
        
    }
    
    public static void main(String[] args) {
        Admin a = new Admin(0, "testAdmin", "test");
        
        System.out.println("Testing viewStaffList() when stafflist is empty. (Expected result: Nothing is printed)");
        a.viewStaffList();
        System.out.println("-----------------------------------------------");
        
        //test - adding some staff to the list
        try {
            a.addStaff(0, "Staff A", "Job 1");
            a.addStaff(1, "Staff B", "Job 1");
            a.addStaff(2, "Staff C", "Job 2");
        } catch (Exception e) {
            System.err.println("Error: unexpected error from addStaff");
        }
        
        //test - getSpecificStaff when it doesn't exist
        System.out.println("Testing getSpecificStaff()...");
        if (a.getSpecificStaff(-1) != null) {
            System.err.println("Error: getSpecificStaff didn't return null when getting an invalid staff");
        }
        //test - getSpecificStaff when it exists
        if (a.getSpecificStaff(1) == null) {
            System.err.println("Error: getSpecificStaff returned null when getting a valid staff");
        }
        
        System.out.println("Testing viewStaffList() when stafflist has three elements. (Expected result: three staff details printed)");
        a.viewStaffList();
        System.out.println("-----------------------------------------------");
        
        //testing an attempt to remove staff that is in the list
        System.out.println("Testing removeStaff(), removing staff with id 1...");
        a.removeStaff(a.getSpecificStaff(1));
        a.viewStaffList();
        System.out.println("-----------------------------------------------");
        
        //testing an attempt to remove staff that isn't in the list
        System.out.println("Removing staff that isn't in the list (id -1)...");
        a.removeStaff(a.getSpecificStaff(-1));
        a.viewStaffList();
        System.out.println("-----------------------------------------------");
    }

    @Override
    public void addFlightToDatabase() {

    }

    @Override
    public void removeFlightFromDatabase() {

    }

    @Override
    public void updateFlightInDatabase() {

    }

    public static void main(String[] args) {}
}
