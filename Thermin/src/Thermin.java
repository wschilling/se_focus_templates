import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;

public class Thermin {
	public static void main(String[] args) {
		EV3GyroSensor volumeSensor = new EV3GyroSensor(SensorPort.S1);
		EV3UltrasonicSensor toneSensor = new EV3UltrasonicSensor(SensorPort.S2);
		toneSensor.enable();

		SampleProvider volumeProvider = volumeSensor.getAngleMode();
		SampleProvider toneProvider = toneSensor.getDistanceMode();

		while (Button.getButtons() == 0) {
			float volumeSamples[] = new float[2];
			float toneSamples[] = new float[2];

			volumeProvider.fetchSample(volumeSamples, 0);
			toneProvider.fetchSample(toneSamples, 0);

			playTone(toneSamples[0], volumeSamples[0]);

		}

		volumeSensor.close();
		toneSensor.close();
	}

	public static void playTone(float frequency, float volume) {
		System.out.println("Freq A: " + frequency);
		System.out.println("Vol A: " + volume);

		// STart by converting the volume into something meaningful.
		// The angle is between -50 and +50, so we will add 50 to this and set
		// the volume.
		int aVolume = (int) (volume + 50);

		// Now do some bounds checking on the volume.
		if (aVolume < 0) {
			aVolume = 0;
		}
		if (aVolume > 100) {
			aVolume = 100;
		}

		// Now do some things with the frequency.
		// The value from the ultrasonic sensor ranges from 0 to 1.
		// We need to scale this based on tones to cover the octave ranges we
		// want to have.
		// A value of .5 will give me a frequency of 440, 
		// A value of 0 will give me a frequency of 220Hz.
		// A value of 1 will give me a frequency of 880.
		double pwr = Math.pow(2, ((frequency - .5) * 2));
		int aFreq = (int) (pwr * 440);
		
		System.out.println("Freq: " + aFreq);
		System.out.println("Vol: " + aVolume);

		Sound.playTone(aFreq, 50, aVolume);
	}
}
