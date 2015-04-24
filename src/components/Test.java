package components;

public class Test {

	public static void main(String[] args) {
		String y = "00000101010111101011101";
		System.out.println("11111111111111111111111111111000".length());
		int x = Integer.parseUnsignedInt(y,2);
		System.out.println(Integer.parseUnsignedInt(y,2));
		System.out.println(x<<2);
	}

}
