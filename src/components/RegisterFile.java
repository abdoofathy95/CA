package components;

import java.util.Hashtable;
import java.util.Iterator;

public class RegisterFile {

	private static Hashtable<String, String> registersValue = new Hashtable<String, String>();
	static final Hashtable<String, String> registersAddress = new Hashtable<String, String>();

	public static String readRegisterWithAddress(String register) { // register is address in binary
		int address = Integer.parseInt(register,2);
		String hexAddress = "0x"+Integer.toHexString(address);
		String registerValue = "";
		Iterator <String> keys = registersAddress.keySet().iterator();
		while(keys.hasNext()) {
			String key = keys.next();
			if (registersAddress.get(key).matches(hexAddress)) {
				registerValue = key;
				break;
			}
		}
		return registersValue.get(registerValue);
	}

	public static String readRegisterWithItsName(String register) { // register is address in binary
		return registersValue.get(register);
	}
	
	public static void writeToRegister(String register, String value) { // register is address in binary
		int address = Integer.parseInt(register,2);
		String hexAddress = "0x"+Integer.toHexString(address);
		String registerValue = "";

		Iterator <String> keys = registersAddress.keySet().iterator();
		while(keys.hasNext()) {
			String key = keys.next();
			if (registersAddress.get(key).matches(hexAddress)) {
				registerValue = key;
				break;
			}
		}		
		registersValue.put(registerValue, value);
	}

	public static void initRegistersWithAddresses() {
		registersAddress.put("zero", "0x0");
		registersAddress.put("at", "0x1");
		registersAddress.put("v0", "0x2");
		registersAddress.put("v1", "0x3");
		registersAddress.put("a0", "0x4");
		registersAddress.put("a1", "0x5");
		registersAddress.put("a2", "0x6");
		registersAddress.put("a3", "0x7");
		registersAddress.put("t0", "0x8");
		registersAddress.put("t1", "0x9");
		registersAddress.put("t2", "0xa");
		registersAddress.put("t3", "0xb");
		registersAddress.put("t4", "0xc");
		registersAddress.put("t5", "0xd");
		registersAddress.put("t6", "0xe");
		registersAddress.put("t7", "0xf");
		registersAddress.put("s0", "0x10");
		registersAddress.put("s1", "0x11");
		registersAddress.put("s2", "0x12");
		registersAddress.put("s3", "0x13");
		registersAddress.put("s4", "0x14");
		registersAddress.put("s5", "0x15");
		registersAddress.put("s6", "0x16");
		registersAddress.put("s7", "0x17");
		registersAddress.put("t8", "0x18");
		registersAddress.put("t9", "0x19");
		registersAddress.put("k0", "0x1a");
		registersAddress.put("k1", "0x1b");
		registersAddress.put("gp", "0x1c");
		registersAddress.put("sp", "0x1d");
		registersAddress.put("s7", "0x1e");
		registersAddress.put("ra", "0x1f");
	}

	public static void initRegistersWithZeros() {
		registersValue.put("zero", "00000000000000000000000000000000");
		registersValue.put("at", "00000000000000000000000000000000");
		registersValue.put("v0", "00000000000000000000000000000000");
		registersValue.put("v1", "00000000000000000000000000000000");
		registersValue.put("a0", "00000000000000000000000000000000");
		registersValue.put("a1", "00000000000000000000000000000000");
		registersValue.put("a2", "00000000000000000000000000000000");
		registersValue.put("a3", "00000000000000000000000000000000");
		registersValue.put("t0", "00000000000000000000000000000000");
		registersValue.put("t1", "00000000000000000000000000000000");
		registersValue.put("t2", "00000000000000000000000000000000");
		registersValue.put("t3", "00000000000000000000000000000000");
		registersValue.put("t4", "00000000000000000000000000000000");
		registersValue.put("t5", "00000000000000000000000000000000");
		registersValue.put("t6", "00000000000000000000000000000000");
		registersValue.put("t7", "00000000000000000000000000000000");
		registersValue.put("s0", "00000000000000000000000000000000");
		registersValue.put("s1", "00000000000000000000000000000000");
		registersValue.put("s2", "00000000000000000000000000000000");
		registersValue.put("s3", "00000000000000000000000000000000");
		registersValue.put("s4", "00000000000000000000000000000000");
		registersValue.put("s5", "00000000000000000000000000000000");
		registersValue.put("s6", "00000000000000000000000000000000");
		registersValue.put("s7", "00000000000000000000000000000000");
		registersValue.put("t8", "00000000000000000000000000000000");
		registersValue.put("t9", "00000000000000000000000000000000");
		registersValue.put("k0", "00000000000000000000000000000000");
		registersValue.put("k1", "00000000000000000000000000000000");
		registersValue.put("gp", "00000000000000000000000000000000");
		registersValue.put("sp", "00000000000000000000000000000000");
		registersValue.put("s7", "00000000000000000000000000000000");
		registersValue.put("ra", "00000000000000000000000000000000");
	}
}