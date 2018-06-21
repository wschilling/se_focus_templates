import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.Button;

/**
 * This program will demonstrate making the robot go forward and backward in an
 * automated fashion. It will also listen for a button press and will exit then
 * that occurs.
 * 
 * @author se2890
 * 
 */
public class Program4  implements Runnable {
	// These variables define the left and right motor instances.
	// TODO 
	// TODO 
	
	/**
	 * This is a constructor which will create an instance of the motor
	 * controller.
	 * 
	 * @param leftMotor
	 *            This is the motor that is to be used as the left motor.
	 * @param rightMotor
	 *            This is the motor that is to be used as the right motor.
	 */
	public Program4(BaseRegulatedMotor leftMotor,
			BaseRegulatedMotor rightMotor) {
		super();
		this.leftMotor = leftMotor;
		this.rightMotor = rightMotor;
	}

	/**
	 * This method will drive the robot. It will go forward for a period of time
	 * and then backward for a period of time, repeating ad nausium.
	 */
	public void driveRobot() {
		while (true) {

			for (int motorSpeed = 0; motorSpeed < 1000; motorSpeed++) {
				// TODO 
				// TODO 
				// TODO 
				// TODO 
				try {
					Thread.sleep(5);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}

			// Now decelerate the motor from 1000 to 0 in the same fashion.
			for (int motorSpeed = 1000; motorSpeed > 0; motorSpeed--) {
				// TODO 
				// TODO 
				leftMotor.setSpeed(motorSpeed);
				rightMotor.setSpeed(motorSpeed);
				try {
					Thread.sleep(5);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}

			// Now that the motor is stopped, change the direction to backward
			// and accelerating the motor
			// from stop to 1000 units, sleeping for 5 ms as each value is
			// reached.
			for (int motorSpeed = 0; motorSpeed < 1000; motorSpeed++) {
				// TODO 
				// TODO 
				leftMotor.setSpeed(motorSpeed);
				rightMotor.setSpeed(motorSpeed);
				try {
					Thread.sleep(5);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}

			// Now decelerate the motor from 1000 to 0 in the same fashion.
			for (int motorSpeed = 1000; motorSpeed > 0; motorSpeed--) {
				leftMotor.backward();
				rightMotor.backward();
				// TODO 
				// TODO 

				try {
					Thread.sleep(5);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
			}

		}

	}

	/**
	 * This is the main method. It will instantiate a basic motor controller and
	 * assign it to listen for button presses. It then will drive the robot.
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		Program4 mc = new Program4(new EV3LargeRegulatedMotor(MotorPort.B), new EV3LargeRegulatedMotor(MotorPort.C));
		new Thread(mc).start();
		// TODO 
	}

	@Override
	public void run() {
		Button.waitForAnyPress();
		System.exit(0);;
	}
}
