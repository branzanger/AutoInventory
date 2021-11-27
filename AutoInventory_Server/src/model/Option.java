//Brandon Zhang
package model;

import java.io.Serializable;
import java.util.ArrayList;


public  class Option implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private double price;

	public  Option() {
		name = "";
		price = 0.0f;
	}

	public  Option(String name) {
		this.name = name;
		price = 0.0f;
	}

	public Option(String name, double price) {
		this.name = name;
		this.price = price;
	}

	public Option(float price, String name) {
		this.name = name;
		this.price = price;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}

	protected double getPrice() {
		return price;
	}

	protected void setPrice(double price) {
		this.price = price;
	}

	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("Option [name");
		str.append(name);
		str.append(", price=");
		str.append(price);
		str.append("]");
		return str.toString();
	}



}


