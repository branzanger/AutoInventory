package adapter;

import exception.AutoException;

public interface UpdateAuto {
	
	public void updateOptionSetName(String carName, String OptionSetname, String newName) throws AutoException;
	public void updateOptionPrice(String carName, String Optionname, String Option, 
			double newprice) throws AutoException;
	public void updateOptionChoice(String carName, String OptionSetname, String optionChoice) throws AutoException;
}
