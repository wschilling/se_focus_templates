import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;

/**
 * This program will demonstrate the robot dancing.
 * 
 * @author se2890
 * 
 */
public class Program5  {
	/**
	 * This is the main method. It will instantiate a basic motor controller and
	 * assign it to listen for button presses. It then will drive the robot.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		// Instantiate a DJ to provide music and start it playing.
		// TODO 
		// TODO 
		
		// Instantiate a motor controler and make the robot dance.
		BasicMotorCtrl mc = new BasicMotorCtrl(new EV3LargeRegulatedMotor(MotorPort.B), new EV3LargeRegulatedMotor(MotorPort.C));
		
		// Make the motor controller listen for the enter button to be pressed.
		Thread MotorThread = new Thread(mc);
		MotorThread.start();
		
		// TODO 
	}

}
