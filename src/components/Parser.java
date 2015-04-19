package components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Parser
{
ArrayList<instruction> instructions;

public Parser()
{
    instructions = new ArrayList<instruction>();
    RegisterFile.initRegistersWithAddresses();
}

public void readFile() throws NumberFormatException, IOException
{
	@SuppressWarnings("resource")
	BufferedReader bufferRead = new BufferedReader(new FileReader("input.txt"));
	String line =  "";
	ArrayList<String> labels = new ArrayList<String>();
	
	
    while((line = bufferRead.readLine()) != null) 
    {
     ArrayList<String> parameters = new ArrayList<String>();
     String instruction = "";
     String label = "";
     int offset = 0;
     instruction toBeAdded = new instruction(label, instruction, parameters, offset);
     //getting label and deleting it from String
     if (line.contains(":"))
     {
    	 label = (line.split(":"))[0];
    	 label = label.replaceAll("\\s", "");
    	 labels.add(label);	 
    	 toBeAdded.label = label;
    	 line = line.substring(label.length() + 1);
     }
     
      //getting instruction
    while (line.length() != 0 && line.charAt(0) != ' ')
	    {
			instruction += line.charAt(0);
			line = line.substring(1);
		}
    
    toBeAdded.name = instruction;
   //getting list of parameters
	 line = line.replaceAll("\\s","");
	 parameters = new ArrayList<String>(Arrays.asList(line.split(",")));
	 
	 for (int i = 0; i < parameters.size(); i++)
	 {
		 parameters.set(i, parameters.get(i).replaceAll("\\s", ""));
	 }
	 
	 toBeAdded.parameters = parameters;
	 //DONE
    if (instruction.equals("add") || instruction.equals("sub") || instruction.equals("slt") ||
    	instruction.equals("sltu")|| instruction.equals("and") || instruction.equals("nor"))
	{  
   
		if (rValidator(instruction, parameters))
		{
			
			instructions.add(toBeAdded);
		}
		
	}
    
    else if (instruction.equals("lw") || instruction.equals("lb") || instruction.equals("lbu") ||
    		 instruction.equals("sw") || instruction.equals("sb") || instruction.equals("lui"))
    {
    	try
    	{
    	 	    offset = Integer.parseInt(parameters.get(2).charAt(0) + "");
    	 		String dr = parameters.get(2).substring(2, parameters.get(2).length());
    	 		parameters.set(2, dr);
    	 		
    	 		if (jvalidator(instruction, parameters, offset))
    			{
    				instructions.add(toBeAdded);
    			}
    	 		
    	}
    	catch(java.lang.NumberFormatException e)
    	{
    		 System.out.println("Offset error");
    	}
    			
    }
    else if(instruction.equals("beq") || instruction.equals("bne"))
    {
  label =  parameters.get(2);
 parameters.remove(2);
    	if (labels.contains(label) && jvalidator(instruction, parameters, labels.get(labels.size() - 1)))
    	{
    		instructions.add(toBeAdded);
    	}
    }

    }

}

//DONE
public boolean rValidator(String instruction, ArrayList<String> parameters)
{ 

	for (int i = 0; i < parameters.size(); i++) 
	{
		if (parameters.get(i).charAt(0) != '$')
		{
			return false;	
		}
		else
		{
			parameters.set(i,parameters.get(i).substring(1));
		}
	}
	
	if (parameters.size() < 3)
	{
		return false;
	}
	
	for (int i = 0; i < parameters.size(); i++)
	{
		if (!RegisterFile.registersAddress.containsKey(parameters.get(i)))
		{
         return false;
		}
	}
    return true;
}


public boolean jvalidator(String instruction, ArrayList<String> parameters, String label)
{
	
	
return true;
}

public boolean jvalidator(String instruction, ArrayList<String> parameters, int offset)
{
	
	if(parameters.size() < 2)
	{
		return false;
	}
	for (int i = 0; i < parameters.size(); i++) 
	{
		if (parameters.get(i).charAt(0) != '$')
		{
			return false;
		}
		else
		{
			parameters.set(i,parameters.get(i).substring(0));
		}
	}
	
	for (int i = 0; i < parameters.size(); i++)
	{
		if (!RegisterFile.registersAddress.contains(parameters.get(i)))
		{
         return false;
		}
	}
	
	return true;
}


public static void main(String[] args) throws NumberFormatException, IOException
{
	Parser n = new Parser();
	String ins = "add";
	ArrayList<String> x = new ArrayList<String>();
	x.add("$t0");
	x.add("$t1");
	x.add("$t2");
	System.out.println(n.rValidator(ins,x));
}
}
