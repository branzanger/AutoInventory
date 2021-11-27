package driver;

import server.DefaultServerSocket;
import exception.*;

public class Driver {

	public static void main(String[] args) throws AutoException {

		int port = 4447;
		DefaultServerSocket server= new DefaultServerSocket(port);
		System.out.println("Starting Auto server at port " + "/" + port);
		server.start();
	}	
		
}