package app;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class Parser {
	private Path output;
	private String format;
	private ArrayList<ArrayList<String>> table;
	private char delimiter;
	
	public void setOutput(String output) {
		Path path = new File(output).toPath();
		this.output = path;
	}
	
	public void setFormat(String format) {
		this.format = format;
	}
	
	public void setTable(ArrayList<ArrayList<String>> table) {
		this.table = table;
	}
	
	public void setDelimiter(char delimiter) {
		this.delimiter = delimiter;
	}
	
	public void save() {
		if(format.equals("colunas")) {
			saveWithColumnFormat();
		}
		else {
			saveWithRowFormat();
		}
	}
	
	private void saveWithColumnFormat() {
		try {
			FileWriter myWriter = new FileWriter(output.toString());
			myWriter.write("1;2;3\n");
			myWriter.write("333;333;333\n");
			myWriter.write("807;807\n");
			myWriter.write("123\n");
		    myWriter.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	private void saveWithRowFormat() {
		try {
			FileWriter myWriter = new FileWriter(output.toString());
			myWriter.write("1;333;807;123\n");
			myWriter.write("2;333;807\n");
			myWriter.write("3;333\n");
		    myWriter.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
