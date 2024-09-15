import java.net.*;
import java.io.*;

public class MyFileClient {
    // User Client
    public static void main(String[] args) {
        String SERVER_IP = args[0];
        int PORT = Integer.parseInt(args[1]);
        String FILENAME = args[2];
        // Read the server address, port, and filename from the command line

        SocketHandling socketHandling = new SocketHandling();
        socketHandling.run(SERVER_IP, PORT, FILENAME);
        // SocketHandling class is used to handle the socket connection and file
        // transfer
    }
}