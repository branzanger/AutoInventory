package adapter;

public interface ScaleAuto {
	//public void addOptionSet(String carName, String optionSetName);
	//public void removeOptionSet(String carName, String optionSetName);
	//public void addOption(String carName, String optionSetName, String OptionName, double optionPrice);
	//public void removeOption(String carName, String optionSetName, String OptionName);
	public void updateOptionSetName(String carName, String optionSetName, String newOptionSetName);
	public void updateOptionPrice(String carName, String optionSetName, String optionName, double newPrice);
}
