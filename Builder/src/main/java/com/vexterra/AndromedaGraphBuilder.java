package com.vexterra;

// still needs to include generated classes Collection, Source, Account, etc...
// only benefit is to hide Map<QID,Collection> etc. when building edges.
// should also create individual csv files: belongs.csv, has.csv, and connectedto.csv

// uhoh, big problem: what about nodes without any edges? this only import nodes with connections.
// if need to import them, then also need 4 more csv for nodes....
// anyways, this design should work cleanly.
public class AndromedaGraphBuilder extends GraphBuilder {
	AndromedaGraph graph = new AndromedaGraph();
	// specifics to Andromeda graph
	//List<Collection> collectionNodes;
	//List<Source> sourceNodes;
	//List<Account> accountNodes;
	//List<Usb> usbNodes;
	//List<String> belongsEdges; //<String> = <from,to>
	//List<String> hasEdges;
	//List<String> connectedToEdges;
	// ? perhaps another builder of different edge type?

	@Override
	public void buildNodes() {
		// buildCollectionNode()
		// buildSourceNode()
		// buildAccountNode()
		// buildUsbNode()
	}

	@Override
	public void buildEdges() {
		// buildBelongsEdge() // graph.setBelongs(collection-source)
		// buildHasEdge()	// graph.setHas(source-account)
		// buildConnectedToEdge()	// graph.setConnectedTo(usb-source)
	}

	@Override
	public Graph getGraph() {
		return graph;
	}
	
	/*
	 * input = Collection and Source node
	 * output = List<String> in graph object, complete with header, ready to write to csv
	 */
	protected void buildBelongsEdge() {
		
	}

}
