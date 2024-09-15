public class Ticket {


    String firstName = "";
    String ticketClass;    // false = business, true = economy


    boolean seatTaken;      // 0 = not taken, taken
    int seatPrice;
    int seatNumber;


    public Ticket(String firstName, String ticketClass, boolean seatTaken, int seatPrice, int seatNumber) {
    this.firstName = firstName;
    this.ticketClass = ticketClass;
    this.seatTaken = seatTaken;
    this.seatPrice = seatPrice;
    this.seatNumber = seatNumber;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setSeatTaken(boolean seatTaken) {
        this.seatTaken = seatTaken;
    }
    public boolean isSeatTaken() {
        return seatTaken;
    }

    public String getName() {
        return firstName;
    }

    public String getTicketClass() {
       return ticketClass;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

}


