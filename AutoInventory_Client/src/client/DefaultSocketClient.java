package client;

import java.net.*;
import java.io.*;
//import adapter.DEBUG_FLAGgable;

public class DefaultSocketClient extends Thread  {

	////////// PROPERTIES //////////

	private ObjectOutputStream out;
	private ObjectInputStream in;
	private BufferedReader stdIn;
	private Socket sock;
	private String strHost;
	private int iPort;
	private CarModelOptionsIO clientFTP;
	private SelectCarOptions clientProtocol;
	private boolean DEBUG_FLAG=true;

	////////// CONSTRUCTORS //////////

	public DefaultSocketClient(String strHost, int iPort) {
		this.strHost = strHost;
		this.iPort = iPort;
	}

	////////// INSTANCE METHODS //////////

	public void establishConnection() {
		try {
			if (DEBUG_FLAG)
				System.out.println("Connecting to host ... ");
			this.sock = new Socket(strHost, iPort);

			if (DEBUG_FLAG)
				System.out.println("Connected to host, creating object streams ... ");
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
			stdIn = new BufferedReader(new InputStreamReader (System.in));

			clientFTP = new CarModelOptionsIO();
			clientProtocol = new SelectCarOptions();
		}
		catch (IOException e) {
			System.err.println("Error obtaining I/O for connection to host ... ");
			System.exit(1);
		}
	}

	public void handleConnection() {
		Object from, to = null;

		try {
			if (DEBUG_FLAG)
				System.out.println("Communicating with host ... ");

			while ((from = in.readObject()) != null) {
				if (DEBUG_FLAG)
					System.out.println("Received server response ... ");
				System.out.println(from.toString());

				if (clientProtocol.isAutomobile(from))
					clientProtocol.configureAuto(from);

				System.out.println("Response to server: ");
				to = stdIn.readLine();
				//if it's a property file it's good, otherwise, bad input
				if (to.toString().contains(".prop")) {
					to = clientFTP.loadPropsFile(to.toString());
				}
				
				if (DEBUG_FLAG)
					System.out.println("Sending " + to + " to server ... ");
				sendOutput(to);
				System.out.println("");
		
				if (to.equals("0")) {			
					if (DEBUG_FLAG)
						System.out.println("Closing client input stream ... ");
					in.close();
					break;
					
				}

			}
		}
		catch (ClassNotFoundException e1) {
			System.err.println("Error in downloaded object, object may be corrupted ... ");
			System.exit(1);
		}
		catch (IOException e2) {
			System.err.println("Error in I/O stream ... " + e2.getMessage());
			System.exit(1);
		}

		finally {
			if (DEBUG_FLAG)
				System.out.println("finally!");
		}
	}

	public void sendOutput(Object obj) {
		try {
			out.writeObject(obj);
		}
		catch (IOException e) {
			System.err.println("Error in I/O stream while sending object to host ... ");
			System.exit(1);
		}
	}

	@Override
	public void run() {
		System.out.print("client>dsc: client is open for business");
		establishConnection();
		handleConnection();
		try {
			if (DEBUG_FLAG)
				System.out.println("Closing client output stream ... ");
			out.close();

			if (DEBUG_FLAG)
				System.out.println("Closing client console input stream ... ");
			stdIn.close();

			if (DEBUG_FLAG)
				System.out.println("Closing client socket ... ");
			sock.close();
		}
		catch (IOException e) {
			System.err.println("Error ending client connection ... ");
			System.exit(1);
		}
	}

}