package components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;


public class Parser
{
ArrayList<String> instructions;

public Parser()
{
    instructions = new ArrayList<String>();
    RegisterFile.initRegistersWithAddresses();
}

public void readFile() throws NumberFormatException, IOException
{
	@SuppressWarnings("resource")
	BufferedReader bufferRead = new BufferedReader(new FileReader("input.txt"));
	String tmp =  "";
	ArrayList<String> labels = new ArrayList<String>();
	
    while((tmp = bufferRead.readLine()) != null) 
    {
     ArrayList<String> registers = new ArrayList<String>();
     String instruction = "";
     if (tmp.contains(":"))
     {
    	 String tmp2 = (tmp.split(":"))[0];
    	 labels.add(tmp2);
    	 
    	 tmp = tmp.substring(tmp2.length() + 1);
     }
     
     
     int c = 0;
     for (c = 0; tmp.charAt(c) != ' '; c++)
    {
		instruction += tmp.charAt(c);	
	}
   
	 tmp = tmp.substring(c);
	 tmp = tmp.replaceAll("\\s","");
	 registers = new ArrayList<String>(Arrays.asList(tmp.split(",")));
	    
    if (instruction.equals("add") || instruction.equals("addi") || instruction.equals("sub") ||
    	instruction.equals("slt") || instruction.equals("sltu") || instruction.equals("and") ||
    	instruction.equals("nor"))
	{  
   
		if (rvalidator(instruction, registers))
		{
			
			instructions.add(tmp);
		}
		
	}
    else if (instruction.equals("lw") || instruction.equals("lb") || instruction.equals("lbu") ||
    		instruction.equals("sw")  || instruction.equals("sb") || instruction.equals("lui"))
    {
    	try
    	{
    	 		int offset = Integer.parseInt(registers.get(2).charAt(0) + "");
    	 		String dr = registers.get(2).substring(0, registers.get(2).length());
    	 		registers.set(2, dr);
    	 		if (jvalidator(instruction, registers, offset))
    			{
    				instructions.add(tmp);
    			}
    	 		
    	}
    	catch( Exception e)
    	{
    		 System.out.println("ERROR!");
    	}
    			
    }
    else if(instruction.equals("beq") || instruction.equals("bne"))
    {
 String label =  registers.get(2);
 registers.remove(2);
    	if (labels.contains(label) && jvalidator(instruction, registers, labels.get(labels.size() - 1)))
    	{
    		instructions.add(tmp);
    	}
    }

    }

}

public boolean jvalidator(String instruction, ArrayList<String> registers, String label)
{
	
	
return true;
}

public boolean jvalidator(String instruction, ArrayList<String> registers, int offset)
{
	
	if(registers.size() < 2)
	{
		return false;
	}
	for (int i = 0; i < registers.size(); i++) 
	{
		if (registers.get(i).charAt(0) != '$')
		{
			return false;
		}
		else
		{
			registers.set(i,registers.get(i).substring(0));
		}
	}
	
	for (int i = 0; i < registers.size(); i++)
	{
		if (!RegisterFile.registersAddress.contains(registers.get(i)))
		{
         return false;
		}
	}
	
	return true;
}

public boolean rvalidator(String instruction, ArrayList<String> registers)
{ 
	if(registers.size() != 3)
	{
	 	
		return false;
	}
	
	for (int i = 0; i < registers.size(); i++) 
	{
		if (registers.get(i).charAt(0) != '$')
		{
			
			return false;
		}
		else
		{
			registers.set(i,registers.get(i).substring(1));
		}
	}
	
	for (int i = 0; i < registers.size(); i++)
	{
		System.out.println(registers.get(i));
		if (!RegisterFile.registersAddress.containsKey(registers.get(i)))
		{
		System.out.println("lel");
         return false;
		}
	}
    return true;
}

public static void main(String[] args) throws NumberFormatException, IOException
{
	Parser x = new Parser();
	x.readFile();
}
}
