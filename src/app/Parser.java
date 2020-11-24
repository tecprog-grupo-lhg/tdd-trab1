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
		final int evolutions = table.size();
		ArrayList<Integer> sizes = new ArrayList<Integer>();
		ArrayList<Boolean> finished = new ArrayList<Boolean>();
		
		try {
			FileWriter myWriter = new FileWriter(output.toString());
			for(int i = 1; i <= evolutions; i++) {
				myWriter.write(Integer.toString(i));
				sizes.add(table.get(i-1).size());
				finished.add(false);
				
				if(i != evolutions) {
					myWriter.write(delimiter);
				}
			}
			
			myWriter.write("\n");
			
			int remainingEvolutions = evolutions;
			int index = 0;
			int lastEvolutionNotFinished = evolutions;
			
			while(remainingEvolutions != 0) {
				for(int i = 1; i <= evolutions; i++) {
					if(finished.get(i-1)) {
						if(i < lastEvolutionNotFinished && i != 1) {
							myWriter.write(delimiter);
						}
						continue;
					}
					
					if(i != 1) {
						myWriter.write(delimiter);
					}
					
					if(index < sizes.get(i-1)) {
						myWriter.write(table.get(i-1).get(index));
					}
					
					if(index == sizes.get(i-1) - 1) {
						if(!finished.get(i-1)) {
							remainingEvolutions--;
							finished.set(i-1, true);
							for(int j = lastEvolutionNotFinished; j > 0; j--) {
								if(!finished.get(j-1)) {
									lastEvolutionNotFinished = j;
									break;
								}
							}
						}
					}
				}
				myWriter.write("\n");
				index++;
			}
		    myWriter.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	private void saveWithRowFormat() {
		final int numberOfEvolutions = table.size();
		
		try {
			FileWriter myWriter = new FileWriter(output.toString());
		    for(int evolution = 0; evolution < numberOfEvolutions; evolution++) {
		    	myWriter.write(Integer.toString(evolution+1));
				
		    	final ArrayList<String> currentEvolution = table.get(evolution);
				final int sizeOfEvolution = currentEvolution.size();
				
				for(int number = 0; number < sizeOfEvolution; number++) {
					myWriter.write(delimiter);
					myWriter.write(currentEvolution.get(number));
				}
				myWriter.write("\n");
			}
		    myWriter.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
}
