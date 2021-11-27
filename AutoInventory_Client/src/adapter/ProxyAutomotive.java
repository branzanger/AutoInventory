package adapter;
import java.util.LinkedHashMap;

import exception.AutoException;
import model.*;
import util.FileIO;

public abstract class ProxyAutomotive {
	
	private static LinkedHashMap<String, Automobile> a1 = 
			new LinkedHashMap<String, Automobile>();;
	
	public static synchronized Automobile getAutomobile(String carName) {
		return a1.get(carName);
	}

	public synchronized void updateOptionSetName(String carName, String optionSetName, String newName) 
		throws AutoException {
		Automobile a1 = getAutomobile(carName);
		if (a1 != null)
			a1.updateOptionSetName(newName, optionSetName);
	}

	public synchronized void updateOptionName(String carName, 
			String optionSetName, String optionName, String newOptionName) throws AutoException {
		Automobile a1 = getAutomobile(carName);
		if (a1 != null)
			a1.updateOptionName(optionSetName, optionName, newOptionName);
	}
	
	public synchronized void updateOptionChoice(String carName, 
			String optionSetName, String optionChoice) throws AutoException {
		Automobile a1 = getAutomobile(carName);
		if (a1 != null)
			a1.updateOptionChoice(optionSetName, optionChoice);
	}
	
	public synchronized void chooseOption(String carName, String optionSetName, String optionName)
			throws AutoException {
		Automobile a1 = getAutomobile(carName);
		if (a1 != null)
			a1.setOptionChoice(optionSetName, optionName);
	}

	public synchronized void updateOptionPrice(String carName, String optionSetName, 
			String optionName, double newPrice) throws AutoException {
		Automobile a1 = getAutomobile(carName);
		if (a1 != null)
			a1.updateOptionPrice(optionSetName, optionName, newPrice);
	}

	public synchronized void buildAuto(String fileName) throws AutoException{
		FileIO IO = new FileIO();
		Automobile auto = IO.buildAuto(fileName);
		a1.put(auto.getName(), auto);		
	}
	
	public synchronized void buildAuto(String carName, String fileName) throws AutoException{
		FileIO IO = new FileIO();
		a1.put(carName,IO.buildAuto(fileName));		
	}
	
	/*
	public synchronized void buildAuto(String fileName, LinkedHashMap<String, Automobile> autoList) {
		FileIO f = new FileIO();
		try {
			Automobile auto = f.buildAuto(fileName);	
			autoList.put(auto.getName(), auto);
		} catch (AutoException e) {
			e.printStackTrace();
		}
	}
	*/

	public synchronized void printAuto(String modelName, String fileName) {
		FileIO IO = new FileIO();
		IO.print(a1.get(modelName), fileName);
	}
	
	public synchronized void printAuto(String name) {
		getAutomobile(name).print();
	}
	
	public synchronized void printAuto() {
		a1.forEach((key,value) -> {
		    System.out.println("model:" + key);
		    value.printDetail();
		});
	}
	
	public synchronized void fix(int errorNo) {
		new AutoException(errorNo).fix();
	}
	
	public synchronized void addAutomobile(Automobile auto) {
		a1.put(auto.getModel(), auto);
	}
	
	public synchronized LinkedHashMap<String, Automobile> getA1() {
		return a1;
	}

	public synchronized void setA1(LinkedHashMap<String, Automobile> a1) {
		this.a1 = a1;
	}
	
	
	
}
