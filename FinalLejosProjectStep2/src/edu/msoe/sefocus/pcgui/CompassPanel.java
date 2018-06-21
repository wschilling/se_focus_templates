package edu.msoe.sefocus.pcgui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Point;
import javax.swing.JPanel;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.dial.DialBackground;
import org.jfree.chart.plot.dial.DialCap;
import org.jfree.chart.plot.dial.DialPlot;
import org.jfree.chart.plot.dial.DialValueIndicator;
import org.jfree.chart.plot.dial.StandardDialFrame;
import org.jfree.chart.plot.dial.StandardDialRange;
import org.jfree.chart.plot.dial.StandardDialScale;
import org.jfree.data.general.DefaultValueDataset;
import org.jfree.data.general.ValueDataset;
import org.jfree.ui.GradientPaintTransformType;
import org.jfree.ui.StandardGradientPaintTransformer;

import edu.msoe.sefocus.core.interfaces.iCompassObserverInterface;

/**
 * This class defines how a compass is to be visualized. It extends a Java
 * JPanel and then builds a circular compass.
 * 
 * @author W Schilling
 *
 */
public class CompassPanel extends JPanel implements iCompassObserverInterface {
	private static final long serialVersionUID = 1L;

	/**
	 * This is the data set that will be used to generate the graph.
	 */
	private DefaultValueDataset dataset;

	/* (non-Javadoc)
	 * @see CompassObserverInterface#updateValue(float)
	 */
	@Override
	public void updateCompassHeading(float value) {
		dataset.setValue(value);
	}

	/**
	 * This helper method will construct the chart that is to be shown.
	 * 
	 * @param title
	 *            This is the title that is to be shown on the chart.
	 * @param valuedataset
	 *            This is the data set that is to be used for the display.
	 * @param lowerBound
	 *            This is the lower value for the graph.
	 * @param upperBound
	 *            This is the upper value for the graph.
	 * @param tickIncrement
	 *            This is the number of degrees between major tick marks.
	 * @param minorTicksPerMajorTick
	 *            This is the number of minor ticks per major tick mark.
	 * @return The return will be an instance of a chart.
	 */
	private static JFreeChart createStandardDialChart(String title, ValueDataset valuedataset, double lowerBound,
			double upperBound, double tickIncrement, int minorTicksPerMajorTick) {
		DialPlot dialplot = new DialPlot();
		dialplot.setDataset(valuedataset);
		dialplot.setDialFrame(new StandardDialFrame());
		dialplot.setBackground(new DialBackground());

		DialValueIndicator dialvalueindicator = new DialValueIndicator(0);
		dialplot.addLayer(dialvalueindicator);
		StandardDialScale standarddialscale = new StandardDialScale(lowerBound, upperBound, -180D, -360D, 15D, 3);
		standarddialscale.setMajorTickIncrement(tickIncrement);
		standarddialscale.setMinorTickCount(minorTicksPerMajorTick);
		standarddialscale.setTickRadius(0.88D);
		standarddialscale.setTickLabelOffset(0.14999999999999999D);
		standarddialscale.setTickLabelFont(new Font("Dialog", 0, 14));
		dialplot.addScale(0, standarddialscale);
		dialplot.addPointer(new org.jfree.chart.plot.dial.DialPointer.Pin());
		DialCap dialcap = new DialCap();
		dialplot.setCap(dialcap);
		return new JFreeChart(title, dialplot);
	}

	/**
	 * This constructor will build a new compass panel.
	 */
	public CompassPanel() {
		super(new BorderLayout());
		dataset = new DefaultValueDataset(10D);
		JFreeChart jfreechart = createStandardDialChart("Dial Demo 1", dataset, -90D, 270D, 45D, 4);
		DialPlot dialplot = (DialPlot) jfreechart.getPlot();
		StandardDialRange standarddialrange = new StandardDialRange(135D, 225D, Color.red);
		standarddialrange.setInnerRadius(0.52000000000000002D);
		standarddialrange.setOuterRadius(0.55000000000000004D);
		dialplot.addLayer(standarddialrange);
		StandardDialRange standarddialrange1 = new StandardDialRange(45D, 135D, Color.orange);
		standarddialrange1.setInnerRadius(0.52000000000000002D);
		standarddialrange1.setOuterRadius(0.55000000000000004D);
		dialplot.addLayer(standarddialrange1);
		StandardDialRange standarddialrange2 = new StandardDialRange(-45D, 45D, Color.green);
		standarddialrange2.setInnerRadius(0.52000000000000002D);
		standarddialrange2.setOuterRadius(0.55000000000000004D);
		dialplot.addLayer(standarddialrange2);

		StandardDialRange standarddialrange3 = new StandardDialRange(225D, 315D, Color.blue);
		standarddialrange3.setInnerRadius(0.52000000000000002D);
		standarddialrange3.setOuterRadius(0.55000000000000004D);
		dialplot.addLayer(standarddialrange3);

		GradientPaint gradientpaint = new GradientPaint(new Point(), new Color(255, 255, 255), new Point(),
				new Color(170, 170, 220));
		DialBackground dialbackground = new DialBackground(gradientpaint);
		dialbackground
				.setGradientPaintTransformer(new StandardGradientPaintTransformer(GradientPaintTransformType.VERTICAL));
		dialplot.setBackground(dialbackground);
		dialplot.removePointer(0);
		org.jfree.chart.plot.dial.DialPointer.Pointer pointer = new org.jfree.chart.plot.dial.DialPointer.Pointer();
		pointer.setFillPaint(Color.yellow);
		dialplot.addPointer(pointer);

		ChartPanel chartpanel = new ChartPanel(jfreechart);
		chartpanel.setPreferredSize(new Dimension(400, 400));
		add(chartpanel);
	}

}