package edu.msoe.sefocus.core.interfaces;

/**
 * This interface defines the interface for accessing a battery monitor object.
 * The battery monitor tells us how the battery is doing on the robot.
 * 
 * @author schilling
 *
 */
public interface iBatteryMonitor {

	/**
	 * This method will obtain the battery life remaining in the Mindtsorm robot
	 * battery. The value will be returned as a value between 0 and 1.00, with
	 * 1.00 representing 100% battery life remaining.
	 * 
	 * @return The return value will be the life remaining in the battery.
	 */
	public abstract double getBatteryLifeRemaining();

}