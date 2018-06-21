package edu.msoe.sefocus.core.realizations;

import java.util.Observable;

import edu.msoe.sefocus.core.interfaces.iBatteryMonitor;
import lejos.hardware.Power;
import lejos.remote.ev3.RemoteEV3;

/**
 * This class monitors the status of the battery on the lego mindstorm robot.
 * 
 * @author schilling
 * 
 */
public class BatterySource extends Observable implements Runnable, iBatteryMonitor {
	private static final int MAX_BATTERY_VOLTAGE = 9000;
	private static final int MIN_BATTERY_VOLTAGE = 7000;

	/**
	 * This is the thread that is running this instance of the Battery monitor.
	 */
	private Thread myThread;

	/**
	 * This is the EV3 mindstorm which is the source for the data.
	 */
	private RemoteEV3 ev3;

	/**
	 * This is the battery which is the source for this class.
	 */
	private Power theBattery;

	/**
	 * This is the life remaining in the battery, expressed as a value between 0
	 * and 1.
	 */
	private double batteryLifeRemaining;

	/**
	 * This variable is used by the thread to allow for an appropriate shutdown
	 * when there no longer is a need for the thread to continue running.
	 */
	private boolean keepGoing = true;

	/**
	 * This will construct a new battery source which will monitor the battery
	 * on a remote device.
	 * 
	 * @param host
	 *            This is the ip address of the robot that is to be controlled.
	 * @throws Exception
	 */
	public BatterySource(String host) throws Exception {
		ev3 = new RemoteEV3(host);
		theBattery = ev3.getPower();
		System.out.println("Creating remote sensor class for battery: " + host);
	}

	/**
	 * This method will shutdown the battery monitor, killing the executing
	 * thread.
	 */
	public void shutdownBatteryMonitor() {
		keepGoing = false;
		myThread.interrupt();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.msoe.sefocus.core.iBatteryMonitor#getBatteryLifeRemaining()
	 */
	@Override
	public double getBatteryLifeRemaining() {
		return batteryLifeRemaining;
	}

	@Override
	public void run() {
		myThread = Thread.currentThread();
		while (keepGoing == true) {
			int batteryVoltage = theBattery.getVoltageMilliVolt();
			batteryLifeRemaining = (batteryVoltage - MIN_BATTERY_VOLTAGE)
					/ (double) (MAX_BATTERY_VOLTAGE - MIN_BATTERY_VOLTAGE);

			// Update the observers of the new state.
			setChanged();
			notifyObservers();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
			}
		}
	}
}
