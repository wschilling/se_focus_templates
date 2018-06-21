package edu.msoe.sefocus.pcgui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import edu.msoe.sefocus.core.interfaces.iRobotPropulsionController;

/**
 * This class defines the controls which will adjust how the robot moves.
 * 
 * @author schilling
 *
 */
public class RobotMotionControlPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private iRobotPropulsionController motorController;
	private final int FORWARD = 1;
	private final int BACKWARD = 2;
	private final int RIGHT = 3;
	private final int LEFT = 4;
	private final int STOP = 0;
	private int robotMotion = STOP;

	/**
	 * This constructor will instantiate a new instance of the robot motion
	 * controller, which will control the operation of the robot.
	 * 
	 * @param pmctrl
	 *            This is the instance of the robot propulsion motor controller
	 *            that is to be controlled by this panel.
	 */
	public RobotMotionControlPanel(iRobotPropulsionController pmctrl) {
		motorController = pmctrl;

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());

		BorderLayout myLayout = new BorderLayout();
		this.setLayout(myLayout);

		JButton forwardButton = new JButton("/\\");
		JButton leftButton = new JButton("<-");
		JButton rightButton = new JButton("->");
		JButton backwardButton = new JButton("\\/");
		JButton stopButton = new JButton("STOP");

		mainPanel.add(forwardButton, BorderLayout.NORTH);
		mainPanel.add(leftButton, BorderLayout.WEST);
		mainPanel.add(rightButton, BorderLayout.EAST);
		mainPanel.add(backwardButton, BorderLayout.SOUTH);
		mainPanel.add(stopButton, BorderLayout.CENTER);

		add(mainPanel, BorderLayout.WEST);
		final JSlider speedControl = new JSlider(JSlider.VERTICAL, 0, 1000, 500);
		speedControl.setMajorTickSpacing(250);
		speedControl.setPaintTicks(true);

		add(new JLabel("    Robot Speed"), BorderLayout.CENTER);

		add(speedControl, BorderLayout.EAST);

		speedControl.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				try {

					motorController.setVelocity(speedControl.getValue());
					switch (robotMotion) {
					case STOP:
					default:
						break;
					case FORWARD:
						motorController.driveRobotForward();
						break;
					case BACKWARD:
						motorController.driveRobotBackward();
						break;
					case LEFT:
						motorController.turnRobotLeft();
						break;
					case RIGHT:
						motorController.turnRobotRight();
						break;
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					motorController.stopRobotMotion();
					robotMotion = STOP;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		leftButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					motorController.turnRobotLeft();
					robotMotion = LEFT;
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		rightButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					motorController.turnRobotRight();
					robotMotion = RIGHT;

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		forwardButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					motorController.driveRobotForward();
					robotMotion = FORWARD;

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		backwardButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					motorController.driveRobotBackward();
					robotMotion = BACKWARD;

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}
}
