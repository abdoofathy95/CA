package components;

import java.util.ArrayList;
import java.util.Arrays;

public class test {
public static void main(String[] args) {
	String x = "exit: addi $t1, $t2, 5($t3)";
	System.out.println((x.split(":"))[0]);
	 x = x.substring(((x.split(":"))[0]).length() + 2);
	 x = x.substring(0);
	 String instruction = "";
	 String a = "-20";
	 int q = Integer.parseInt(a);
	 System.out.println(q);
	
	   for (int i = 0; x.length() != 0 && x.charAt(0) != ' '; i++)
	    {
			instruction += x.charAt(0);
			x = x.substring(1);
		}
	   
	   ArrayList<String> parameters = new ArrayList<String>(Arrays.asList(x.split(",")));
	   for (int i = 0; i < parameters.size(); i++)
		 {
			 parameters.set(i, parameters.get(i).replaceAll("\\s", ""));
		 }
		// System.out.println(parameters.get(2));
	int   offset = Integer.parseInt(parameters.get(2).charAt(0) + "");
		String dr = parameters.get(2).substring(2, parameters.get(2).length() -1);
		parameters.set(2, dr);
		//System.out.println(parameters.get(2));
}
}
