package app;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
	private File file;
	private ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public void parse() {
		table = new ArrayList<ArrayList<String>>(
			Arrays.asList(
				new ArrayList<String>(Arrays.asList("333", "807", "123")),
				new ArrayList<String>(Arrays.asList("333", "807")),
				new ArrayList<String>(Arrays.asList("333"))
			)
		);
	}
	
	public ArrayList<ArrayList<String>> getTable() {
		return this.table;
	}
}
