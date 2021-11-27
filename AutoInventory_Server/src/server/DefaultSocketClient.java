package server;

import java.net.*;
import java.io.*;
import java.util.Properties;
import adapter.*;
import model.*;
import util.FileIO;

public class DefaultSocketClient extends Thread  {

	////////// PROPERTIES //////////

	private static final boolean DEBUG_FLAG = true;
	private static final int WAITING = 0;
    private static final int SENTFIRSTMENU = 1;
	private static final int REQUESTSET = 2;

    private int state = WAITING;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	private Socket clientSocket;
	private BuildCarModelOptions carBuilder;

	////////// CONSTRUCTORS //////////

	public DefaultSocketClient(Socket clientSocket) {
		this.clientSocket = clientSocket;
	}

	////////// INSTANCE METHODS //////////

	public void handleConnection(Socket sock) {
		if (DEBUG_FLAG)
			System.out.println("Creating server object streams ... ");
		try {
			out = new ObjectOutputStream(sock.getOutputStream());
			in = new ObjectInputStream(sock.getInputStream());
		}
		catch (IOException e) {
			System.err.println("Error creating server object streams ... ");
			System.exit(1);
		}


		carBuilder = new BuildCarModelOptions();
		String menu = "\nEnter 1 to upload a new Automobile\n"
				+ "Enter 2 to configure an Automobile\n"
				+ "Enter 3 to get an Automobile\n"
				+ "Enter 0 to terminate connection\n";

		try {
			do {
				
				if (state == WAITING) {
					if (DEBUG_FLAG)
						System.out.println("Sending client interaction choices ... ");
					state = SENTFIRSTMENU;
					sendOutput(menu);
					
				}

				if (state == SENTFIRSTMENU) {
					int request;
					if (DEBUG_FLAG)
						System.out.println("Reading client request ... ");
					try {
						request = Integer.parseInt(in.readObject().toString());
					}
					catch (Exception e){
						request = -1;
						state=WAITING;
						String response="Invalid menu choice. Press any key to continue.";		
						sendOutput(response);
					}
					if (DEBUG_FLAG)
						System.out.println(request);
					if (request == 0)
						break;
					//tests for invalid menu choice
					if (request <0 || request > 3){
						state=WAITING;
						String response="Invalid menu choice. Press any key to continue";		
						sendOutput(response);
					}
					else {
						if (DEBUG_FLAG) {
							System.out.println("Sending client request follow-up ... ");
							System.out.println(carBuilder.setRequest(request));
							}
						state=REQUESTSET;
						sendOutput(carBuilder.setRequest(request));
					}
				}
				if (state == REQUESTSET) {
					if (DEBUG_FLAG)
						System.out.println("Sent request");
					int request = carBuilder.getRequest();
					if (request >= 1 && request <= 3)
						handleInput(request);
					else
						state=WAITING;
				}
				
			} while (in.readObject() != null);

			if (DEBUG_FLAG)
				System.out.println("Closing server input stream for client " + sock.getInetAddress() + " ... ");
			in.close();
		}
		catch (IOException e) {
			System.err.println("Error handling client connection ... " + e.getMessage());
			System.exit(1);
		}
		catch (ClassNotFoundException e) {
			System.err.println("Error in uploaded object, object may be corrupted ... ");
			System.exit(1);
		}
	}

	public void sendOutput(Object obj) {
		try {
			out.flush();
			out.writeObject(obj);
		}
		catch (IOException e) {
			System.err.println("Error returning output to client ...  "+ e.getMessage());
			System.exit(1);
		}
	}

	public void handleInput(int request) {
		//Object fromClient = null;
		String response = null;

		try {
			switch (request) {
				case 1: //Client request to build Automobile
					if (DEBUG_FLAG)
						System.out.println("Waiting for client to upload file ... ");
					try {
						Properties prop = (Properties)in.readObject();
						if (DEBUG_FLAG)
							System.out.println("Get Prop :" + prop.toString());
						response = (String)carBuilder.addAuto(prop);
						if (DEBUG_FLAG) {
							System.out.println("Adding new Automobile to database ... ");
							BuildAuto b = new BuildAuto();
							b.printAuto();
						}
					}
					catch (Exception e) {
							response ="There was a problem with your upload. Please try again.  Press any key to continue.";
			
						}
					finally {
						state=WAITING;
						sendOutput(response);
					}
					break;

				case 2: //Client request to configure Automobile
					if (DEBUG_FLAG)
						System.out.println("Waiting for client to config Automobile ... ");
					try {
						Properties prop = (Properties) in.readObject();
						response = carBuilder.configAuto(prop);
						if (response ==null) {
							response="Not able to find your vehicle. Please press any key to continue.";
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
						state=WAITING;
						sendOutput(response);
					}
					break;

				case 3: //Client request to get list of Automobiles
					//if (DEBUG_FLAG)
					//	System.out.println("Waiting for client to enter carName ... ");
					try {
						//String carName = (String) in.readObject();
						//if (DEBUG_FLAG)
						//	System.out.println("Sending Automobile object to client ... ");
						//Automobile auto = new BuildAuto().getAutomobile(carName);
						//FileIO io = new FileIO();
						//response = io.serializeToString(auto);
						response = carBuilder.listAutos();
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
						state=WAITING;
					}
					if (response ==null) {
						response="Not able to find your vehicle. Please press any key to continue.";
					}
					sendOutput(response);
					break;
					
				default: //Invalid requests
					state=WAITING;
					response ="Invalid . Please press any key to continue.";
					sendOutput(response);
					break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(-1);
		}
		
	}

	@Override
	public void run() {
		handleConnection(clientSocket);

		//Close client output stream
		if (DEBUG_FLAG)
			System.out.println("Closing server output stream for client " + clientSocket.getInetAddress() + " ... ");
		try {
			out.close();
		}
		catch (IOException e) {
			System.err.println("Error closing server output stream for client " + clientSocket.getInetAddress() + " ... ");
		}

		//Close client socket
		if (DEBUG_FLAG)
			System.out.println("Closing client socket " + clientSocket.getInetAddress() + " ... ");
		try {
			clientSocket.close();
		}
		catch (IOException e) {
			System.err.println("Error closing client socket " + clientSocket.getInetAddress() + " ... ");
		}
	}

}