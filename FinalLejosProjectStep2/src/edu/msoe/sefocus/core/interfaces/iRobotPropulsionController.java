package edu.msoe.sefocus.core.interfaces;

public interface iRobotPropulsionController {

	/**
	 * This method will cause the motors to start running so as to propel the
	 * vehicle in a forward direction. The speed will be determined by the motor
	 * velocity.
	 * @throws Exception 
	 */
	public abstract void driveRobotForward() throws Exception;

	/**
	 * This method will cause the motors to start running so as to propel the
	 * vehicle in a backward direction. The speed will be determined by the
	 * motor velocity.
	 * @throws Exception 
	 */
	public abstract void driveRobotBackward() throws Exception;

	/**
	 * This method will cause the robot to start turning to the right.
	 * @throws Exception 
	 */
	public abstract void turnRobotRight() throws Exception;

	/**
	 * This method will cause the robot to start turning to the left.
	 * @throws Exception 
	 */
	public abstract void turnRobotLeft() throws Exception;
	
	/**
	 * This method will stop the motors from running, resulting in them halting
	 * in their current position.
	 * @throws Exception 
	 */
	public abstract void stopRobotMotion() throws Exception;

	/**
	 * This method will set the velocity for the vehicle.
	 * 
	 * @param motorSpeed
	 *            This is the speed of the vehicle given in inches per minute.
	 * @throws Exception 
	 */
	public abstract void setVelocity(int velocity) throws Exception;

	/**
	 * This method will return the set motor velocity from the robot.
	 * 
	 * @return the velocity
	 */
	public abstract int getVelocity();

}