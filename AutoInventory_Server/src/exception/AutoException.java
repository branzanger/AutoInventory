package exception;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class AutoException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int errorNo;
	private String errorMsg;
	
	public AutoException() {
		this(-1);
	}
	
	public AutoException(int errorNo) {
		this.errorNo = errorNo;
		switch (errorNo) { 
	        case 11: 
	            errorMsg = "Missing file name"; 
	            break; 
	        case 21: 
	        	errorMsg = "Missing base price"; 
	            break; 
	        case 31: 
	        	errorMsg = "Missing option set array size"; 
	            break; 
	        case 41: 
	        	errorMsg = "Missing option set name"; 
	            break; 
	        case 51: 
	        	errorMsg = "Missing option name"; 
	            break; 
	        case 61: 
	        	errorMsg = "Wrong make and model name"; 
	            break; 
	        case 71: 
	        	errorMsg = "Invalid Operation"; 
	            break; 
	        case 81: 
	        	errorMsg = "Invalid Automobile Object"; 
	            break; 
	        case -1: 
	        	errorMsg = "Some unknown error occured!"; 
	            break; 
		} 
		log();
	}
	
	public AutoException(int errorNo, String errorMsg) {
		this.errorNo = errorNo;
		this.errorMsg = errorMsg;
		log();
	}
	
	public AutoException(String errorMsg) {
		this.errorNo = -1;
		this.errorMsg = errorMsg;
		log();
	}
	
	public void log() {
		FileWriter file;
		try {
			file = new FileWriter("Logs.txt", true);
			BufferedWriter buff = new BufferedWriter(file);
			buff.write("Error Number: " + this.errorNo + " | Error Message:" + this.errorMsg);
			buff.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	public void log() {
		System.out.printf("\n~~~~~~~Error %d: %s\n", errorNum, errorMsg);
//		PrintWriter writer = new PrintWriter("D:\\Coding Projects\\CIS 35B\\Lab 1\\data\\exception-log.txt", "UTF-8");
//		writer.printf("\n~~~~~~~Error number %d: %s\n", errorNum, errorMsg);
//		writer.close();
	}*/
	
	public String fix() {
		
		Fix1to100 fix = new Fix1to100();
		return fix.fix(errorNo);

	}
	
}


