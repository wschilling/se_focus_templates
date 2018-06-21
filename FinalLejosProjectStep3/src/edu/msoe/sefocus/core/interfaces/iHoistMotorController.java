package edu.msoe.sefocus.core.interfaces;

/**
 * This interfaces defines how one interacts with the hoist controller.
 * 
 * @author schilling
 *
 */
public interface iHoistMotorController {

	/**
	 * This method will cause the hoist to start raising. The hoist will
	 * continue raising until it is stopped.
	 * 
	 * @throws Exception
	 */
	public abstract void raiseHoist() throws Exception;

	/**
	 * This method will cause the hoist to start lowering. The hoist will
	 * continue lowering until it is stopped.
	 * 
	 * @throws Exception
	 */
	public abstract void lowerHoist() throws Exception;

	/**
	 * This method will stop the hoist from moving.
	 */
	public abstract void stopHoist() throws Exception;

}