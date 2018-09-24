package Task3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;

/**
 * A simple class that opens a socket, sends a message to the server, and
 * terminates.
 * @author Graeme Stevenson (graeme.stevenson@ucd.ie)
 */
public class Client3 {

   /**
    * The server host name.
    */
   public String my_serverHost;

   /**
    * The server port.
    */
   public int my_serverPort;

   /**
    * Sets the client up with the server details.
    * @param the_serverHost the server host name.
    * @param the_serverPort the server port.
    */
   public Client3(String the_serverHost, int the_serverPort) {
      my_serverHost = the_serverHost;
      my_serverPort = the_serverPort;
   }

   /**
    * Creates a connection to the server and sends a message.
    * @param a_message the message to send to the server.
    */
   public void sendMessage(int data1, int data2) {
      try {
         // Create a connection to the server.
         Socket toServer = new Socket(my_serverHost, my_serverPort);

         // Wrap a PrintWriter round the socket output stream.
         // Read the javadoc to understand (1) the method arguments, and (2) why
         // we do this rather than writing to raw sockets.
         BufferedReader in = new BufferedReader(new InputStreamReader(toServer.getInputStream()));
         PrintWriter out = new PrintWriter(toServer.getOutputStream(), true);
         
         // Write the message to the socket.
         char[] message = {(char) data1,(char) data2};
         out.println(message);
         
         //
         String line = null;
         while ((line = in.readLine()) != null) {
      	  	 System.out.println(line);
      	 }
      	 System.out.println("Feedback has recieved");
         
         // tidy up
      	 in.close();
         out.close();
         toServer.close();
      } catch (IOException ioe) {
         ioe.printStackTrace();
      } catch (SecurityException se) {
         se.printStackTrace();
      }
   }

   /**
    * Parse the arguments to the program, create a socket, and send a message.
    * @param args the program arguments. Should take the form &lt;host&gt;
    *           &lt;port&gt; &lt;message&gt;
    */
   public static void main(String[] args) {

      String host = null;
      int port = 0;
      int data1 = 0,data2 = 0;
      Random ran=new Random();

      // Check we have the right number of arguments and parse
      if (args.length == 2) {
         host = args[0];
         try {
            port = Integer.valueOf(args[1]);
         } catch (NumberFormatException nfe) {
            System.err.println("The value provided for the port is not an integer");
            nfe.printStackTrace();
         }

         // Create the client
         Client3 client;
         // Send a message using the client
         for ( int k=0 ; k<=299 ; k++) {
        	 client = new Client3(host, port);
        	 data1 = ran.nextInt(40000);
        	 data2 = ran.nextInt(40000);
        	 client.sendMessage(data1,data2);
         }
         
      } else {
         System.out.println("Usage: java TCPSocketClient <host> <port>");
      }

   }
} // end class

