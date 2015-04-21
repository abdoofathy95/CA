package components;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser
{
ArrayList<instruction> instructions;
ArrayList<String> labels;
int lineCounter;

public Parser()
{
    instructions = new ArrayList<instruction>();
    labels = new ArrayList<String>();
    RegisterFile.initRegistersWithAddresses();
}

public void readLabels() throws IOException
{
	@SuppressWarnings("resource")
	BufferedReader bufferRead = new BufferedReader(new FileReader("input.txt"));
	String line =  "";	
	 while((line = bufferRead.readLine()) != null) 
	    {
	
			 String label = ""; 
			 line = line.toLowerCase();
			   
		     //getting label and deleting it from String
		     if (line.contains(":"))
		     {
		    	 label = (line.split(":"))[0];
		    	 label = label.replaceAll("\\s", "");
		    	 labels.add(label);	
		     }
	     
	     }
	    
	    
}

public void readFile() throws NumberFormatException, IOException
{
	@SuppressWarnings("resource")
	BufferedReader bufferRead = new BufferedReader(new FileReader("input.txt"));
	String line =  "";
	lineCounter = 0;
	readLabels();
	   String label = "";
	
 while((line = bufferRead.readLine()) != null) 
    {
     lineCounter++;
     line = line.toLowerCase();
     
     String instruction = "";
  
     int offset = 0;
	
     
  	if (line.contains("#"))
 	{
 		int index = 0;
 		while(line.charAt(index) != '#')
 		{
 		 index++;
 		}
 	line = line.substring(0,index);
 	}
  	
  	while (line.length() > 0 && line.charAt(0) == ' ')
    {
    	line = line.substring(1);
    }
  	line = line.replaceAll("\t", "");
     if (line.length() > 0)
     {
     ArrayList<String> parameters = new ArrayList<String>();
   
     instruction toBeAdded = new instruction(label, instruction, parameters, offset);
     line = line.toLowerCase();
   
     
     //getting label and deleting it from String
     if (line.contains(":"))
     {
    	 label = (line.split(":"))[0];
    	 label = label.replaceAll("\\s", "");	 
    	 toBeAdded.label = label;
    	 line = line.substring(label.length() + 1);
     }
     String tmp2 = line.replaceAll("\\s", "");
     if (tmp2.length() != 0)
     {
      //getting instruction
     
 
    while (line.length() > 0 && line.charAt(0) == ' ')
    {
    	line = line.substring(1);
    }
    
    while ((line.length() != 0 && line.charAt(0) != ' '))
	 {
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
	 
	 
	 if(parameters.get(0).equals("$zero") && !instruction.equals("sw"))
     {
    	 System.out.println(lineCounter +":" + " Unvalid input register $zero cannot be overwritten");
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
		else
		{
			System.out.println(lineCounter + ": Unexpected input");
		}
		
	}
    
    
    //DONE
    else if (instruction.equals("lw") || instruction.equals("lb") || instruction.equals("lbu") ||
    		 instruction.equals("sw") || instruction.equals("sb"))
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
    	 		String dr = parameters.get(1).substring(i + 1, parameters.get(1).length() - 1);
    	 		parameters.set(1, dr);
    	 	   
    	 		if (jValidator(instruction, parameters, offset))
    			{
    	 			toBeAdded.offset = offset;
    	 			toBeAdded.parameters = parameters;
    				instructions.add(toBeAdded);
    			}
    	 		
    	 		else
    			{
    				System.out.println(lineCounter + ": Unexpected input");
    			}
    	 		
    	}
    	
    	catch(java.lang.NumberFormatException e)
    	{
    		 System.out.println(lineCounter + ": Offset must be a number");
    	}
    			
    }
    
    else if  (instruction.equals("lui"))
    {
    	if(luiValidator(parameters))
    	{
    		toBeAdded.parameters = parameters;
    		instructions.add(toBeAdded);
    	}
    	else
    	{
    		System.out.println(lineCounter + ": Unexpected input");
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
    	
    	else
		{
			System.out.println(lineCounter + ": Unexpected input");
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
    	
    	else
		{
			System.out.println(lineCounter + ": Unexpected input");
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
    	
    	else
		{
    		if (!labels.contains(parameters.get(0)))
    		{
    			System.out.println(lineCounter + ": The label referred to does not exist");
    		}
    		
			System.out.println(lineCounter + ": Unexpected input");
		}
    	
    }
    
    else if (instruction.equalsIgnoreCase("jr"))
    {
    	if (jrValidator(parameters))
    	{
    		toBeAdded.parameters = parameters;
    		instructions.add(toBeAdded);
    	}
    	
    	else
		{
			System.out.println(lineCounter + ": Unexpected input");
		}
    	
    }
    else
    {
    	System.out.println(lineCounter + ": Unexpected input");
    }

    }
    }
    }

}

public boolean luiValidator(ArrayList<String> parameters)
{
	if (parameters.size() > 2)
	{
		return false;
	}
	if (parameters.get(0).charAt(0) != '$')
	{
		return false;
	}
	else 
	{
		try 
		{
			Integer.parseInt(parameters.get(1));
		}
		catch (java.lang.NumberFormatException e)
		{
			System.out.println(lineCounter + ": constant must be an integer");
			return false;
		}
	}
	return true;
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
		 System.out.println(lineCounter +": Unvalid register name");
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
			 System.out.println(lineCounter +": Unvalid register name");
	         return false;
			}
		}
		
	}
	try
	{
		@SuppressWarnings("unused")
		
		int temp = Integer.parseInt(parameters.get(2));
	}
	catch (Exception e)
	{
		System.out.println(lineCounter + ": input must be a constant");
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
			 System.out.println(lineCounter +": Unvalid register name");
	         return false;
			}
		}
	}
	if (parameters.get(2).charAt(0) == '$'|| !labels.contains(parameters.get(2)))
	{
		if(!labels.contains(parameters.get(2)))
		{
			System.out.println(lineCounter + ": The label referred to does not exist");
	    }
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
			 System.out.println(lineCounter +": Unvalid register name");
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
			
			System.out.println(parameters.get(i));
			return false;
		}
		else
		{
			parameters.set(i,parameters.get(i).substring(1));
			if (!RegisterFile.registersAddress.containsKey(parameters.get(i)))
			{
			System.out.println(lineCounter +": Unvalid register name");
	         return false;
			}
			
		}
	}
	
	return true;
}


public static void main(String[] args) throws NumberFormatException, IOException
{
	Parser n = new Parser();
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
		if (n.instructions.get(i).label != "")
		System.out.print(n.instructions.get(i).label + ":");
		
		System.out.print(n.instructions.get(i).name + " ");
		
		for (int j = 0; j < n.instructions.get(i).parameters.size(); j++)
		{
			System.out.print(n.instructions.get(i).parameters.get(j) + " ");
		}
		System.out.println();
	}
}
}
