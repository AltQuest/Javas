import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Java - must OOPs (Object Oriented Programming)
//C++  - is OPPs but built on C which procedural/structured

//the class named the same as the file is public
//only 1 public class per file
public class getAname { //everything we code will be inside a class

	public static void main(String[] args) throws FileNotFoundException 
	{
		//Exercise 1 
		//Ask the user for a name, look for the name in the table, if it is found
		//then output the value associated with the name
		//if it is not found then indicate this by saying "invalid name"
		//Additionally loop back and ask for a new name after searching, the program 
		//does not have to end
		Scanner fileIn = new Scanner(new File("table.txt"));
		Scanner sc = new Scanner(System.in); //Scanning in System.in
		String textLine = sc.nextLine(); //use nextLine ONCE & use variable
		while (fileIn.hasNext())
		{
			String name = fileIn.next();
			String value = fileIn.next();
			if (textLine.equalsIgnoreCase(name)) {
			 System.out.println(name + " " +value);
			}
		}
	}
}

