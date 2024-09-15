There are a total of 6 java files.
The main two files MyFileClient and MyFileServer.
MyFileClient uses the SocketHandling class.
    - SocketHandling
        - Sending and Recieving information from the server
MyFileServer uses the MultiThreadServer, ClientWorkerThread, and ServerStatistics class.
    - Multithread pool of 10
    - Handles the request and file transfers
    - Updates the amount of actions done

MyFileClient.java
    Upon running the java file, there will have to be three parameters that need to be fullfilled.
    The following parameters requirements are the: IP, PORT, and FILENAME
    It will then try to establish connection with the server via the ip and port and look for the file.
    If the file cannot be found, then the server will let the client know.
    An acknowledge should be sent at this moment letting the server know that the client has successfully established connection.

MyFileServer.java
    Upon running the java file, the server will have a specific socket that it is bound to, and it will wait and listen for
    client to establish a connection. An acknowledge from the client with their information should be recieved.
    The server will then attempt to try and locate the file, and read and write to the client the file that it is requesting.
    The server's function also allow for multiple clients to connect the server, which the maximum being 10; thus the function
    of the multithreading. With multithreading in mind, a function is there to intialize and assign the tasks when needed.
    The function that takes cares of the file transfer is mainly in the ClientWorkerThread, which establishes the required methods:
    - DataInputStream, DataOutputStream, and FileInputStream, to perform the file transfer.
    Firstly, the DataStreams were implemented to allow packets to send and recieve from, a boolean was created to verify if the file was
    located, then the FileInputStream to read and write the bytes from and to the client.
    Additionally, each of these functions/procedure has a counter from the ServerStatistics to ensure the global count of actions.

In this example, the transport layer is the main layer that we are dealing with, as the communication and data transfer
will be happening through here. This can be shown through the established connection between the client and server, and the
request for file transfer.