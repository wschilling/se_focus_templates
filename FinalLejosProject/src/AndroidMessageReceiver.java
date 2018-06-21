
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import edu.msoe.sefocus.core.interfaces.iBatteryMonitor;
import edu.msoe.sefocus.core.interfaces.iCompassInterface;
import edu.msoe.sefocus.core.interfaces.iOdometer;
import edu.msoe.sefocus.core.realizations.PropulsionMotorController;
import edu.msoe.sefocus.core.realizations.Robot;

/**
 * This class functions as intermediary between the Robot and an Android device
 * that commands the Robot; it listens for commands coming in on a TCP/IP port,
 * and relays those commands to the Robot. This makes use of Java Sockets to
 * receive and send text data, representing commands, over the TCP/IP
 * connection. // 7/15/2015: modified Stop command to acquire status info and
 * send it back to the Android client
 * 
 * @author hornick
 */
public class AndroidMessageReceiver {
	private ServerSocket serverSocket; // this proxy's socket
	private Robot robot; // the robot to command
	private final int PORT; // the TCP port to listen on
	private final ClientConnection cc; // see private class below

	// this inner class groups the client connection attributes
	private class ClientConnection {
		private Socket clientSocket; // the socket on the client that attaches
										// to this server's socket
		private PrintWriter toClientStream; // for writing text to the client
		private Scanner fromClientStream; // for reading text from the client
	}

	/**
	 * Constructor - performs initialization
	 * 
	 * @param robot
	 *            the robot to relay commands to/from
	 * @param port
	 *            the TCP/IP port to listen on for commands from the Android
	 *            device
	 * @throws Exception
	 *             errors in TCP/IP initialization are reported via this
	 *             Exception
	 */
	public AndroidMessageReceiver(Robot robot, int port) throws Exception {
		PORT = port;
		this.robot = robot;
		cc = new ClientConnection();
		initConnection(); // create the network connection
		initCommandListener(); // listen for commands
	}

	// initializes the TCP-based network connection via sockets
	private void initConnection() throws Exception {
		// Create server-side socket and wait for a single client to connect
		try {
			System.out.println("Creating server socket for Android clients...");
			serverSocket = new ServerSocket(PORT); // listen on this port
			InetAddress localIP = InetAddress.getLocalHost();
			System.out.println("Listening from address " + localIP.getHostAddress() + " on port " + PORT + ".");

		} catch (UnknownHostException e) {
			System.out.println("Proxy initialization error: Unknown host.");
			throw new Exception("Proxy initialization error: Unknown host.");
		} catch (IOException e) {
			System.out.println("Proxy initialization error: Can't create server-side socket.");
			throw new Exception("Proxy initialization error: Can't create server-side socket.");
		}
	}

	// creates a worker thread the listens forever for incoming commands coming
	// from the Android
	private void initCommandListener() throws Exception {
		try {
			System.out.println("AndroidProxy socket created; waiting for Android client to connect...");
			cc.clientSocket = serverSocket.accept(); // wait for a client to
														// connect
			System.out.println("Android client connected; starting to send and receive...");
			try {
				cc.toClientStream = new PrintWriter(cc.clientSocket.getOutputStream(), true); // autoflush!
				// c.toClientStream = new
				// ObjectOutputStream(c.clientSocket.getOutputStream() );
			} catch (Exception e) {
				System.out.println("Proxy initialization error: getOutputStream error");
				throw new Exception("Proxy initialization error: getOutputStream error.");
			}
			try {
				// c.fromClientStream = new
				// DataInputStream(c.clientSocket.getInputStream());
				cc.fromClientStream = new Scanner(cc.clientSocket.getInputStream());
			} catch (Exception e) {
				System.out.println("Proxy initialization error: getInputStream error");
				throw new Exception("Proxy initialization error: getInputStream error.");
			}
		} catch (IOException e) {
			System.out.println("Can't connect to Android-side socket.");
			throw new Exception("Proxy initialization error: Can't connect to Android-side socket.");
		}

		Runnable r = new Runnable() {
			public void run() {
				while (true) { // listen for incoming messages forever
					String command = cc.fromClientStream.nextLine(); // read
																		// from
																		// the
																		// Scanner
																		// that
																		// wraps
																		// the
																		// client
																		// input
																		// Socket
					System.out.println(command); // echo the incoming command to
													// the console
					processCommand(command); // dispatch the command to be
												// processed
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
	}

	/**
	 * Processes a specific command received over the network from the Android
	 * device, and relays that command to the robot being controlled.
	 * 
	 * @param command
	 *            the robot-control command received from the Android device
	 */
	private void processCommand(String command) {
		System.out.println("Command received: " + command);
		PropulsionMotorController motorController = (PropulsionMotorController) robot.getPropulsionController();
		iBatteryMonitor battery = robot.getBattery();
		iCompassInterface compass = robot.getCompass();
		iOdometer odo = robot.getOdometer();
		try {

			if (command.equals("Stop")) {
				int velocity = motorController.getVelocity();
				String heading = compass.getCompassHeadingText();
				double distance = odo.getOdometerReading();
				double charge = battery.getBatteryLifeRemaining();
				String status = "Velocity="+velocity+
				"Odometer="+distance+", Battery="+charge+", Compass="+heading;
				cc.toClientStream.println(status);
				motorController.stopRobotMotion();
			} else if (command.equals("Left"))
				motorController.turnRobotLeft();
			else if (command.equals("Right"))
				motorController.turnRobotRight();
			else if (command.equals("Forward"))
				motorController.driveRobotForward();
			else if (command.equals("Backward"))
				motorController.driveRobotBackward();
			else if (command.equals("Faster")) {
				int v = motorController.getVelocity();
				v = (v * 5) / 4;
				motorController.setVelocity(v);
				System.out.println("Set +velocity: " + v);
			} else if (command.equals("Slower")) {
				int v = motorController.getVelocity();
				v = (v * 4) / 5;
				motorController.setVelocity(v);
				System.out.println("Set -velocity: " + v);
			}
			// TODO: add additional 'else if' statements to handle additional
			// commands as needed (e.g. "up", "down" etc).
			else {
				motorController.stopRobotMotion();
				System.out.println("Unknown Command; emergency stop action taken.");

			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
