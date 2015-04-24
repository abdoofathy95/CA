package components;

import java.util.Hashtable;
import java.util.Iterator;


public class Test {

	public static void main(String [] args){ 
		Hashtable <String , String> hash = new Hashtable <String , String >();
		hash.put("first", "value");
		Iterator<String> x = hash.keySet().iterator();
		while(x.hasNext()){
			if (x.next().matches("first")) System.out.println(x);
		}
	}
}
