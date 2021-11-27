package driver;
import client.*;

import exception.*;


public class Driver {

	public static void main(String[] args) throws AutoException {
		DefaultSocketClient client = new DefaultSocketClient("127.0.0.1" ,4447);
		client.start();	
	}
}