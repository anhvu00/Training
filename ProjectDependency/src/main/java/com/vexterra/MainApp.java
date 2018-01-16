package com.vexterra;

import com.vexterra.MyJDBC;

public class MainApp {

	public static void main(String[] args) {
		// calling a common static function
		int retval = MyJDBC.connect("jdbc.somewhere.something");
		System.out.println("return value is "+retval);

	}

}
