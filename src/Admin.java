import java.util.ArrayList;
public class Admin extends Staff implements AdminInterface{
    private ArrayList<Staff> staffList;
    public Admin(int id, String name, String job) {
        super(id, name, job);
        staffList = new ArrayList<>();
    }

    @Override
    public void viewFinances() {

    }

    @Override
    public void viewStaffList() {
        for (int x=0;x<staffList.size();x++) {
            System.out.println(staffList.get(x).id+"\t"+staffList.get(x).name+"\t"+staffList.get(x).jobName);
        }
    }

    @Override
    public void addStaff() {
        staffList.add(new Staff(newID, newName, newJob));
    }

    @Override
    public void removeStaff() {
        if (staffList.contains(staff)) {
            staffList.remove(staff);
        }
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

    public static void main(String[] args) {}
}
