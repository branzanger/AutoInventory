package scale;

import adapter.ProxyAutomotive;
import exception.AutoException;

public class EditOptions extends ProxyAutomotive implements Runnable {
	public static final int UPDATE_OPTIONSET_NAME = 1;
	public static final int UPDATE_OPTION_NAME = 2;
	public static final int UPDATE_OPTION_PRICE = 3;
	public static final int UPDATE_OPTION_CHOICE = 4;
	
	
	private boolean available = true;
	private int editType;
	private String carName;
	private String optionSetName;
	private String newOptionSetName;
	private String optionName;
	private String newOptionName;
	private double newOptionPrice;
	
	public EditOptions(int editType, String carName, String optionSetName, String newName) {
		this.carName = carName;
		this.editType = editType;
		this.optionSetName = optionSetName;
		switch (editType) {
		case UPDATE_OPTIONSET_NAME:
			this.newOptionSetName = newName;
			break;
		case UPDATE_OPTION_CHOICE:
			this.newOptionName = newName;
			break;
		}
	}
	
	public EditOptions(String carName, String optionSetName,String optionName, String newOptionName) {
		this.carName = carName;
		this.optionSetName = optionSetName;
		this.optionName=optionName;
		this.newOptionName = newOptionName;
		this.editType= UPDATE_OPTION_NAME;
	}
	
	public EditOptions(String carName, String optionSetName,String optionName, double newOptionPrice) {
		this.carName = carName;
		this.optionSetName = optionSetName;
		this.optionName=optionName;
		this.newOptionPrice = newOptionPrice;
		this.editType=UPDATE_OPTION_PRICE;
	}
	
	public void updateOptionSetName(String carName, String optionSetName, String newOptionSetName)
			throws AutoException {

		while (available == false) {
			try {
				// wait for Producer to put value
				wait();
			} catch (InterruptedException e) {
			}
		}
		available = false;
		// notify Producer that value has been retrieved
		super.updateOptionSetName(carName, optionSetName, newOptionSetName);
		notifyAll();
		return;
	}

	public void updateOptionName(String carName, String optionSetName, String optionName, String newOptionName)
			throws AutoException {

		while (available == false) {
			try {
				// wait for Producer to put value
				wait();
			} catch (InterruptedException e) {
			}
		}
		available = false;
		// notify Producer that value has been retrieved
		super.updateOptionName(carName, optionSetName, optionName, newOptionName);
		available = true;
		//notifyAll();
		return;
	}
	
	public void updateOptionChoice(String carName, String optionSetName, String optionChoice)
			throws AutoException {

		while (available == false) {
			try {
				// wait for Producer to put value
				wait();
			} catch (InterruptedException e) {
			}
		}
		available = false;
		// notify Producer that value has been retrieved
		super.updateOptionChoice(carName, optionSetName, optionChoice);
		available = true;
		//notifyAll();
		return;
	}

	public void updateOptionPrice(String carName, String optionSetName, String optionName, double newPrice)
			throws AutoException {

		while (available == false) {
			try {
				// wait for Producer to put value
				wait();
			} catch (InterruptedException e) {
			}
		}
		available = false;
		// notify Producer that value has been retrieved
		super.updateOptionPrice(carName, optionSetName, optionName, newPrice);
		available = true;
		//notifyAll();
		return;
		
	}

	public void run() {
		System.out.println("\nStarting Thread " + Long.toString(Thread.currentThread().getId()));
		try {
		switch (editType) {
		case UPDATE_OPTIONSET_NAME :
				updateOptionSetName(carName,optionSetName,newOptionSetName);
				break;
		case UPDATE_OPTION_PRICE :
				updateOptionPrice(carName,optionSetName,optionName,newOptionPrice);
				break;
		case UPDATE_OPTION_NAME :
				updateOptionName(carName,optionSetName,optionName,newOptionName);
				break;
		case UPDATE_OPTION_CHOICE :
			updateOptionChoice(carName,optionSetName,newOptionName);
			break;
		default :
				throw new AutoException(71,"invalid editType chosen");
			}
		}
		catch (AutoException e) {
			e.fix();
		}
		System.out.println("Ending Thread " + Long.toString(Thread.currentThread().getId()));
		//t.interrupt();
		return;
	}

	/*
	public void start() {
		start();
	}*/
}
