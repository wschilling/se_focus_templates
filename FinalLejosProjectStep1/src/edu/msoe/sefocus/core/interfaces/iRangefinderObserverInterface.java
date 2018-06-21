package edu.msoe.sefocus.core.interfaces;
/**
 * This interface is provided to provide a common mechanism for implementing
 * observers of compass data.
 * 
 * @author W Schilling
 *
 */
public interface iRangefinderObserverInterface {
	public static final float INCHES_PER_METER = 39.3701f;
	public static final float FEET_PER_METER = 3.28084f;
	
	/**
	 * This method will be invoked when there is a change in the compass reading
	 * and the value read in.
	 * 
	 * @param value
	 *            This is the new value expressed in meters to the reflecting item.
	 */
	void updateRangefinderData(float value);

}