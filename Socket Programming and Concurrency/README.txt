There are a total of 6 java files.
The main two files MyFileClient and MyFileServer.
MyFileClient uses the SocketHandling class.
    - SocketHandling
        - Sending and Recieving information from the server
MyFileServer uses the MultiThreadServer, ClientWorkerThread, and ServerStatistics class.
    - Multithread pool of 10
    - Handles the request and file transfers
    - Updates the amount of actions done