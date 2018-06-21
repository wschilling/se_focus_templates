package edu.msoe.sefocus.core.realizations;

import java.util.Observer;

import edu.msoe.sefocus.core.interfaces.iBatteryMonitor;
import edu.msoe.sefocus.core.interfaces.iCompassInterface;
import edu.msoe.sefocus.core.interfaces.iCompassObserverInterface;
import edu.msoe.sefocus.core.interfaces.iHoistMotorController;
import edu.msoe.sefocus.core.interfaces.iOdometer;
import edu.msoe.sefocus.core.interfaces.iRobotPropulsionController;
import lejos.remote.ev3.RMIRegulatedMotor;
import lejos.remote.ev3.RemoteEV3;

/**
 * This class defines the various pieces of a robot. It contains all of the
 * necessary items as well as allows for devices to obtain various pieces of
 * information about the robot.
 * 
 * @author schilling
 * 
 */
public class Robot {
	/**
	 * This is the EV3 mindstorm which is the source for the data.
	 */
	private RemoteEV3 ev3;

	/**
	 * The following is the instance of the class which will control motor
	 * propulsion.
	 */
	private PropulsionMotorController mc;

	/**
	 * The following are the two motors which are the left and right motors on
	 * the robot.
	 */
	private RMIRegulatedMotor lm;
	private RMIRegulatedMotor rm;
	/**
	 * This is the motor which is going to control the hoist.
	 */
	private RMIRegulatedMotor hm;

	/**
	 * This class will control the operation of the hoist motor.
	 */
	private iHoistMotorController hmc;
	/**
	 * This class will provide data for the compass.
	 */
	// TODO 

	/**
	 * This class will provide data for the battery.
	 */
	private BatterySource bsrc;
	/**
	 * This is the thread instance for the compass.
	 */
	private Thread ct;
	/**
	 * This is the thread instance for the battery source.
	 */
	private Thread bst;
	/**
	 * This is the thread instance for the motion controller.
	 */
	private Thread mct;

	/**
	 * This is the thread instance for the rangefinder.
	 */
	private Thread rft;

	// todo private TouchSensor ts;

	/**
	 * This method will instantiate a new instance of the robot.
	 * 
	 * @throws Exception
	 */
	public Robot(String host) throws Exception {
		ev3 = new RemoteEV3(host);

		System.out.println("Creating Regulated Motor B: " + host);
		lm = ev3.createRegulatedMotor("B", 'L');

		System.out.println("Creating Regulated Motor C: " + host);
		rm = ev3.createRegulatedMotor("C", 'L');

		mc = new PropulsionMotorController(lm, rm);

		System.out.println("Creating Regulated Motor A: " + host);
		hm = ev3.createRegulatedMotor("A", 'M');

		hmc = new HoistMotorController(hm);

		// TODO 
		bsrc = new BatterySource(host);

		// TODO 
		bst = new Thread(bsrc);
		mct = new Thread(mc);
	}

	/**
	 * This method will cause the robot to start operating in a normal fashion.
	 * It will start all subthreads with appropriate delays in between to ensure
	 * reliable operation.
	 */
	public void initiateRobotOperation() {
		ct.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		bst.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		mct.start();
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
		}
		if (rft != null) {
			rft.start();
		}

		try {
			ct.join();
			bst.join();
			mct.join();
			rft.join();
		} catch (Exception ex) {

		}
	}

	/**
	 * This method will return an instance of the propulsion controller
	 * 
	 * @return The robot propulsion controller will be returned.
	 */
	public iRobotPropulsionController getPropulsionController() {
		return mc;
	}

	/**
	 * This method will return a reference to the controller for the hoist.
	 * 
	 * @return The hoist controller will be returned.
	 */
	public iHoistMotorController getHoistController() {
		return hmc;
	}

	/**
	 * This method will add a new subscriber to the compass.
	 * 
	 * @param o
	 *            This is the compass observer that wishes to subscribe.
	 */
	public void addCompassObserver(iCompassObserverInterface o) {
		this.compass.addObserver(o);
	}

	/**
	 * This method will add a new subscriber to the battery.
	 * 
	 * @param o
	 *            This is the battery observer that wishes to subscribe.
	 */
	public void addBatteryObserver(Observer o) {
		this.bsrc.addObserver(o);
	}

	/**
	 * This method will add an observer to the odometer source.
	 * 
	 * @param o
	 *            This is the observer that is to be added.
	 */
	public void addOdometerObserver(Observer o) {
		mc.addObserver(o);
	}

	/**
	 * This method will obtain the battery for the robot.
	 * 
	 * @return This is the battery within the robot.
	 */
	public iBatteryMonitor getBattery() {
		return bsrc;
	}

	/**
	 * This method will return the instance of the compass within the .
	 *
	 * @return
	 */
	public iCompassInterface getCompass() {
		return this.compass;
	}

	/**
	 * This method will obtain the odometer instance for the robot.
	 * 
	 * @return The instance of the odometer will be returned.
	 */
	public iOdometer getOdometer() {
		return mc;
	}

	/**
	 * This method will shutdown all components of the robot in preparation for
	 * the system to exit.
	 * 
	 * @throws Exception
	 */
	public void shutdownRobot() throws Exception {
		bsrc.shutdownBatteryMonitor();
		compass.shutdown();
		mc.shutdownMotorController();
		lm.close();
		rm.close();
		hm.close();
	}
}
