package app;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import exceptions.EscritaNaoPermitidaException;
import exceptions.DelimitadorInvalidoException;
import exceptions.FormatoDeSaídaInvalidoException;
import exceptions.ArquivoNaoEncontradoException;
import utils.Helpers;
import java.io.FileWriter;
import java.io.IOException;

public class Parser {

	private char delimiter;
	private File file;
	private Path output;
	private ArrayList<ArrayList<String>> table;

	public void setOutput(String output) throws EscritaNaoPermitidaException {
		Path path = new File(output).toPath();
		
		if(!Files.isWritable(path)) {
			throw new EscritaNaoPermitidaException(output);
		}
		
		if(file.getName().equals("analysisTime.out")) {
			this.output = new File(output + "/analysisTimeTab.out").toPath();
		}
		else {
			this.output = new File(output + "/totalTimeTab.out").toPath();
		}
	}	

	public Path getPath() {
		return this.output;
	}
	
	public void setFile(String fileName) throws ArquivoNaoEncontradoException {
		File file = new File(fileName);
		checkIfFileExists(file);
		this.file = file;
	}
	
	public String getFile() {
		return this.file.getName();
	}

	private void checkIfFileExists(File file) throws ArquivoNaoEncontradoException {
		if(!file.exists()) {
			throw new ArquivoNaoEncontradoException(file.getName());
		}
	}

	public char getDelimiter() {
		return this.delimiter;
	}
	
	public void setDelimiter(String delimiter) throws DelimitadorInvalidoException {
		isValidDelimiter(delimiter);
		this.delimiter = delimiter.charAt(0);
	}
		
	private void isValidDelimiter(String delimiter) throws DelimitadorInvalidoException {
		if(delimiter.length() == 2 && delimiter.charAt(0) == '\\') {
			isValidEscapedChar(delimiter);
		} else if (delimiter.length() != 1) {
			throw new DelimitadorInvalidoException(delimiter);
		}
	}
	
	private void isValidEscapedChar(String delimiter) throws DelimitadorInvalidoException {
		char escaped_delimiter = delimiter.charAt(1);
		char[] escapableChars = new char[] { 't', 'b', 'n', 'r', 'f', '\'', '\"', '\\' };
		if (!Helpers.arrayContainsChar(escaped_delimiter, escapableChars)) {
			throw new DelimitadorInvalidoException(delimiter);
		}
	}

	private String formatToSave;

	public String getFormatToSave() {
		return this.formatToSave;
	}
	
	public void setFormatToSave(String format) throws FormatoDeSaídaInvalidoException {
		checkValidOutputFormat(format);
		this.formatToSave = format;
	}
	
	private void checkValidOutputFormat(String format) throws FormatoDeSaídaInvalidoException {
		if (!format.equals("column") && !format.equals("row")) {
			throw new FormatoDeSaídaInvalidoException(format);
		}
	}
	
	public void save() {
		if (this.formatToSave.equals("column")) {
			saveAsColumn();
		} else {
			saveAsRow();
		}
	}

	public void parse() {
		table = new ArrayList<ArrayList<String>>();
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

	public void setTable(ArrayList<ArrayList<String>> table) {
		this.table = table;
	}
	
	
	private void saveAsColumn() {
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
	
	private void saveAsRow() {
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
