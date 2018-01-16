package com.vexterra;

import java.util.ArrayList;

public class MainApp {

	public static void main(String[] args) {
		Director director = new Director();
		// we can create different graphs here...
		GraphBuilder graphBuilder = new AndromedaGraphBuilder();
		// single call to createGraph() with a specific builder
		AndromedaGraph agraph = (AndromedaGraph) director.createGraph(graphBuilder);
		// dummy data
		ArrayList<String> list = new ArrayList<String>();
		list.add("one,two,three");
		agraph.setBelongs(list);
		agraph.createCsv();
		System.out.println("DONE, check " + agraph.getOutPath());
	}

}
