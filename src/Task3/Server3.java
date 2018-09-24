package Task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * A simple class that opens a server socket, and prints each message received
 * to the console.
 * @author Graeme Stevenson (graeme.stevenson@ucd.ie)
 */
public class Server3 {

   /**
    * Accept this many connections.
    */
   private static final int my_backlog = 5;
   
   private int count = 0;

   /**
    * The server socket.
    */
   private ServerSocket my_serverSocket;
   


   /**
    * Create the server socket.
    * @param a_port the port that the server socket should listen on.
    */
   public Server3(int a_port) {
      try {
         my_serverSocket = new ServerSocket(a_port, my_backlog);
         System.out.println("TCP socket listening on port " + a_port);
      } catch (IOException ioe) {
         ioe.printStackTrace();
      } catch (SecurityException se) {
         se.printStackTrace();
      }
   }

   /**
    * Method that listens on the server socket forever and prints each incoming
    * message to the console.
    */
   public void listen() {
      while (true) {
         try {
            // Listens for a connection to be made to this socket.
            Socket socket = my_serverSocket.accept();

            // Wrap a buffered reader round the socket input stream.
            // Read the javadoc to understand why we do this rather than dealing
            // with reading from raw sockets.
            BufferedReader in = new BufferedReader(new InputStreamReader(socket
                  .getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            
            // Read in the message
           int data1 = in.read();
           int data2 = in.read();
           long result = data1 * data2;

            

            // Print the message to the console
            System.out.println("Recceived number: " + data1 + " & " + data2 + " = " + result);
            
            out.println("The result of " + data1 + "*" + data2 + " is " + result);
            System.out.println("Feedback has sent.");
            System.out.println("count: " + count++);
            
            // EXERCISE: Instead of printing out client messages to the console:
            // 1. Construct a response in the form "Your message is: <message>".
            // 2. Send the response back to the client.
            
            // tidy up
            in.close();
            out.close();
            socket.close();
         } catch (IOException ioe) {
            ioe.printStackTrace();
         } catch (SecurityException se) {
            se.printStackTrace();
         }
      }
   }
 

   /**
    * Parse the arguments to the program and create the server sst;port&gt;
    */
   public static void main(String[] args) {
      int port = 0;

      // Check we have the right number of arguments and parse
      if (args.length == 1) {
         try {
            port = Integer.valueOf(args[0]);
         } catch (NumberFormatException nfe) {
            System.err.println("The value provided for the port is not an integer");
            nfe.printStackTrace();
         }
         // Create the server
         Server3 server = new Server3(port);
         // Listen on the server socket. This will run until the program is
         // killed.
         server.listen();
      } else {
         System.out.println("Usage: java TCPSocketServer <port>");
      }

   }
} // end class

