
import java.io.*;
import java.util.*;

public class runMinerThread extends Thread {
    private volatile boolean isMining = true;
    public String fileName="";
    	
	//@Override
	public void run () {
		//startMiner(shFileName);
            while (!Thread.currentThread().isInterrupted()) {
		main();
	    }
	}
	public void main() {
	   try {
		System.out.println("attempting to run...");
		ProcessBuilder pb = new ProcessBuilder("./MinerScripts/"+fileName);
 		Process p = pb.start();
 		BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
 		String line = null;			
		while ((isMining)&&(line = reader.readLine()) != null) {
			//EasyMinerGUI.printToConsole(line);
    			System.out.println(line);
 		}
			


    	   } catch (IOException ex) {
		System.out.println("error running: "+ex);
    	   }
        
         
	System.out.println("Stopped Running....");
	}

	public void stopMining() {
        isMining = false;
	//thread.stopRunning();
    	}

	public boolean setItemToRun(String FTR) {
		fileName=FTR;
		return true;	
	}
}

