package com.vexterra;

public class Director {
	public Graph createGraph(GraphBuilder builder) {
		builder.buildNodes();
		builder.buildEdges();
		return builder.getGraph();
	}
} 
