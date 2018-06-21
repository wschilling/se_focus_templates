package edu.msoe.sefocus.core.interfaces;
/**
 * This interface is provided to provide a common mechanism for implementing
 * observers of compass data.
 * 
 * @author W Schilling
 *
 */
public interface iCompassObserverInterface {

	/**
	 * This method will be invoked when there is a change in the compass reading
	 * and the value read in.
	 * 
	 * @param value
	 *            This is the new value.
	 */
	void updateCompassHeading(float value);

}