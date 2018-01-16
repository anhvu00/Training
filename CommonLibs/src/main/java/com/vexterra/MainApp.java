package com.vexterra;

public class MainApp {

	public static void main(String[] args) {
		// Just call a static method in this project
		int retval = MyJDBC.connect("jdbc:something:something");
		System.out.println("return value is " + retval);

	}

}
