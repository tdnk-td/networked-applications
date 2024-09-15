import java.net.*;
import java.util.concurrent.*;

// Handle Individual thread requests from multiple clients
public class MultiThreadServer {

    // Maintain a queue of threads, and waiting threads are servred when
    // ClientWorkerThread is free
    public void run() {
        int PORT = 1234;
        int MAX_THREADS = 10;
        ServerSocket server = null;

        try {
            server = new ServerSocket(PORT);
            // This method creates a specified port and listens for client requests

            // Maintain pool of clients threas as maximum threads allowed at a time is 10
            ExecutorService executor = Executors.newFixedThreadPool(MAX_THREADS);
            // ExecutorService provides a pool of threads and an API for assigning tasks to
            // it

            while (true) {
                Socket client = server.accept();
                // This method blocks until either a client connects to the server on the
                // specified port or the socket times out

                executor.execute(new ClientWorkerThread(client));
                // Initializing the threads (assigning tasks)
            }
        } catch (Exception ex) {
            System.out.println(ex);
        } finally {
            try {
                server.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
}
