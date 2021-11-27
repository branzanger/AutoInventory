package scale;

import exception.AutoException;

public interface EditThread  {
	
	public abstract void updateOptionSetName(String carName, 
			String optionSetName, String newOptionSetName) throws AutoException;
	public abstract void updateOptionPrice(String carName, 
			String optionSetName, String option, double newPrice) throws AutoException;
	public abstract void updateOptionName(String carName, 
			String optionSetName, String optionName, String newOptionName) throws AutoException;
}