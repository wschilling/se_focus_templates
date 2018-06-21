import java.util.Scanner;

/**
 * This program will help to navigate a robot from a location to Chipotle.  The user will enter the path and it will process those commands one by one, printing them out to the console.
 * 
 * @author se2890
 *
 */
public class GetToChipotle {
	public static void main(String args[])
	{
		// Declare a scanner to read from the keyboard.
		Scanner kbd = new Scanner(System.in);
		
		// Prompt the user to enter their path.
		// TODO 
		
		// Read in the path the robot is to follow.
		// TODO 
		
		// Loop over each entry, printing it out to the console.
		// TODO 
		{
			// Get the current action by looking at the current character in the string.
			// TODO 
			
			// Print the action out to the console.
			// TODO 
		}

		// Close the open file.
		kbd.close();
	}
	
	
	/**
	 * This method will print the actions out to the console that should occur on the basic of the directions given.
	 * @param action The action is the direction that the robot is to turn as it is following along.
	 */
	public static void printProperAction(char action)
	{
		// Take the appropriate action if the command is an L.
		// TODO 
		{
			System.out.println("Turning left!");
		}
		// Take the appropriate action if the command is an R.
		// TODO 
		{
			System.out.println("Turning right!");
		}
		// Take the appropriate action if the command is an S.
		// TODO 
		{
			System.out.println("Go straight one block.");
		}
	}
}
