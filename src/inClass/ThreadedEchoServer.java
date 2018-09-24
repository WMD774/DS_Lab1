package inClass;

import java.io.IOException;
import java.net.ServerSocket;

public class ThreadedEchoServer {
	
    public static final int BACKLOG = 5;
    private ServerSocket serverSocket;
    
    public static void main(String[] args) { 
        new ThreadedEchoServer(7788).listen();
    }
        
    public ThreadedEchoServer(int port) {
        try {
            serverSocket = new ServerSocket(port, BACKLOG);
        } catch (IOException e) { 
        	e.printStackTrace(); 
        }
    }
    
    public void listen() {
    	
        while (true) {
            try {
                new Thread(new Connection(serverSocket.accept())).start();
            } catch (IOException e) { 
            	e.printStackTrace(); 
            } 
        }
        
    }
}

