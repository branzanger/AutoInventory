package adapter;

import java.util.LinkedHashMap;

import exception.AutoException;
import model.Automobile;

public interface CreateAuto {
	
	/*
	 * Build an instance of Automobile from a text file
	 */
	//public void BuildAuto(String filename, LinkedHashMap<String, Automobile> autoList) throws AutoException;
	public void buildAuto(String carName, String filename) throws AutoException;
	public void buildAuto(String filename) throws AutoException;
	/*
	 * Searches and prints the properties of a given automobile model
	 */
	//public void PrintAuto(String modelName, String fileName);
	public void printAuto(String modelName);
	//public void PrintAuto();
	public void chooseOption(String modelName, String optionSetName, String optionName) throws AutoException;

}
