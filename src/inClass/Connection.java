package inClass;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection implements Runnable {
	
    private Socket socket;
    
    public Connection(Socket socket) { 
    	this.socket = socket; 
    }
    
    public void run() {
        try {
        	DataInputStream in = new DataInputStream(socket.getInputStream()); 
        	DataOutputStream out = new DataOutputStream(socket.getOutputStream()); 
            
            int message = in.readInt();
            
            
            while (Integer.toString(message) != null) {
            	//in.readByte();
            	System.out.println(message);
                out.writeInt(message);
                //out.writeByte('\n');
                message = in.readInt();
            }
            
        } catch (IOException e) { 
        	e.printStackTrace(); 
        } 
    }
}