package main;

public class test2 {

	public static String a;
	public static String b;
	
	public test2 (){
		a = test1.a;
		b = test1.b;
	}
	
	public static void main (String [] args){
		test1.init();
		System.out.println(a);
		System.out.println(b);
	}
}
