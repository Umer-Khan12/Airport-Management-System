public class Staff implements StaffInterface{
    public int id;
    public String name;
    public String jobName;

    public Staff(int id, String name, String job) {
        this.id = id;
        this.name = name;
        this.jobName = job;
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

    public void main(String[] args) {}
}
