package com.vexterra;

public class MyJDBC {

	// This is a common function that can be shared by other projects
	public static int connect(String url) {
		System.out.println("Connected to url: " + url);
		return 1;
	}
}
