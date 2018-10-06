
import java.io.*;
import java.util.*;

public class runMinerThread implements Runnable {
    private volatile boolean isMining = true;
    	public void stopMining() {
        isMining = false;
	//thread.stopRunning();
    	}
	
	//@Override
	public void run () {
		//startMiner(shFileName);
	}
    	public void startMiner(String fileName) {
		try {
			System.out.println("attempting to run...");
			ProcessBuilder pb = new ProcessBuilder("./"+fileName);
 			Process p = pb.start();
 			BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
 			String line = null;			
				while ((isMining)&&(line = reader.readLine()) != null) {
    					System.out.println(line);
 				}
			


    		} catch (IOException ex) {
			System.out.println("error running: "+ex);
    		}
        
         
	System.out.println("Stopped Running....");
	}
}

