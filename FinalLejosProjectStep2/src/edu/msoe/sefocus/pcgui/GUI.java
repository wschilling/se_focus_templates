package edu.msoe.sefocus.pcgui;

/**
 * This file is not copyrighted.  Others are free to copy, modify, or redistribute this file as is seen fit.
 * 
 * @author hornick
 * $Header: d:\cvs/webpage/msoe/se1020/GUI.java,v 1.1 2007/12/13 22:31:00 schilling Exp $
 * $Name:  $ 
 * $Revision: 1.1 $ 
 * $Date: 2007/12/13 22:31:00 $ 
 * $Log: GUI.java,v $
 * Revision 1.1  2007/12/13 22:31:00  schilling
 * Added archived to MSOE CVS repository.
 *
 * Revision 1.1  2007/11/30 23:01:57  wws
 * Updated SE1020 material on the website, as well as uploaded notes for SE-4831.
 *
 *
 *
 **/
import java.awt.Color;
import java.awt.Container;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import edu.msoe.sefocus.core.realizations.Robot;

public class GUI {
	private Container contentPane;
	private JFrame jfMainWindow; // main window
	private OdometerDisplayPanel odometerDisplay;
	private CompassPanel compassDisplay;
	private RobotMotionControlPanel motionControlUI;
	private HoistControlPanel hoistControlUI;

	private BatteryStatusPanel batteryStatusDisplay;

	/**
	 * constructor for the GUI class creates the JFrame window and subcomponents
	 */
	public GUI(Robot robot) {
		// create a JFrame window and set its properties
		jfMainWindow = new JFrame("MSOE WALL-G");
		jfMainWindow.setSize(620, 260); // window size
		jfMainWindow.setLocation(10, 10); // window location
		jfMainWindow.setResizable(true); // not resizeable

		/* !!!! */jfMainWindow.setVisible(true); // make visible!!!

		// EXIT_ON_CLOSE destroys all windows
		jfMainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// get the container that is part of the JFrame
		contentPane = jfMainWindow.getContentPane();

		// we're using absolute positioning
		contentPane.setLayout(null);

		// override default background color
		contentPane.setBackground(Color.GRAY);

		motionControlUI = new RobotMotionControlPanel(robot.getPropulsionController());
		motionControlUI.setBounds(115, 110, 300, 100);

		hoistControlUI = new HoistControlPanel(robot.getHoistController());
		hoistControlUI.setBounds(505, 110, 100, 100);

		batteryStatusDisplay = new BatteryStatusPanel(robot.getBattery());
		batteryStatusDisplay.setBounds(5, 5, 100, 200);
		robot.addBatteryObserver(batteryStatusDisplay);

		odometerDisplay = new OdometerDisplayPanel(robot.getOdometer());
		odometerDisplay.setBounds(115, 5, 384, 100);
		robot.addOdometerObserver(odometerDisplay);

		// TODO 
		// TODO 
		// TODO 

		contentPane.add(this.odometerDisplay);
		contentPane.add(this.batteryStatusDisplay);
		// TODO 
		contentPane.add(motionControlUI);
		contentPane.add(hoistControlUI);

		// always call validate() after adding components
		// if the window is already visible
		contentPane.validate();
		// ...and this too; forces a refresh
		contentPane.repaint();
		contentPane.setFocusable(true);
		this.setDashColor(Color.CYAN);

		jfMainWindow.addWindowListener(new WindowListener() {

			@Override
			public void windowActivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosed(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent arg0) {
				try {
					robot.shutdownRobot();
				} catch (Exception e) {
				}

			}

			@Override
			public void windowDeactivated(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowOpened(WindowEvent arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	/**
	 * This method will set the background color of all components on the
	 * display as is appropriate.
	 * 
	 * @param c
	 *            This is the color to be set.
	 */
	private void setDashColor(Color c) {
		this.contentPane.setBackground(c);
		this.jfMainWindow.setBackground(c);
		this.batteryStatusDisplay.setBackground(c);
	}

}
