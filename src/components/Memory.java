package components;


public class Memory {
	public static String [] mem; // dynamic memory with bounds [0:1000000000]
	public static String [] stack;		// stack starts at 999999999
	public static void writeToMemory(int index,String content) {
		String byte1 = content.substring(0, 8);
		String byte2 = content.substring(8, 16);
		String byte3 = content.substring(16, 24);
		String byte4 = content.substring(24, 32);
		
		mem[index] = byte1;
		mem[index+1]= byte2;
		mem[index+2]= byte3;
		mem[index+3]= byte4;
	}
	public static String readFromMemory(int index) {
		String word = mem[index]+mem[index+1]+mem[index+2]+mem[index+3];
		return word;
	}
	public static void init() {
		mem = new String [50000]; // set size to 50000
		stack = new String [20000]; // set size to 20000
	}
}