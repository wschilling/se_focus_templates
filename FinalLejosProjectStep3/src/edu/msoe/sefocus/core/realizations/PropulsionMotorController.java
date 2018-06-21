package edu.msoe.sefocus.core.realizations;

import java.rmi.RemoteException;
import java.util.Observable;

import edu.msoe.sefocus.core.interfaces.iOdometer;
import edu.msoe.sefocus.core.interfaces.iRangefinderObserverInterface;
import edu.msoe.sefocus.core.interfaces.iRobotPropulsionController;
import lejos.remote.ev3.RMIRegulatedMotor;

/**
 * This class controls the operation of the Lego Mindstorm motors. It supports
 * the ability to run the motors forward, backward, turn left, turn right, stop,
 * and set the speed for the motors.
 * 
 * @author schilling
 * 
 */
public class PropulsionMotorController extends Observable implements Runnable, iOdometer, iRobotPropulsionController, iRangefinderObserverInterface {

	private static final double WHEEL_DIAMETER = 2.125; // The diameter of the
														// tires is 2.125
														// inches.
	private static final double WHEEL_CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER; // The
																				// wheel
																				// circumference
																				// for
																				// the
																				// vehicle.
	private static final double INCHES_PER_DEGREE = WHEEL_CIRCUMFERENCE / 360; // This
																				// constant
																				// represents
																				// the
																				// distance
																				// traveled
																				// in
																				// inches
																				// for
																				// each
																				// degree
																				// of
																				// rotation
																				// on
																				// the
																				// wheels.

	private RMIRegulatedMotor leftMotor;
	private int lastLeftMotorAngle = 0;
	private int lastRightMotorAngle = 0;
	private RMIRegulatedMotor rightMotor;
	private int velocity;
	private double robotOdometer;
	private boolean keepGoing = true;
	private Thread myThread;
	private boolean reverseDirection = false;

	/**
	 * This method will instantiate a new instance of the motor controller
	 * class. This class will control the operation of the motors.
	 * 
	 * @param lm
	 *            This is the motor that is on the left side of the vehicle.
	 * @param rm
	 *            This is the motor that is on the right side of the vehicle.
	 * @throws RemoteException
	 */
	public PropulsionMotorController(RMIRegulatedMotor lm, RMIRegulatedMotor rm) throws RemoteException {
		this.leftMotor = lm;
		this.rightMotor = rm;
		velocity = 500;

		lm.resetTachoCount();
		rm.resetTachoCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.msoe.sefocus.core.iRobotPropulsionController#driveRobotForward()
	 */
	@Override
	public void driveRobotForward() throws Exception {
		int currentSpeed = (int) ((velocity / 60) * (360 / WHEEL_CIRCUMFERENCE));

		leftMotor.setSpeed(currentSpeed);
		rightMotor.setSpeed(currentSpeed);

		leftMotor.forward();
		rightMotor.forward();
		reverseDirection = false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.msoe.sefocus.core.iRobotPropulsionController#driveRobotBackward()
	 */
	@Override
	public void driveRobotBackward() throws Exception {
		int currentSpeed = (int) ((velocity / 60) * (360 / WHEEL_CIRCUMFERENCE));

		leftMotor.setSpeed(currentSpeed);
		rightMotor.setSpeed(currentSpeed);

		leftMotor.backward();
		rightMotor.backward();
		reverseDirection = true;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.msoe.sefocus.core.iRobotPropulsionController#turnRobotRight()
	 */
	@Override
	public void turnRobotRight() throws Exception {
		int currentSpeed = (int) ((velocity / 60) * (360 / WHEEL_CIRCUMFERENCE));

		leftMotor.setSpeed(currentSpeed / 2);
		rightMotor.setSpeed(currentSpeed / 2);

		leftMotor.forward();
		rightMotor.backward();
		reverseDirection = false;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.msoe.sefocus.core.iRobotPropulsionController#turnRobotLeft()
	 */
	@Override
	public void turnRobotLeft() throws Exception {
		int currentSpeed = (int) ((velocity / 60) * (360 / WHEEL_CIRCUMFERENCE));

		leftMotor.setSpeed(currentSpeed / 2);
		rightMotor.setSpeed(currentSpeed / 2);

		leftMotor.backward();
		rightMotor.forward();
		reverseDirection = false;

	}

	/**
	 * This method will stop the motors from running, resulting in them halting
	 * in their current position.
	 */
	public void stopRobotMotion() throws Exception {
		leftMotor.stop(true);
		rightMotor.stop(true);
		reverseDirection = false;
	}

	/**
	 * This method will obtain the desired velocity for the robot.
	 * 
	 * @return The velocity for the robot if it is moving will be returned. The
	 *         value will be in inches per minute.
	 */
	public int getMotorSpeed() {
		return velocity;
	}

	/**
	 * This method will shutodnw the motor controller, causing it to exit the
	 * running thread.
	 */
	public void shutdownMotorController() {
		this.keepGoing = false;
		myThread.interrupt();
	}

	@Override
	public void run() {
		this.myThread = Thread.currentThread();
		while (keepGoing == true) {
			int leftAngle;
			try {
				leftAngle = leftMotor.getTachoCount();
				int rightAngle = rightMotor.getTachoCount();

				// Figure out the delta of the two motors in terms of angles.
				int leftDelta = leftAngle - lastLeftMotorAngle;
				int rightDelta = rightAngle - lastRightMotorAngle;

				// Now that all of that has been done, figure out the forward
				// distance traveled by averaging the two angles and seeing if
				// it is greater than 0.
				if (((leftDelta + rightDelta) / 2) > 0) {
					// We've moved forward, so update the distance.
					int forwardAngle = (leftDelta + rightDelta) / 2;
					robotOdometer += forwardAngle * INCHES_PER_DEGREE;

					// Update the observers with the new information.
					setChanged();
					notifyObservers();
				}

				// Update the last positions.
				lastLeftMotorAngle = leftAngle;
				lastRightMotorAngle = rightAngle;

			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			try {
				Thread.sleep(250);
			} catch (InterruptedException e) {

			}

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.msoe.sefocus.core.test1#getVelocity()
	 */
	@Override
	public int getVelocity() {
		return velocity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.msoe.sefocus.core.iRobotPropulsionController#setVelocity(int)
	 */
	@Override
	public void setVelocity(int velocity) throws Exception {
		// Update the motor values and then store.

		// Determine the ratio between the new and old velocity.
		double change = (velocity) / (double) this.velocity;
		leftMotor.setSpeed((int) (leftMotor.getSpeed() * change));
		rightMotor.setSpeed((int) (rightMotor.getSpeed() * change));

		this.velocity = velocity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.msoe.sefocus.core.iOdometer#getRobotOdometer()
	 */
	@Override
	public double getOdometerReading() {
		return robotOdometer;
	}

	@Override
	public void updateRangefinderData(float value) {
		// If there is something less than 6 inches in front of the sensor, stop the robot.
		// TODO
		{
			try {
				// TODO
			} catch (Exception e) {
			}
		}
		
	}

}
