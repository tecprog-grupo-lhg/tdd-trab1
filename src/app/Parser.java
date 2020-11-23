package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Parser {
	private File file;
	private ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
	
	public void setFile(File file) {
		this.file = file;
	}
	
	public void parse() {
		int evolution = 0;
		try {
			Scanner sc = new Scanner(file);
			while (sc.hasNextLine()) {
		        final String linha = sc.nextLine();

		        if(linha.charAt(0) == '-') {
		        	table.add(new ArrayList<String>());
		        	evolution++;
		        }
		        else {
		        	table.get(evolution-1).add(linha);
		        }
		    }
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ArrayList<String>> getTable() {
		return this.table;
	}
}
