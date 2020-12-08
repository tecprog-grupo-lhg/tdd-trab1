package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Table {
	private Parser parser;
	
	public Table(Parser parser) {
		this.parser = parser;
	}
	
	public ArrayList<ArrayList<String>> makeTable() {
		ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>();
		int evolution = 0;
		try {
			Scanner sc = new Scanner(this.parser.fileManipulation.input.getFile());
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
		
		return table;
	}
}
