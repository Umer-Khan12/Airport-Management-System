public interface AdminInterface extends StaffInterface{

    public void viewFinances();

    public void viewStaffList();

    public void addStaff(int newID, String newName, String newJob);

    public void removeStaff(Staff staff);

    public void updateFinances();

    public void addFlightToDatabase();

    public void removeFlightFromDatabase();

    public void updateFlightInDatabase();
}
