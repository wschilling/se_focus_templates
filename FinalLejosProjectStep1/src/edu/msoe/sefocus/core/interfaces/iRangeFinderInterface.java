package edu.msoe.sefocus.core.interfaces;

/**
 * This interface will define how one interacts with a compass data provider. A
 * compass data provider provides information about the direction a unit is
 * heading.
 */
public interface iRangeFinderInterface {

	/**
	 * This method will add a new observer to the system.
	 * 
	 * @param cp
	 *            This is the class that is to act as an observer.
	 */
	void addObserver(iRangefinderObserverInterface cp);

	/**
	 * This method will shutdown the given data provider, causing it to stop
	 * operating.
	 */
	void shutdown();

}