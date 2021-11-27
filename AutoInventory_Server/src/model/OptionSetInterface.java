//Brandon Zhang
package model;

import java.io.Serializable;
import java.util.ArrayList;

public interface OptionSetInterface {
	
	public Option getOption(int i) throws ArrayIndexOutOfBoundsException;
	public void addOption(String name, double price);

}


