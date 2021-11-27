//Brandon Zhang
package model;

import java.io.Serializable;
import java.util.ArrayList;

public  class OptionSet implements OptionSetInterface, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private ArrayList<Option> options;
	private Option choice;

	public  OptionSet() {
		this.name = "";
		options = new ArrayList<Option>();
	}

	public  OptionSet(String name) {
		this.name = name;
		options = new ArrayList<Option>();
	}

	/**
	 * @param index of option wanted
	 * @return option selected by i
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public Option getOption(int i) throws ArrayIndexOutOfBoundsException {
		return options.get(i);
	}

	public void setOptions(ArrayList<Option> options) {
		this.options = options;
	}
	
	public Option getChoice() {
		return choice;
	}

	public void setChoice(Option choice) {
		this.choice = choice;
	}
	
    protected void setOptionChoice(String choice) {
    	if (choice != null) 
    		this.choice = findOption(choice);
    }

	/**
	 * @param name  of option
	 * @param price of option(float)
	 */
	public void addOption(String name, double price) {
		options.add(new Option(name, price));
	}
	
	/**
	 * @param index of location to set
	 * @param name  of option
	 * @param price of option(float)
	 * @throws ArrayIndexOutOfBoundsException
	 */
	protected void setOption(String name, float price, int i) throws ArrayIndexOutOfBoundsException {
		options.set(i, new Option(name, price));
	}

	protected void setOptionName(String name, int i) {
		options.get(i).setName(name);
	}

	protected void setOptionPrice(float price, int i) {
		options.get(i).setPrice(price);
	}

	protected String getOptionName(int i) {
		return options.get(i).getName();
	}

	protected double getOptionPrice(int i) {
		return options.get(i).getPrice();
	}

	protected Option findOption(String name) {
		for (int i = 0; i < options.size(); i++) {
			if (options.get(i).getName().equals(name)) {
				return options.get(i);
			}
		}
		return null;
	}
	
	protected Option getOptionChoice() {
		return choice;
	}
	
	protected String getOptionChoiceName() {
		return choice.getName();
	}
	
	protected double getOptionChoicePrice() {
		return choice.getPrice();
	}
	
	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected ArrayList<Option> getOptions() {
		return options;
	}
	
	protected void newOptionSet() {
		this.options = new ArrayList<Option>();
	}
	
	protected void setOptionSet(ArrayList<Option> options) {
		this.options = options;
	}

	protected int getOptionSetLength() {
		return options.size();
	}
	
    protected boolean isOptionNull() {
    	return options == null;
    }
    
    protected int findOptionIndex(String name) {
        if (!isOptionNull()) {
        	for (int i = 0; i < options.size(); i++) {
        		if ((options.get(i).getName()).equals(name)) {
        			return i;
        		}
        	}
        }
    	return -1;
    }
	
    protected void deleteOption(String name) {
    	int index = findOptionIndex(name);
        if (index != -1) 
        	options.remove(index);
    }

    protected void updateOption(String name, Option newOpt) {
    	int index = findOptionIndex(name);
    	if (index != -1) options.set(index, newOpt);
    }

    protected void updateOptionName(String name, String newName) {
    	int index = findOptionIndex(name);
    	if (index != -1) options.get(index).setName(newName);
    }

    protected void updateOptionPrice(String name, double price) {
    	int index = findOptionIndex(name);
    	if (index != -1) options.get(index).setPrice(price);
    }

	protected void print() {
		for (Option i : options) {
			System.out.println(i.getName());
		}
	}
	
	public  String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Option [name");
		str.append(name);
		str.append(", options=");
		str.append(getOptionSetLength());
		str.append("]");
		return str.toString();		
	}

}
