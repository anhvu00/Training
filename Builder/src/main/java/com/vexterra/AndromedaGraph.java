package com.vexterra;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

// independent of the generated classes (Collection, Source, Account, etc.)
// only need output path from properties
public class AndromedaGraph extends Graph {
	List<String> belongs;
	List<String> has;
	List<String> connectedTo;
	// List<String> belongsEdges = graph.getBelongsEdges();
	// List<String> hasEdges = graph.getHasEdges();
	// List<String> connectedToEdges = graph.getConnectedToEdges();

	BufferedWriter bw = null;

	@Override
	public void createCsv() {
		writeBelongEdgeCsv();
		// writeHasEdgeCsv();
		// writeConnectedToEdgeCsv();
	}

	protected void writeBelongEdgeCsv() {
		// String header =
		// "collection_id,collection_qid,collection_name,source_id,source_qid,source_name";
		File outFile = new File("c:/temp/andromeda/out/belongs.csv");
		list2File(belongs, outFile);
	}
	
	public String getOutPath() {
		return "c:/temp/andromeda/out";
	}

	public void list2File(List<String> list, File outFile) {

		FileWriter fw;
		try {
			fw = new FileWriter(outFile);
			bw = new BufferedWriter(fw);
			for (String s : list) {
				bw.write(s);
				bw.newLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public List<String> getBelongs() {
		return belongs;
	}

	public void setBelongs(List<String> belongs) {
		this.belongs = belongs;
	}

	public List<String> getHas() {
		return has;
	}

	public void setHas(List<String> has) {
		this.has = has;
	}

	public List<String> getConnectedTo() {
		return connectedTo;
	}

	public void setConnectedTo(List<String> connectedTo) {
		this.connectedTo = connectedTo;
	}

}
