public class ServerStatistics {
    // Maintain server statics i.e number N and M

    // The numbers N and M are updated by individual threads
    private static int N = 0;
    private static int M = 0;
    private static ServerStatistics instance = null;

    // Private constructor
    private ServerStatistics() {
    }

    // Get the instance of the ServerStatistics class
    public static ServerStatistics getInstance() {
        if (instance == null) {
            // Ensure that only one thread can access this block
            synchronized (ServerStatistics.class) {
                if (instance == null) {
                    instance = new ServerStatistics();
                }
            }
        }

        return instance;
    }

    // Increment the number of total requests
    public void incrementN() {
        N++;
    }

    // Increment the number of successful responses
    public void incrementM() {
        M++;
    }

    // Get the number of total requests
    public int getN() {
        return N;
    }

    // Get the number of successful responses
    public int getM() {
        return M;
    }
}
