package components;

import java.util.ArrayList;
import java.util.Arrays;

public class test {
public static void main(String[] args) {
	int r = Integer.parseInt("s");
	String x = "exit: addi $t1, $t2, 5($t3)";
	System.out.println((x.split(":"))[0]);
	 x = x.substring(((x.split(":"))[0]).length() + 2);
	 x = x.substring(0);
	 String instruction = "";
	
	   for (int i = 0; x.length() != 0 && x.charAt(0) != ' '; i++)
	    {
			instruction += x.charAt(0);
			x = x.substring(1);
		}
	   ArrayList<String> registers = new ArrayList<String>(Arrays.asList(x.split(",")));
		 
	for (int i = 0; i < registers.size(); i++) {
		System.out.print(registers.get(i) + " ");
	}
}
}
