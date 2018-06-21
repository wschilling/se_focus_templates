package edu.msoe.sefocus.pcgui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

import edu.msoe.sefocus.core.interfaces.iHoistMotorController;

/**
 * This class deals with the control of the hoist, allowing it to be raised up
 * and lowed as is necessary.
 * 
 * @author schilling
 * 
 */
public class HoistControlPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private iHoistMotorController hc;

	/**
	 * This constructor will instantiate a new instance of the hoist control
	 * panel.
	 * 
	 * @param mhc
	 *            This is the interface to the motor that is to control the
	 *            panel.
	 */
	public HoistControlPanel(iHoistMotorController mhc) {
		this.hc = mhc;
		BorderLayout myLayout = new BorderLayout();
		setLayout(myLayout);

		JButton upButton = new JButton(" UP ");
		add(upButton, BorderLayout.NORTH);

		JButton stopButton = new JButton("STOP");
		add(stopButton, BorderLayout.CENTER);

		JButton downButton = new JButton("DOWN");
		add(downButton, BorderLayout.SOUTH);

		upButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					hc.raiseHoist();
				} catch (Exception e1) {
				}
			}
		});

		stopButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					hc.stopHoist();
				} catch (Exception e1) {
				}
			}
		});

		downButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					hc.lowerHoist();
				} catch (Exception e1) {
				}
			}
		});
	}
}
