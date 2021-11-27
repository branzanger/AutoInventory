package client;

import model.*;
import adapter.*;
import java.util.*;

public class SelectCarOptions {
	private static boolean DEBUG=true;
	////////// PROPERTIES //////////
	private Scanner in = new Scanner(System.in);

	////////// CONSTRUCTORS //////////

	public SelectCarOptions() {

	}

	////////// INSTANCE METHODS //////////

	public void configureAuto(Object obj) {
		if (DEBUG)
			System.out.println("It's configuration time! In SelectCarOptions!\n");
		Automobile a = (Automobile)obj;
		BuildAuto b = new BuildAuto();
		//b.updateO(a);
		System.out.print("You have configured the following:\n");
		a.print();
		System.out.println("");
		System.out.println("Press any key to continue.");
		
	}

	public boolean isAutomobile(Object obj) {
		return (obj instanceof Automobile);
	}

}