package components;

import java.util.ArrayList;

public class Memory {
	public static ArrayList<String> mem; // dynamic memory with bounds [0:1000000000]
										// stack starts at 999999999
	public static void writeToMemory(int index,String content) {
		System.out.println(index);
		String byte1 = content.substring(0, 8);
		String byte2 = content.substring(8, 16);
		String byte3 = content.substring(16, 24);
		String byte4 = content.substring(24, 32);
		mem.add(index, byte1);
		mem.add(index+1, byte2);
		mem.add(index+2, byte3);
		mem.add(index+3, byte4);
	}
	public static String readFromMemory(int index) {
		String word = mem.get(index)+mem.get(index+1)+mem.get(index+2)+mem.get(index+3);
		return word;
	}
	public static void init() {
		mem =  new ArrayList<String>();
	}
}
