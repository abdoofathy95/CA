package components;

import java.util.ArrayList;

public class instruction {
	 String label;
	 String name;
	 int offset;
	 ArrayList<String> parameters;
	
	public instruction(String label, String name, ArrayList<String> parameters, int offset)
	{
		this.label = label;
		this.name = name;
		this.parameters = parameters;
		this.offset = offset;
	}
	

}