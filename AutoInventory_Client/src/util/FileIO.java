//Brandon Zhang
package util;

import java.io.*;
import java.util.Base64;

import exception.AutoException;
import model.Automobile;

public class FileIO {

	/*
	public Automobile buildAuto(String fileName) throws AutoException {
		Automobile car = null;
		FileReader file = null;
		try {
			file = new FileReader(fileName);
		} catch (Exception ex) {
			throw new AutoException(AutoException.FileNotFound);
		}
		
		try {
			BufferedReader buff = new BufferedReader(file);
			boolean eof = false;
			car = new Automobile(
					buff.readLine(), 
					buff.readLine(), 
					buff.readLine(), 
					Double.parseDouble(buff.readLine()),
					null,
					null);
			int numOfOptionSets = Integer.parseInt(buff.readLine());
			for (int x = 0; x < numOfOptionSets; x++) {
				car.addOptionSet(buff.readLine());
				int numOfOptions = Integer.parseInt(buff.readLine());
				for (int i = 0; i < numOfOptions; i++) {
					String str = buff.readLine();
					String[] str2 = str.split(",");
					car.addOption(str2[0].trim(), Float.parseFloat(str2[1].trim()), x);
					//car.setOption(str2[0].trim(), Float.parseFloat(str2[1].trim()), x, i);
				}
			}
			buff.close();
		} catch (IOException e) {
			System.out.println("Error " + e.toString());
			
		}
		return car; 
	}*/
	
	public Automobile buildAuto(String fileName) throws AutoException {
		try {
			FileReader file = null;
			BufferedReader buff = null;
			double basePrice = 0.0;
			double price = 0.0;
			String name,make,model = "";
			String optionName = "";
			//FileWriter writer = new FileWriter("exception-log.txt");
			//PrintWriter w = new PrintWriter(writer);
			
			try {
				try {
					file = new FileReader(fileName);
					buff = new BufferedReader(file);
				} catch(IOException event) {
					throw new AutoException(11);
				}
			} catch (AutoException e) {
				e.log();
				return buildAuto(e.fix());
			}
			
			try {
				name = buff.readLine();
				String[] splits = name.split(" ");
				if (splits.length != 2)
					throw new AutoException(61);
				else {
					make = splits[0];
					model = splits[1];
				}
			} catch (AutoException e) {
				e.log();
				return buildAuto(e.fix());
			}
		
			
			try {
				String temp = buff.readLine();
					if (temp.equals("")) {
						throw new AutoException(21);
					} else {
						basePrice = Double.parseDouble(temp);	
					}
			} catch (AutoException e) {
				e.log();
				return buildAuto(e.fix());
			}
			
			Automobile myCar = new Automobile(name, make, model, basePrice);
			boolean newOption = true;
			String optionSetName = "";
			String temp = "";
			boolean eof = false;
			//buff.readLine(); // Skip a line
			while((temp=buff.readLine())!=null && temp.length()!=0) {
				try {
					if (temp.equals("---------------")) {
						newOption = true;
						continue;
					}
					if (newOption) {	
						if (temp.equals("")) 
							throw new AutoException(41);
						
						optionSetName = temp;
						myCar.addOptionSet(optionSetName);
						newOption = false;
						continue;
					} 
					
					optionName = temp;
					price = Double.parseDouble(buff.readLine());
					myCar.addOption(optionSetName, optionName, price);

				} catch (AutoException e) {
					e.log();
					return buildAuto(e.fix());
				}
			}
			buff.close();
			file.close();
			return myCar;
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}

	public void print(Automobile car, String fileName) {
		try {
			FileWriter file = new FileWriter(fileName);
			BufferedWriter buff = new BufferedWriter(file);
			buff.write(car.getMake() + "\n");
			buff.write(car.getModel() + "\n");
			buff.write(car.getBasePrice() + "\n");
			buff.write(car.getOptionSetsLength() + "\n");
			for (int i = 0; i < car.getOptionSetsLength(); i++) {
				buff.write(car.getOptionSetName(i) + "\n");
				buff.write(car.getOptionSetLength(i) + "\n");
				for (int x = 0; x < car.getOptionSetLength(i); x++) {
					buff.write(car.getOptionName(x, i) + ", " + car.getOptionPrice(x, i) + "\n");
				}
			}
			buff.close();
		} catch (IOException e) {
			System.out.println("Error " + e.toString());
		}
	}
	
	public String serializeToString(Object obj) {
		ByteArrayOutputStream baos = null;
        ObjectOutputStream oos = null;
        String response = null;
		try {
			baos = new ByteArrayOutputStream();
	        oos = new ObjectOutputStream( baos );
	        oos.writeObject(obj);
	        oos.close();
	        response = Base64.getEncoder().encodeToString(baos.toByteArray()); 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return response;
	}
	
	public Object serializeFromString(String autoStr) {
		Object obj = null;
		ObjectInputStream ois = null;
		try {
			byte [] data = Base64.getDecoder().decode( autoStr);
			ois = new ObjectInputStream( 
					new ByteArrayInputStream(  data ) );
			obj  = ois.readObject();
		} catch (Exception e) {
				e.printStackTrace();
		} finally {
			try {
				ois.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return obj;
	}

	public Automobile deSerializeFromFile(String filename) {
		ObjectInputStream in = null;
		Automobile auto = null;
		try {
			in = new ObjectInputStream(new FileInputStream(filename));
			auto = (Automobile) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return auto;
	}

	public void serializeToFile(Automobile auto, String filename) {
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(filename));
			out.writeObject(auto);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
