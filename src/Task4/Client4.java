package Task4;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

/**
 * A simple class that opens a socket, sends a message to the server, and
 * terminates.
 * @author Graeme Stevenson (graeme.stevenson@ucd.ie)
 */
public class Client4 {

   /**
    * The server host name.
    */
   public String my_serverHost;

   /**
    * The server port.
    */
   public int my_serverPort;
   
   private Socket toServer;
   private DataInputStream in;
   private DataOutputStream out;

   /**
    * Sets the client up with the server details.
    * @param the_serverHost the server host name.
    * @param the_serverPort the server port.
    */
   public Client4(String the_serverHost, int the_serverPort) {
      my_serverHost = the_serverHost;
      my_serverPort = the_serverPort;
   }

   /**
    * Creates a connection to the server and sends a message.
    * @param a_message the message to send to the server.
    */
   public void connect() {
	   try {
		   // Create a connection to the server.
		   toServer = new Socket(my_serverHost, my_serverPort);
		   
		   // Wrap a PrintWriter round the socket output stream.
		   // Read the javadoc to understand (1) the method arguments, and (2) why
		   // we do this rather than writing to raw sockets.
		   in = new DataInputStream(toServer.getInputStream());
		   out = new DataOutputStream(toServer.getOutputStream());
	   } catch (IOException ioe) {
	       ioe.printStackTrace();
	   } catch (SecurityException se) {
	       se.printStackTrace();
	   }
   }
   
public void sendReceive (int data1, int data2) {
      try {
         // Write the message to the socket.
         out.writeInt(data1);
         out.writeInt(data2);
         System.out.println("Massage has sent: "+ data1 + " & " + data2);
         
         //
//        String line = null;
//         while ((line = in.readLine()) != null) {
//      	  	 System.out.println(line);
//      	 }
         String line = in.readLine();
         System.out.println(line);
      	 System.out.println("Feedback has recieved" + '\n');
      	 //System.out.println("The result of " + data1 + "*" + data2 + " is " + in.readInt() + "\n");
         
      } catch (IOException ioe) {
         ioe.printStackTrace();
      } catch (SecurityException se) {
         se.printStackTrace();
      }
   } 
   
   public void close() {
	   try {
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
         Client4 client = new Client4(host, port);
         
         client.connect();
         
         // Send a message using the client
         for ( int k=0 ; k<=99 ; k++) {
        	 data1 = ran.nextInt(40000);
        	 data2 = ran.nextInt(40000);
        	 client.sendReceive(data1, data2);
         }
         
         client.close();
         
      } else {
         System.out.println("Usage: java TCPSocketClient <host> <port>");
      }

   }
} // end class

