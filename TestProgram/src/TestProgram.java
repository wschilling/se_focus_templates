import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;

/**
 * This is a demonstration program. It will verify that your development
 * environment is working properly.
 * 
 * @author W. Schilling
 * 
 */
public class TestProgram {
	public static void main(String args[]) {
		LCD.clearDisplay();
		LCD.drawString("Hello Focus Students", 0, 0);
		Sound.beepSequenceUp();
		Sound.beepSequence();
		Button.waitForAnyPress();
	}
}