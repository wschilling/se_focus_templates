import java.io.FileReader;
import java.util.Properties;
import java.util.Scanner;

import edu.msoe.sefocus.core.realizations.Robot;
import edu.msoe.sefocus.pccmdui.BatteryDisplay;
import edu.msoe.sefocus.pccmdui.OdometerDisplay;

/**
 * This main class demonstrates a pc command based approach to controlling the
 * robot.
 * 
 * @author schilling
 *
 */
public class CmdBasedMainProgram implements Runnable {
	private static Robot myRobot;

	public static void main(String args[]) throws Exception {
		/**
		 * Obtain a properties file. The properties file determines what the IP
		 * address is for the given robot.
		 */
		Properties p = new Properties();
		p.load(new FileReader("project.properties"));

		myRobot = new Robot(p.getProperty("host"));

		// Set up the various observers, which simply print out to the console.
		OdometerDisplay od = new OdometerDisplay(myRobot.getOdometer());
		myRobot.addOdometerObserver(od);

		BatteryDisplay bd = new BatteryDisplay(myRobot.getBattery());
		myRobot.addBatteryObserver(bd);
		
		new Thread(new CmdBasedMainProgram()).start();

		// Start the robot.
		myRobot.initiateRobotOperation();

	}

	@Override
	public void run() {
		// Setup a scanner which will read values form the keyboard. When these
		// values come in the appropriate actions will be taken.
		String inputText = "";
		Scanner kbd = new Scanner(System.in);
		int robotSpeed = 500;

		do {
			inputText = kbd.nextLine();

			try {
				// 8 is an up arrow. (Hint: Look at the numeric keypad.)
				if (inputText.equalsIgnoreCase("8")) {
					myRobot.getPropulsionController().driveRobotForward();
				} else if (inputText.equalsIgnoreCase("2")) {
					myRobot.getPropulsionController().driveRobotBackward();
				} else if (inputText.equalsIgnoreCase("4")) {
					myRobot.getPropulsionController().turnRobotLeft();
				} else if (inputText.equalsIgnoreCase("6")) {
					myRobot.getPropulsionController().turnRobotRight();
				} else if (inputText.equalsIgnoreCase("+")) {
					robotSpeed += 10;
					myRobot.getPropulsionController().setVelocity(robotSpeed);
				} else if (inputText.equalsIgnoreCase("-")) {
					robotSpeed -= 10;
					myRobot.getPropulsionController().setVelocity(robotSpeed);
				} else if (inputText.equalsIgnoreCase("U")) {
					myRobot.getHoistController().raiseHoist();
				} else if (inputText.equalsIgnoreCase("D")) {
					myRobot.getHoistController().lowerHoist();
				} else {
					// If no valid button is pressed, simply stop the robot.
					myRobot.getPropulsionController().stopRobotMotion();
					myRobot.getHoistController().stopHoist();
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			// Entering the text quit will cause the program to exit.
		} while (inputText.equalsIgnoreCase("Quit") == false);

		// SHutdown anything that is going on on the robot.
		try {
			myRobot.shutdownRobot();
		} catch (Exception e) {
		}

		kbd.close();

	}
}
