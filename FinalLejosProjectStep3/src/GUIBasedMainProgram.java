import java.io.FileReader;
import java.util.Properties;

import edu.msoe.sefocus.core.realizations.Robot;
import edu.msoe.sefocus.pcgui.GUI;

public class GUIBasedMainProgram {

	/**
	 * the entry point for this program
	 * 
	 * @param args
	 *            Not used
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		/**
		 * Obtain a properties file. The properties file determines what the IP
		 * address is for the given robot.
		 */
		Properties p = new Properties();
		p.load(new FileReader("project.properties"));

		/**
		 * Instantiate a new instance of a robot.
		 */
		Robot r = new Robot(p.getProperty("host"));
		new GUI(r);
		r.initiateRobotOperation();
	}
}
