//Brandon Zhang
package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Automobile implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private String make;
	private String model;
	private double basePrice;
	private ArrayList<OptionSet> optionSets;// make this an arraylist
	//private ArrayList<Option> choices;
	
	
	public Automobile() {
		super();
		// TODO Auto-generated constructor stub
	}

    public  Automobile(String name, String make, String model, double basePrice) {
        this.name = name;
        this.make = make;
        this.model = model;
        this.basePrice = basePrice;
        optionSets = new ArrayList<OptionSet>();
        //choices = new ArrayList<Option>();
    }
	/**
	 * Constructor with all parameters
	 * @param make | Make of the Automobile
	 * @param model | Model of the Automobile
	 * @param basePrice | basePrice of the Automobile
	 */
	public Automobile(String name, String make, String model, double basePrice, ArrayList<OptionSet> optionSets,
			ArrayList<Option> choices) {
		super();
		this.name = name;
		this.make = make;
		this.model = model;
		this.basePrice = basePrice;
		this.optionSets = optionSets;
		//this.choices = choices;
	}

	/*
	public synchronized Automobile(String make, String model, double basePrice) {
		this.make = make;
		this.model = model;
		this.basePrice = basePrice;
		this.optionSets = new ArrayList<OptionSet>();
		this.choice = new ArrayList<Option>();
	}*/
	
	
	
	public synchronized String getName() {
		return name;
	}

	public synchronized void setName(String name) {
		this.name = name;
	}

	//option set methods
	public synchronized OptionSet getOptionSet(int i) {
		return optionSets.get(i);
	}
	
	public synchronized int getOptionSetsLength() {
		return optionSets.size();
	}
	
	/*
	public synchronized ArrayList<Option> getChoice() {
		return choices;
	}

	public synchronized void setChoice(ArrayList<Option> choices) {
		this.choices = choices;
	}
	*/

	public synchronized OptionSet findOptionSet(String name) {
		for (OptionSet os : optionSets) {
			if(os.getName().equalsIgnoreCase(name)) {
				return os;
			}
		}
		return null;
	}
	
	public synchronized void addOptionSet(String name) {
		optionSets.add(new OptionSet(name));
	}
	
	public synchronized void addOptionSet(String name, OptionSet optionSet) {
		optionSets.add(optionSet);
	}
	
	/**
	 * @param name : name of OptionSet
	 * @param y : location in OptionSet[]
	 */
	public synchronized void setOptionSet(String name, int y) {
		optionSets.set(y, new OptionSet(name));
	}
	
	/**
	 * @param optionSet : OptionSet you with to set
	 * @param y : location in OptionSet[]
	 */
	public synchronized void setOptionSet(OptionSet optionSet, int i) {
		optionSets.set(i, optionSet);
	}
	
	public synchronized String getOptionSetName(int i) {
		return optionSets.get(i).getName();
	}
	
	public synchronized void setOptionSetName(String name, int i) {
		optionSets.get(i).setName(name);
	}
	
	
	public synchronized void deleteOptionSet(int i) {
		optionSets.remove(i);
	}
	
	public synchronized void deleteOptionSet(String name) {
		optionSets.remove(findOptionSet(name));
	}
	
	public synchronized int getOptionSetLength(int i) {
		return optionSets.get(i).getOptionSetLength();
	}
	
	/**
	 * @param i : location of Option in ArrayList<Option>(OptionSet)
	 * @param x : location of OptionSet in ArrayList<OptionSet>(Automotive)
	 * @return name
	 */
	public synchronized String getOptionName(int i, int x) {
		return optionSets.get(x).getOptionName(i);
	}

	
	//option methods
	/**
	 * 
	 * @param name : name of option
	 * @param price : price of option
	 * @param x : location of OptionSet in ArrayList<OptionSet>(Automotive)
	 */
	public synchronized void addOption(String optionSetName, String ame, double price) {
		findOptionSet(optionSetName).addOption(ame, price);
	}
	
	/**
	 * @param i : location of Option in ArrayList<Option>(OptionSet)
	 * @param x : location of OptionSet in ArrayList<OptionSet>(Automotive)
	 * @return price
	 */
	public synchronized double getOptionPrice(int i, int x) {
		return optionSets.get(x).getOptionPrice(i);
	}
	
	/*
    public synchronized void deleteOptionSet(String name) { 
    	if (!areOptionSetsNull()) {
        	for (int i = 0; i < optionSets.size(); i++) {
        		if (optionSets.get(i) != null && (optionSets.get(i).getName()).equals(name))
        			optionSets.set(i, null);	
        	}
        }
    }*/

    public synchronized void deleteOption(String optionSetsName, String optName) {
    	if (optionSets != null) {
        	for (int i = 0; i < optionSets.size(); i++) {
        		if (optionSets.get(i) != null && (optionSets.get(i).getName()).equals(name))
        			optionSets.get(i).updateOption(optName, null);	
        	}
        }
    }
    
    public synchronized void updateOptionSet(String name, OptionSet newoptionSets) {
    	if (optionSets != null) {
        	for (int i = 0; i < optionSets.size(); i++) {
        		if (optionSets.get(i) != null && (optionSets.get(i).getName()).equalsIgnoreCase(name)) {
        			optionSets.set(i, newoptionSets);
        		}
        	}
        }
    }

    
    public synchronized void updateOptionSetName(String name, String newName) {
    	findOptionSet(name).setName(newName);
    }
    
    public synchronized void updateOption(String optionSetsName, String optName, Option newOpt) {
    	findOptionSet(optionSetsName).updateOption(optName, newOpt);
    }
    
    public synchronized void updateOptionChoice(String optionSetsName, String optionChoice) {
    	findOptionSet(optionSetsName).setOptionChoice(optionChoice);
    }
    
    public synchronized void updateOptionName(String optionSetsName, String optName, String newName) { 
    	findOptionSet(optionSetsName).findOption(optName).setName(newName);
    }
    
    public synchronized void updateOptionPrice(String optionSetsName, String optName, double newPrice) {
    	findOptionSet(optionSetsName).updateOptionPrice(optName, newPrice);
    }  
    
	
	public synchronized int findOption(String name, int x) {
		ArrayList<Option> temp = getOptionSet(x).getOptions();
		for(int i = 0; i<getOptionSetLength(x);i++) {
			if(temp.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}
	
	public synchronized int findOptionSetIndex(String optionSetName) {
		if (optionSets == null)
			return -1;
		
		for (int i = 0; i < optionSets.size(); i++) {
			if (optionSets.get(i) != null && 
					optionSets.get(i).getName().equals(optionSetName))
				return i;
		}
	
		return -1;
	}
	
	//choice methods
	
	public synchronized void setOptionChoice(String setName, String optionName) {
		findOptionSet(setName).setOptionChoice(optionName);;
	}
	
	public synchronized String getOptionChoice(String setName) {
		return findOptionSet(setName).getOptionChoiceName();
	}
	
	public synchronized double getOptionPrice(String setName) {
		return findOptionSet(setName).getOptionChoicePrice();
	}
	
	//auto methods
	public synchronized String getMake() {
		return make;
	}

	public synchronized void setMake(String make) {
		this.make = make;
	}

	public synchronized String getModel() {
		return model;
	}

	public synchronized void setModel(String model) {
		this.model = model;
	}

	public synchronized double getBasePrice() {
		return basePrice;
	}

	public synchronized void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}

	public synchronized ArrayList<OptionSet> getOptionSets() {
		return optionSets;
	}

	public synchronized void setOptionSets(ArrayList<OptionSet> optionSets) {
		this.optionSets = optionSets;
	}

	
	@Override
	public synchronized String toString() {
		return "Automobile [name=" + name + ", make=" + make + ", model=" + model + ", basePrice=" + basePrice
				+ ", optionSets=" + optionSets + "]";
	}
	
    public synchronized boolean areOptionsNull(int i) {
    	return (optionSets.get(i).getOptions()) == null;
    }
    
    public synchronized double getTotalPrice() { // <----------------
    	double totalPrice = basePrice;
    	for (OptionSet os : optionSets) {
    			// Add every choice's price to the base Price
    			if (os.getChoice() != null)
    				totalPrice += os.getOptionChoicePrice();
    	}
    	return totalPrice;
    }
    
	public synchronized void printChoices() {
		System.out.printf("Your Option Choices for %s:\n", name);
		for (OptionSet os : optionSets) {
			if (os.getChoice() != null) {
				System.out.println(os.getName() + ":" + 
						os.getChoice().getName() + "," + os.getChoice().getPrice());
			}
		}
		double totalPrice = getTotalPrice();
		System.out.printf("Total cost - $%.2f", totalPrice);
	}

    public synchronized void print() {
        System.out.printf("\n~~~~~~~~~~~\n%s\nBase price is $%.2f\n", name, basePrice);
        printChoices();
        //if (optionSets != null) {
    	//	for(int i = 0; i < optionSets.size(); i++) {
          //  	if (optionSets.get(i) != null) optionSets.get(i).print();
          //  }	
    	//}
        /*
		for (OptionSet os : optionSets) {
			//System.out.println(os.getName() + ":" );
			//for (Option option : os.getOptions()) {
				//System.out.println(option.getName() + ", " + option.getPrice());
			//}
			if (os.getChoice() != null) {
				System.out.println(os.getName() + ":" + 
						os.getChoice().getName() + "," + os.getChoice().getPrice());
			}
		}
        System.out.printf("\t\t~~Total price: $%.2f", getTotalPrice());
        */
    }
    
	public synchronized void printDetail() {
		try {
			System.out.printf("\n~~~~~~~~~~~\n%s\nBase price is $%.2f\n", name, basePrice);
			for (OptionSet os : optionSets) {
				System.out.println("Option:" + os.getName());
				for (Option option : os.getOptions()) {
					System.out.println(option.getName() + ", " + option.getPrice());
				}
				if (os.getChoice() != null)
					System.out.println(os.getChoice().getName());
			}
		} catch (Exception e) {
			System.out.println("Error " + e.toString());
		}
	}
	
}
