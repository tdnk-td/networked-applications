import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;
import java.util.List;

public class rsvServer extends UnicastRemoteObject implements Reservation {

    private List<Ticket> listOfTickets;
    rsvServer(List<Ticket> tickets) throws RemoteException {
                super(); this.listOfTickets = tickets;
        System.out.println("Server Initialized");
    }

    @Override
    public String list() throws RemoteException {
        String returnMessage = "";
        String totalNumOfBseat = "";
        String totalNumOfEseat = "";
        int bCount500 = 0;
        int bCount800 = 0;
        int eCount200 = 0;
        int eCount300 = 0;
        int eCount450 = 0;

        for (int i = 0; i < listOfTickets.size(); i++) {
            Ticket ticket = listOfTickets.get(i);
            if (ticket.getSeatNumber() <= 5) {
                if (bCount800 < 2 && !ticket.isSeatTaken()) {
                    bCount800++;
                    totalNumOfBseat+= ticket.getSeatNumber() + ", ";
                } else if (!ticket.isSeatTaken()) {
                    bCount500++;
                    totalNumOfBseat+= ticket.getSeatNumber() + ", ";
                }
            } else {
                if (eCount450 < 5 && !ticket.isSeatTaken()) {
                    eCount450++;
                    totalNumOfEseat+= ticket.getSeatNumber() + ", ";
                } else if (eCount300 < 10 && !ticket.isSeatTaken()) {
                    eCount300++;
                    totalNumOfEseat+= ticket.getSeatNumber() + ", ";
                } else if (!ticket.isSeatTaken()) {
                    eCount200++;
                    totalNumOfEseat+= ticket.getSeatNumber() + ", ";
                }
            }
        }
        returnMessage += "business class:\n" + bCount500 + " seats at $500 each\n" + bCount800 + " seats at $800 each\n" + "Seat numbers: " + totalNumOfBseat
                + "\neconomy class:\n" + eCount200 + " seats at $200 each\n" + eCount300 + " seats at $300 each\n" + eCount450 + " seats at $450 each\n"
                + "Seat numbers: " + totalNumOfEseat + "\n";
        return returnMessage;
    }

    @Override
    public String passengerList() throws RemoteException {
        String returnMessage = "";
        for (int i = 0; i < listOfTickets.size(); i++) {
            Ticket ticket = listOfTickets.get(i);
            if(ticket.isSeatTaken()) {

                returnMessage+= ticket.getFirstName() + " " + ticket.getTicketClass() + " " + ticket.getSeatNumber();
            }
        }

        return returnMessage;
    }

    @Override
    public String reserve(String ticketClass, String name, int seatNumber) throws RemoteException {
        if (seatNumber < 1 || seatNumber > 30) {
            return "\nFailed to reserve: invalid seat number";
        }
        if (listOfTickets.get(seatNumber - 1).isSeatTaken()) {
            return "\nFailed to reserve: seat not available";
        }
        if (!listOfTickets.get(seatNumber - 1).getTicketClass().equals(ticketClass)) {
            return "\nFailed to reserve: seat not available due to wrong class type";
        }
        listOfTickets.get(seatNumber - 1).setFirstName(name);
        listOfTickets.get(seatNumber - 1).setSeatTaken(true);


        return "Successfully reserved seat #" + seatNumber + " for passenger " + name;
    }

    private static List<Ticket> generateListOfTickets() {
        List<Ticket> generateTickets = new ArrayList<>();

        generateTickets.add(new Ticket("", "business", false, 500, 1));
        generateTickets.add(new Ticket("", "business", false, 500, 2));
        generateTickets.add(new Ticket("", "business", false, 500, 3));
        generateTickets.add(new Ticket("", "business", false, 800, 4));
        generateTickets.add(new Ticket("", "business", false, 800, 5));
        generateTickets.add(new Ticket("", "economy", false, 200, 6));
        generateTickets.add(new Ticket("", "economy", false, 200, 7));
        generateTickets.add(new Ticket("", "economy", false, 200, 8));
        generateTickets.add(new Ticket("", "economy", false, 200, 9));
        generateTickets.add(new Ticket("", "economy", false, 200, 10));
        generateTickets.add(new Ticket("", "economy", false, 200, 11));
        generateTickets.add(new Ticket("", "economy", false, 200, 12));
        generateTickets.add(new Ticket("", "economy", false, 200, 13));
        generateTickets.add(new Ticket("", "economy", false, 200, 14));
        generateTickets.add(new Ticket("", "economy", false, 200, 15));
        generateTickets.add(new Ticket("", "economy", false, 300, 16));
        generateTickets.add(new Ticket("", "economy", false, 300, 17));
        generateTickets.add(new Ticket("", "economy", false, 300, 18));
        generateTickets.add(new Ticket("", "economy", false, 300, 19));
        generateTickets.add(new Ticket("", "economy", false, 300, 20));
        generateTickets.add(new Ticket("", "economy", false, 300, 21));
        generateTickets.add(new Ticket("", "economy", false, 300, 22));
        generateTickets.add(new Ticket("", "economy", false, 300, 23));
        generateTickets.add(new Ticket("", "economy", false, 300, 24));
        generateTickets.add(new Ticket("", "economy", false, 300, 25));
        generateTickets.add(new Ticket("", "economy", false, 450, 26));
        generateTickets.add(new Ticket("", "economy", false, 450, 27));
        generateTickets.add(new Ticket("", "economy", false, 450, 28));
        generateTickets.add(new Ticket("", "economy", false, 450, 29));
        generateTickets.add(new Ticket("", "economy", false, 450, 30));
        return generateTickets;
    }

    public static void main(String[] args) {
        try {
            rsvServer server = new rsvServer(generateListOfTickets());
            Naming.rebind("rmi://localhost/Reservation", server);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}

