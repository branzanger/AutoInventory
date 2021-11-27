package server;

import util.*;

import java.util.Properties;

import adapter.*;
import exception.*;
import model.*;

public class BuildCarModelOptions implements AutoServer {
	private int request;
	
	public String setRequest(int request) {
		this.request=request;
		switch (request) {
		case 1:
			return ("Please enter the filename for the automobile you would like to upload.");
		case 2:
			return("Which car would you like to configure from the following list:\n"+ listAutos());
		default:{
			this.request=-1;
			return("Not a valid Menu Choice. Press any key to continue.");
			}
		}
	}
	
	public int getRequest() {
		return this.request;
	}
	
	public String listAutos() {
		BuildAuto b = new BuildAuto();
		return b.listAutos();
	};
	
	public Automobile getAuto(String fullAutoName) {
		BuildAuto b = new BuildAuto();
		return b.getAuto(fullAutoName);
	};

	public String addAuto(Properties prop) {
		BuildAuto b = new BuildAuto();
		return b.addAuto(prop);
	}
	
	public String configAuto(Properties prop) {
		BuildAuto b = new BuildAuto();
		return b.configAuto(prop);
	}
}