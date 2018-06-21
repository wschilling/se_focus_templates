package edu.msoe.sefocus.core.realizations;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import edu.msoe.sefocus.core.interfaces.iCompassInterface;
import edu.msoe.sefocus.core.interfaces.iCompassObserverInterface;
import lejos.remote.ev3.RMISampleProvider;
import lejos.remote.ev3.RemoteEV3;
import lejos.utility.Delay;

/**
 * This class acts as a data source for a compass. It will obtain updates from
 * the compass hardware and send them out to the various systems which need to
 * receive them.
 * 
 * @author W Schilling
 *
 */
public class CompassDataSource implements Runnable, iCompassInterface {
	/**
	 * This is the EV3 mindstorm which is the source for the data.
	 */
	private RemoteEV3 ev3;
	/**
	 * This is the instance of the remote sample provider. It is what is used to
	 * access the sample of the Mindtsorm robot.
	 */
	private RMISampleProvider sp;

	/**
	 * This is the frequency of updates, in updates per second.
	 */
	private float frequency;

	/**
	 * This variable determines if the thread is to keep running.
	 */
	private boolean running = true;
	
	private double currentHeading = 0.0;

	/**
	 * This is a list of observers. Observers are classes which are updated when
	 * the data changes.
	 */
	private List<iCompassObserverInterface> observers = new ArrayList<iCompassObserverInterface>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.msoe.sefocus.core.iCompassInterface#addObserver(edu.msoe.sefocus.core
	 * .CompassObserverInterface)
	 */
	@Override
	public void addObserver(iCompassObserverInterface cp) {
		observers.add(cp);
	}

	/**
	 * This constructor will instantiate a new data provider.
	 * 
	 * @param host
	 *            This is the ip address of the host EV3 mindstorm robot.
	 * @param portName
	 *            This is the name of the port that the device is connected to.
	 * @param frequency
	 *            This is the frequency of access.
	 * @throws Exception
	 *             An exception will be thrown if a connection can not be made.
	 */
	public CompassDataSource(String host, String portName, float frequency) throws Exception {
		ev3 = new RemoteEV3(host);

		this.frequency = frequency;
		System.out.println("Creating remote sensor class: " + "lejos.hardware.sensor.EV3GyroSensor" + " on port "
				+ portName + " with mode " + "Angle");
		sp = ev3.createSampleProvider(portName, "lejos.hardware.sensor.EV3GyroSensor", "Angle");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.msoe.sefocus.core.iCompassInterface#shutdown()
	 */
	@Override
	public void shutdown() {
		try {
			sp.close();
		} catch (RemoteException e) {
			e.printStackTrace();
			running = false;
		}
	}

	/**
	 * This is the run method as part of the thread. It will query the remote
	 * device for the data and update it.
	 */
	public void run() {
		while (running) {
			float[] sample;
			try {
				if (running) {
					sample = sp.fetchSample();

					sample[0] = sample[0] * -1.0f;

					while (sample[0] < 0) {
						sample[0] += 360.0f;
					}

					sample[0] = sample[0] % 360.0f;
					
					currentHeading = sample[0];

					for (iCompassObserverInterface cp : observers) {
						cp.updateCompassHeading(sample[0]);
					}
				}
				Delay.msDelay(((int) (1000f / frequency)));

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				running = false;
			}
		}
	}

	@Override
	public String getCompassHeadingText() {
		return ""+this.currentHeading;
	}
}
