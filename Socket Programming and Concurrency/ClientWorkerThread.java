import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.*;
import java.util.Calendar;

public class ClientWorkerThread extends Thread {
    Socket client;
    Calendar calendar = Calendar.getInstance();
    ServerStatistics serverStatistics = null;
    int bytes = 2048;
    InetAddress inet = null;
    File file = null;
    FileInputStream fis;

    ClientWorkerThread(Socket c) {
        client = c;
    }

    public void run() {
        inet = client.getInetAddress();
        serverStatistics = ServerStatistics.getInstance();

        try (
                DataInputStream in = new DataInputStream(client.getInputStream());
                DataOutputStream out = new DataOutputStream(client.getOutputStream());) {

            String fileName = in.readUTF();
            serverStatistics.incrementN();

            System.out.println("REQ " + serverStatistics.getN() + ": File " + fileName + " requested by "
                    + inet.getHostAddress());

            // Get the file from local directory
            file = new File(fileName);
            fis = new FileInputStream(fileName);

            if (file.exists()) {
                // Check if the file is available

                out.writeBoolean(true);
                serverStatistics.incrementM();
                out.writeUTF("File " + fileName + " found at server");
                out.writeUTF("Server handled " + serverStatistics.getN() + " requests, " + serverStatistics.getM()
                        + " requests were successful");
                out.writeUTF("Downloading file " + fileName);
                out.writeUTF("Download complete");

                // Transfer file to client
                byte[] buffer = new byte[bytes];
                int byteReaded = 0;
                while ((byteReaded = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, byteReaded);
                }

                System.out.println("REQ " + serverStatistics.getN() + ": Successful");

                System.out.println("REQ " + serverStatistics.getN() + ": Total successful requests so far = "
                        + serverStatistics.getM());
                System.out.println("REQ " + serverStatistics.getN() + ": File transfer complete");

            } else {
                out.writeUTF("File " + fileName + " not found at server");
                out.writeBoolean(false);
                System.out.println("REQ " + serverStatistics.getN() + ": Not Successful");
                System.out.println("REQ " + serverStatistics.getN() + ": Total successful requests so far = "
                        + serverStatistics.getM());
            }

        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
