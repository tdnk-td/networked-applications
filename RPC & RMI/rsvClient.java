import java.rmi.*;

public class rsvClient {
    public static void main(String[] args) {
        try {
            Reservation client = (Reservation) Naming.lookup("rmi://localhost/Reservation");
            if (args[0].equals("list")){
                System.out.println(client.list());
            }
            if (args[0].equals("passengerlist")) {
                System.out.println(client.passengerList());
            }
            if (args[0].equals("reserve")) {
                System.out.println(client.reserve(args[1],args[2], Integer.parseInt(args[3])));
            }
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}