import java.io.FileReader;
import java.util.Properties;
import java.util.Scanner;

import edu.msoe.sefocus.core.realizations.Robot;
import edu.msoe.sefocus.pccmdui.BatteryDisplay;
import edu.msoe.sefocus.pccmdui.OdometerDisplay;
/**
 * This main class demonstrates a pc command based approach to controlling the robot.
 * @author schilling
 *
 */
public class CmdBasedMainProgram {

	public static void main(String args[])  throws Exception {
			/**
			 * Obtain a properties file. The properties file determines what the IP
			 * address is for the given robot.
			 */
			Properties p = new Properties();
			p.load(new FileReader("project.properties"));

		
		Robot myRobot = new Robot(p.getProperty("host"));
		int robotSpeed = 500;

		// Set up the various observers, which simply print out to the console.

		OdometerDisplay od = new OdometerDisplay(myRobot.getOdometer());
		myRobot.addOdometerObserver(od);

		BatteryDisplay bd = new BatteryDisplay(myRobot.getBattery());
		myRobot.addBatteryObserver(bd);

		// Setup a scanner which will read values form the keyboard.  When these values come in the appropriate actions will be taken.
		String inputText = "";
		Scanner kbd = new Scanner(System.in);

		// Start the robot.
		// TODO 
		
		do {
			inputText = kbd.nextLine();

			// 8 is an up arrow.  (Hint: Look at the numeric keypad.)
			if (inputText.equalsIgnoreCase("8")) {
				// TODO 
			} else if (inputText.equalsIgnoreCase("2")) {
				// TODO 
			} else if (inputText.equalsIgnoreCase("4")) {
				// TODO 
			} else if (inputText.equalsIgnoreCase("6")) {
				// TODO 
			} else if (inputText.equalsIgnoreCase("+")) {
				robotSpeed+=10;
				// TODO 
			} else if (inputText.equalsIgnoreCase("-")) {
				robotSpeed-=10;
				// TODO 
			} else if (inputText.equalsIgnoreCase("U")) {
				myRobot.getHoistController().raiseHoist();
			} else if (inputText.equalsIgnoreCase("D")) {
				myRobot.getHoistController().lowerHoist();
			} else {
				// If no valid button is pressed, simply stop the robot.
				myRobot.getPropulsionController().stopRobotMotion();
				myRobot.getHoistController().stopHoist();
			}
			// Entering the text quit will cause the program to exit.
		} while (inputText.equalsIgnoreCase("Quit") == false);
		
		// SHutdown anything that is going on on the robot.
		myRobot.shutdownRobot();
		
		kbd.close();
	}
}
