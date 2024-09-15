import java.rmi.*;

public interface Reservation extends java.rmi.Remote {
    String list () throws RemoteException;
    String passengerList() throws RemoteException;
    String reserve(String ticketClass, String name, int seatNumber) throws RemoteException;

}
