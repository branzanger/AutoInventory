package server;
import model.*;

import java.util.Properties;

import exception.*;

public interface AutoServer {
	public String addAuto(Properties prop);
	public String configAuto(Properties prop);
	public String listAutos();
	public Automobile getAuto(String carName);
}