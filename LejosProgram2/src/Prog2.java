
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;

/**
 * This program will read the distance sensor and print out the distance in
 * metres and feet.
 * 
 * @author W Schilling
 * 
 */
public class Prog2 {
	// The following declares constants that will be used to convert sensor
	// readings from one unit to another.
	public static final float INCHES_PER_METER = 39.3701f;
	// TODO 

	public static void main(String[] args) {
		// Instantiate a new instance of a Ultrasonic sensor device
		// TODO 

		// Declare the variables that will be used in the code.
		float distanceMetres = 0;
		float distanceFeet = 0;
		float sumDistanceMetres = 0;
		int numberOfReads = 0;
		float averageDistanceMetres = 0;
		float averageDistanceFeet = 0;

		// Get the sample provider, which is the source, for distance.
		// TODO 

		// Loop until the user presses a button.
		while (Button.readButtons() == 0) {
			float[] sample = new float[10];

			// Read the current distance measurement in.
			// TODO 

			// Get the current distance in metres.
			// TODO 

			// Add it to the sum.
			// TODO 

			// Increment the count.
			// TODO 

			// Calculate the average
			// TODO 

			// Calculate the English unit equivalents.
			// TODO 
			// TODO 

			// Now print everything out to the display
			LCD.clearDisplay();
			// TODO 
			// TODO 
			// TODO 
			// TODO 

			// Wait 250ms and repeat it again.
			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
			}
		}
		// Always close up the sensor when finished.
		// TODO 
	}
}
