import lejos.hardware.Button;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.robotics.RegulatedMotor;

/**
 * This class will control the oepration of the motors, randomly making them "dance" as the program runs.
 * @author se2890
 *
 */
public class BasicMotorCtrl implements Runnable {
	// These variables define the left and right motor instances.
	private BaseRegulatedMotor leftMotor;
	private BaseRegulatedMotor rightMotor;

	//RegulatedMotor m = new EV3LargeRegulatedMotor(MotorPort.A);
	
	/**
	 * This is a constructor which will create an instance of the motor
	 * controller.
	 * 
	 * @param leftMotor
	 *            This is the motor that is to be used as the left motor.
	 * @param rightMotor
	 *            This is the motor that is to be used as the right motor.
	 */
	public BasicMotorCtrl(BaseRegulatedMotor leftMotor,
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
			float speed1 = (float) (Math.random() * 1600);
			float speed2 = (float) (Math.random() * 1600);
			// TODO 
			// TODO 
			
			double rnum = Math.random();

			if (rnum < 0.25) {
				// TODO 
				// TODO 
			} else if (rnum < 0.5) {
				leftMotor.backward();
				rightMotor.forward();
			} else if (rnum < 0.75) {
				leftMotor.forward();
				rightMotor.backward();
			} else {
				leftMotor.backward();
				rightMotor.backward();
			}

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}



	@Override
	public void run() {
		Button.waitForAnyPress();
		Button.waitForAnyEvent();
		System.exit(0);
	}



}
