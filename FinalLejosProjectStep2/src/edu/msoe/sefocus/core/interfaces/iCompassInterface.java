package edu.msoe.sefocus.core.interfaces;

/**
 * This interface will define how one interacts with a compass data provider. A
 * compass data provider provides information about the direction a unit is
 * heading.
 */
public interface iCompassInterface {

	/**
	 * This method will add a new observer to the system.
	 * 
	 * @param cp
	 *            This is the compass panel that is to act as an observer.
	 */
	void addObserver(iCompassObserverInterface cp);

	/**
	 * This method will shutdown the given data provider, causing it to stop
	 * operating.
	 */
	void shutdown();

	/**
	 * This method will return a string representing the current heading.
	 * @return The current heading text will be returned.
	 */
	String getCompassHeadingText();

}