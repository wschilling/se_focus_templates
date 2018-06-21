
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.robotics.SampleProvider;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;

/**
 * This program will read the gyro sensor and it's current angle and acceleration..
 * 
 * @author se2890
 * 
 */
public class Program3 {
	public static void main(String[] args) {
		// Instantiate a new instance of a Compass device
		// TODO 
		
		// Declare the variables that will be used in the code.
		float distAngle = 0;
		float angleVelocity = 0;

		// Get the sample provider for distance.
		// TODO 
		
		// Loop until the user presses a button.
		while (Button.readButtons() == 0) {
			float[] sample = new float[2];

			// Read the current distance measurement in.
			// TODO 
			distAngle = sample[0];
			angleVelocity = sample[1];

			// Now print everything out to the display
			LCD.clearDisplay();
			// TODO 
			// TODO 
			
			// Wait 250 ms and repeat it again.
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
			}
		}
		gyroSensor.close();
	}
}
