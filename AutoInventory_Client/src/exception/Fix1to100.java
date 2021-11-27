package exception;

import java.util.Scanner;

public class Fix1to100 {
	
	public String fix(int errorNo) {
		String s = null;
		Scanner ss = null;
		try {
			System.out.printf("Error(" + errorNo + " occured, please re-enter correct information below:\n");
			ss = new Scanner(System.in);
			s = ss.nextLine();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			ss.close();
		}
		return s;
	}
	

}
