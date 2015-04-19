package components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import jdk.nashorn.internal.ir.Labels;


public class Parser
{
ArrayList<instruction> instructions;
ArrayList<String> labels;


public Parser()
{
    instructions = new ArrayList<instruction>();
    labels = new ArrayList<String>();
    RegisterFile.initRegistersWithAddresses();
}

public void readFile() throws NumberFormatException, IOException
{
	@SuppressWarnings("resource")
	BufferedReader bufferRead = new BufferedReader(new FileReader("input.txt"));
	String line =  "";
	int lineCounter = 0;
    while((line = bufferRead.readLine()) != null) 
    {
     lineCounter++;
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
     boolean flag = false;
    
    while ((line.length() != 0 && line.charAt(0) != ' ') || !flag)
	    {
    	if (line.charAt(0) != ' ')
    	{
    		flag = true;
    	}
			instruction += line.charAt(0);
			line = line.substring(1);
		}
    
   
    instruction = instruction.replaceAll("\\s", "");
    toBeAdded.name = instruction;
   //getting list of parameters
	 line = line.replaceAll("\\s","");
	 parameters = new ArrayList<String>(Arrays.asList(line.split(",")));
	 
	 for (int i = 0; i < parameters.size(); i++)
	 {
		 parameters.set(i, parameters.get(i).replaceAll("\\s", ""));
	 }
	 if(parameters.get(0).equals("$zero"))
     {
    	 System.out.println("Unvalid instruction in line" +lineCounter+ " register $zero cannot be overwritten");
    	 return;
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
    //DONE
    else if (instruction.equals("lw") || instruction.equals("lb") || instruction.equals("lbu") ||
    		 instruction.equals("sw") || instruction.equals("sb") || instruction.equals("lui"))
    {
    	try
    	{
    			int i = 0;
    			String tmp = "";
    			while(parameters.get(1).charAt(i) != '(')
    			{
    				tmp +=parameters.get(1).charAt(i);
    				i++;
    			}
    	 	    offset = Integer.parseInt(tmp);
    	 		String dr = parameters.get(1).substring(2, parameters.get(1).length() - 1);
    	 		parameters.set(1, dr);
    	 	   
    	 		if (jValidator(instruction, parameters, offset))
    			{
    	 			toBeAdded.offset = offset;
    	 			toBeAdded.parameters = parameters;
    				instructions.add(toBeAdded);
    			}
    	 		
    	}
    	catch(java.lang.NumberFormatException e)
    	{
    		 System.out.println("Offset must be a number");
    	}
    			
    }
    //DONE
    else if(instruction.equals("beq") || instruction.equals("bne"))
    {
    	if (branchValidator(parameters))
    	{
    		toBeAdded.parameters = parameters;
    		instructions.add(toBeAdded);
    	}
    }
    //DONE
    else if(instruction.equals("addi") || instruction.equals("sll") || instruction.equals("srl"))
    {
    	if(immediateValidator(parameters))
    	{
    		toBeAdded.parameters = parameters;
    		instructions.add(toBeAdded);
    	}
    }
    //DONE
    else if(instruction.equals("j") || instruction.equals("jal"))
    {
    	if (!parameters.get(0).contains("$") && labels.contains(parameters.get(0)) && parameters.size() == 1)
    	{
    		toBeAdded.parameters = parameters;
    		instructions.add(toBeAdded);
    	}
    }
    
    else if (instruction.equalsIgnoreCase("jr"))
    {
    	if (jrValidator(parameters))
    	{
    		toBeAdded.parameters = parameters;
    		instructions.add(toBeAdded);
    	}
    }

    }

}
//DONE
public boolean jrValidator(ArrayList<String> parameters)
{
	if (parameters.size() > 1)
	{
		return false;
	}
	
	if (parameters.get(0).charAt(0) != '$')
	{
		return false;
	}
	else
	{
		parameters.set(0,parameters.get(0).substring(1));
		if (!RegisterFile.registersAddress.containsKey(parameters.get(0)))
		{
         return false;
		}
	}
		
	return true;
}

//DONE
public boolean immediateValidator(ArrayList<String> parameters)
{
	if (parameters.size() < 3)
	{
		return false;
	}
	for (int i = 0; i < parameters.size() - 1; i++) 
	{
		if (parameters.get(i).charAt(0) != '$')
		{
			return false;	
		}
		else
		{
			parameters.set(i,parameters.get(i).substring(1));
			if (!RegisterFile.registersAddress.containsKey(parameters.get(i)))
			{
	         return false;
			}
		}
	}
	try
	{
		System.out.println(parameters.get(2));
		@SuppressWarnings("unused")
		
		int temp = Integer.parseInt(parameters.get(2));
		System.out.println(temp);
	}
	catch (Exception e)
	{
		System.out.println("CONSTANT ERROR");
		return false;
	}
	
	return true;
}
//DONE
public boolean branchValidator (ArrayList<String> parameters)
{
	if (parameters.size() < 3)
	{
	return false;	
	}
	
	for (int i = 0; i < parameters.size() - 1; i++) 
	{
		if (parameters.get(i).charAt(0) != '$')
		{
			return false;	
		}
		else
		{
			parameters.set(i,parameters.get(i).substring(1));
			if (!RegisterFile.registersAddress.containsKey(parameters.get(i)))
			{
	         return false;
			}
		}
	}
	if (parameters.get(2).charAt(0) == '$'|| !labels.contains(parameters.get(2)))
	{
		return false;
	}
	
	return true;
}

//DONE
public boolean rValidator(String instruction, ArrayList<String> parameters)
{ 
	if (parameters.size() < 3)
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
			parameters.set(i,parameters.get(i).substring(1));
			if (!RegisterFile.registersAddress.containsKey(parameters.get(i)))
			{
	         return false;
			}
		}
	}
    return true;
}
//DONE
public boolean jValidator(String instruction, ArrayList<String> parameters, int offset)
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
			parameters.set(i,parameters.get(i).substring(1));
			if (!RegisterFile.registersAddress.containsKey(parameters.get(i)))
			{
	         return false;
			}
		}
	}
	
	return true;
}


public static void main(String[] args) throws NumberFormatException, IOException
{
	Parser n = new Parser();
	String ins = "jr";
	ArrayList<String> x = new ArrayList<String>();
	n.labels.add("t2");
	x.add("s");
	//x.add("$t1");
	//x.add("5");
	//System.out.println(n.jrValidator(x));
	n.readFile();
	//System.out.println(n.instructions.size());
	for (int i = 0; i < n.instructions.size(); i++)
	{
		System.out.println(n.instructions.get(i).name);
	}
}
}
