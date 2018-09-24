package inClass;

public class SharedMemoryThread implements Runnable {
    
	private static class SharedValue {
        
    	private int value;
        
        public void updateValue(int value) {
            System.out.println("updating: " + value);
            this.value = value;
            System.out.println("value=" + this.value);
            System.out.println("updated: " + value);
        }
        
    }
	
    private SharedValue value;
    
    public static void main(String[] args) {
        SharedValue value = new SharedValue();
        new Thread(new SharedMemoryThread(value)).start();
        new Thread(new SharedMemoryThread(value)).start();
    }
    
    public SharedMemoryThread(SharedValue value) { 
    	this.value = value; 
    }
    
    public void run() {
        for (int i=0; i<10; i++) { 
        	value.updateValue(i); 
        }
    }

}

