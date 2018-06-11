//Java Ideas app @BitcoinJake09 6/10/2018
import java.io.*;
import java.util.*;

class IdeasApp {

    public static void main(String[] args) {

  try {	//using try will catch errors to deal with easier
	File yourFile = new File("Ideas.txt");
	yourFile.createNewFile(); // creates file if not there
        FileWriter fw = new FileWriter("Ideas.txt", true); //make a FileWritter
        BufferedWriter bw = new BufferedWriter(fw); //Buffer the FilerWriter
	PrintWriter writer = new PrintWriter(bw); //use PrintWriter to write to file
        Scanner sc = new Scanner(System.in); //Scanning in System.in
        System.out.print("Ideas?:"); //display for user
        	while(sc.hasNextLine()) { //while the scanner has inputs
		 String textLine = sc.nextLine(); //use nextLine ONCE & use variable
		 if (textLine.equalsIgnoreCase("exit")) { //if idea/textLine is exit
		  writer.close(); //close writer
		  return; //close program
		 } //if idea isnt "exit" print to file
		 writer.println(textLine);
                 System.out.print("Ideas?:"); //ask for next idea and loop back
		}
  } catch (Exception e) { //catch and print errors
        System.out.println(e.toString());
  } // ends try&catch
 } //ends vaid main
} //end app

