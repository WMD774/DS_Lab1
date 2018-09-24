package Task4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * A class that responses to use threat to due with the received data,
 * then send back to the console.
 * @author Minda Wen 14207118 (minda.wen@ucdconnect.ie)
 */

public class Service implements Runnable {
	
	// count the processing time.
	private int count = 0;	
	// create a socket.
	private Socket socket;
	
	public Service(Socket socket) { 
    	this.socket = socket; 
    }

	public void run() {
		
        try {
        	int data1,data2;
        	// use binary stream to transport data.
        	DataInputStream in	= new DataInputStream(this.socket.getInputStream());
        	DataOutputStream out = new DataOutputStream(this.socket.getOutputStream());
	         // Read in the integer respectively.
//	        data1 = in.readInt();
//	        data2 = in.readInt();
	        
	        // wait the string untill services died out
	        while(Integer.toString(data1 = in.readInt())!=null&& Integer.toString(data2 = in.readInt())!= null) {
		        // Print the message to the console
		        System.out.println("Recceived number: " + data1 + " & " + data2 + " = " + data1*data2);
		        
		        //send back the response to the client
		        out.writeBytes("The result of " + data1 +"*"+ data2 + " is " + data1*data2 + '\n');
		        System.out.println("Feedback has sent.");
		        System.out.println("count: " + this.count++ + "\n");
//		        data1 = in.readInt();
//		        data2 = in.readInt();
	        }
        } catch (IOException ioe) {
        	ioe.printStackTrace();
        } catch (SecurityException se) {
        	se.printStackTrace();
        }
        
	}
}
