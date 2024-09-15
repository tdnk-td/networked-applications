import java.net.*;
import java.io.*;

public class SocketHandling {
    public void run(String SERVER_IP, int PORT, String FILENAME) {
        FileOutputStream fos = null;
        int bytes = 2048;

        try (
                Socket socket = new Socket(SERVER_IP, PORT);
                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());) {
            out.writeUTF(FILENAME);
            // Send the filename to the server

            if (in.readBoolean() == false) {
                // If the file does not exist, exit
                System.out.println("File not found");
                return;
            }

            System.out.println(in.readUTF());
            // Read the response from the server

            fos = new FileOutputStream(FILENAME + ".txt");
            // Create a file to write the data to

            // Receive the file byte and write it to the file
            byte[] buffer = new byte[bytes];
            int byteReaded = 0;
            while ((byteReaded = in.read(buffer)) > 0) {
                fos.write(buffer, 0, byteReaded);
            }

        } catch (Exception ex) {
            System.out.println(ex);
            ex.printStackTrace();
        }
    }
}
