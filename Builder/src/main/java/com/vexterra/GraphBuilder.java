package com.vexterra;

// All graph builder must implement this base.
// common components of any graphs
public abstract class GraphBuilder {
	protected Graph graph;
	public abstract void buildNodes();
	public abstract void buildEdges();
	public abstract Graph getGraph();
}
