package edu.msoe.sefocus.core.realizations;

import edu.msoe.sefocus.core.interfaces.iHoistMotorController;
import lejos.remote.ev3.RMIRegulatedMotor;

/**
 * This class controls the implementation of the hoist motor. It will raise and
 * lower the hoist on the robot.
 * 
 * @author schilling
 *
 */
public class HoistMotorController implements iHoistMotorController {
	private RMIRegulatedMotor hoistMotor;

	/**
	 * This constructor will instantiate a new instance of the hoist motor
	 * controller.
	 * 
	 * @param hm
	 *            This is the motor assigned to serve as the hoist motor.
	 * @throws Exception
	 */
	public HoistMotorController(RMIRegulatedMotor hm) throws Exception {
		super();
		this.hoistMotor = hm;
		hoistMotor.setSpeed(50);
		// Note: This will reset the value to 0. The assumption is the hoist is
		// lowered when this occurs.
		hoistMotor.resetTachoCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.msoe.sefocus.core.iHoistMotorController#raiseHoist()
	 */
	@Override
	public void raiseHoist() throws Exception {
		// TODO 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.msoe.sefocus.core.iHoistMotorController#lowerHoist()
	 */
	@Override
	public void lowerHoist() throws Exception {
		// TODO 
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.msoe.sefocus.core.iHoistMotorController#stopHoist()
	 */
	@Override
	public void stopHoist() throws Exception {
		hoistMotor.stop(true);
	}
}
