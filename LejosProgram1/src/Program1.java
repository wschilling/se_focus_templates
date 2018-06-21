import java.net.NetworkInterface;
import java.util.Enumeration;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

/**
 * This program will allow the user to print messages on the Lego Mindstorm
 * controller based upon various buttons being pressed.
 * 
 * @author W. Schilling
 *
 */
public class Program1 {

	public static void main(String[] args) throws Exception {
		// While the user does not press one of the buttons that will cause the
		// program to exit, keep going.
		boolean keepGoing = true;

		// Print a message out on the console.
		// TODO 

		// Get the IP address of the machine.
		Enumeration<NetworkInterface> iterAddress = NetworkInterface.getNetworkInterfaces();

		// Print the IP addresses to the screen.
		int count = 1;

		while (iterAddress.hasMoreElements()) {
			// TODO 
			// TODO 
			// Increment the count.
			// TODO 
		}

		while (keepGoing == true) {
			// Wait 10000ms for a button press and read the button id in as an
			// integer.
			int buttonID = Button.waitForAnyPress(10000);

			// If the button pressed is the left button, handle it
			// appropriately.
			// TODO 
			{
				LCD.drawString("Left Button Pressed", 0, count);
				// If the button pressed is the right button, handle it
				// appropriately.
			} 
			// TODO 
			{
				LCD.drawString("Right Button Pressed", 0, count);
			} 
			// TODO 
			{
				LCD.drawString("Down Button Pressed", 0, count);
			} 
			// TODO 
			{
				LCD.drawString("Up Button Pressed", 0, count);
			} else {
				// If another button is pressed, exit the program.
				keepGoing = false;
			}
		}
	}
}
