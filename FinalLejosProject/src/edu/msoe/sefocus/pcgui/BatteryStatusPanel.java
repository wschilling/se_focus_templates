package edu.msoe.sefocus.pcgui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import edu.msoe.sefocus.core.interfaces.iBatteryMonitor;


/**
 * This class will handle the display of battery status. In this particular
 * instance, the battery status looks more like a gas gauge than a battery
 * status, but it works.
 * 
 * @author schilling
 * 
 */
public class BatteryStatusPanel extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JSlider batteryChargeSlider;
	private static final int BATTERY_MAX = 1000;
	private static final int BATTERY_MIN = 0;
	private iBatteryMonitor ov = null;

	/**
	 * This constructor will instantiate a new instance of the battery status
	 * panel.
	 * 
	 * @param ov
	 *            This is the battery subject which is to be observed.
	 */
	public BatteryStatusPanel(iBatteryMonitor ov) {
		this.ov = ov;
		// Create the slider
		batteryChargeSlider = new JSlider(JSlider.VERTICAL, BATTERY_MIN,
				BATTERY_MAX, BATTERY_MAX);
		batteryChargeSlider.setMajorTickSpacing(250);
		batteryChargeSlider.setPaintTicks(true);

		// Create the label table
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		labelTable.put(new Integer(0), new JLabel("EMPTY"));
		labelTable.put(new Integer(BATTERY_MAX / 4), new JLabel("1/4"));
		labelTable.put(new Integer(BATTERY_MAX / 2), new JLabel("1/2"));
		labelTable.put(new Integer(BATTERY_MAX * 3 / 4), new JLabel("3/4"));
		labelTable.put(new Integer(BATTERY_MAX * 4 / 4), new JLabel("FULL"));
		batteryChargeSlider.setLabelTable(labelTable);
		batteryChargeSlider.setPaintLabels(true);

		this.setLayout(new BorderLayout());
		this.add(this.batteryChargeSlider, BorderLayout.CENTER);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#setBackground(java.awt.Color)
	 */
	public void setBackground(Color bg) {
		super.setBackground(bg);
		if (this.batteryChargeSlider != null) {
			this.batteryChargeSlider.setBackground(bg);
		}
	}

	@Override
	public void update(Observable obs, Object arg) {
		if (obs == ov) {
			double batteryLifeLeft = ov.getBatteryLifeRemaining();
			batteryChargeSlider.setValue((int) (batteryLifeLeft * 1000));

		}
	}
}
