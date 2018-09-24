package inClass;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ReusingClient {
	
    private String host; 
    private int port;
    private Socket mySocket;
    private DataInputStream in;
    private DataOutputStream out;
    
    public ReusingClient(String host, int port) { 
    	this.host = host; 
    	this.port = port; 
    }
    
    public void connect() {
        try {
            mySocket = new Socket(host, port);
            in = new DataInputStream(mySocket.getInputStream());
            out = new DataOutputStream(mySocket.getOutputStream());
        } catch (IOException e) { 
        	e.printStackTrace(); 
        }
    }
    
    public void sendMessage(int message) {
        try {
            out.writeInt(message); 
            //out.writeByte('\n');
            System.out.println("Received: " + in.readInt());
            //in.readByte();
        } catch (IOException e) { 
        	e.printStackTrace(); 
        }
    }
    
    public void close() {
        try {
            in.close(); 
            out.close(); 
            mySocket.close();
        } catch (IOException e) { 
        	e.printStackTrace(); 
        }
    }


	public static void main(String[] args) {
		
		ReusingClient client = new ReusingClient("localhost", 7788);

		client.connect();
		
		for (int i=0; i<10; i++) {
		    client.sendMessage(i);
		    }
		
		client.close();
	}
}

