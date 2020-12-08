package app;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.IntStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import exceptions.EscritaNaoPermitidaException;
import exceptions.DelimitadorInvalidoException;
import exceptions.FormatoDeSaidaInvalidoException;
import exceptions.ArquivoNaoEncontradoException;
import utils.Helpers;
import java.io.FileWriter;
import java.io.IOException;

public class Parser {

	private static final String DELIMITER_TOKEN = "!";
	private static final String IGNORE_TOKEN = "@";
	private char delimiter;
	public Persistência fileManipulation = new Persistência();
	private ArrayList<ArrayList<String>> table;

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
	
	public void setFormatToSave(String format) throws FormatoDeSaidaInvalidoException {
		checkValidOutputFormat(format);
		this.formatToSave = format;
	}
	
	private void checkValidOutputFormat(String format) throws FormatoDeSaidaInvalidoException {
		if (!format.equals("column") && !format.equals("row")) {
			throw new FormatoDeSaidaInvalidoException(format);
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
		this.table = new Table(this).makeTable();
	}

	private void readLinesFromFile() throws FileNotFoundException {
		Scanner scanner = new Scanner(this.fileManipulation.input.getFile());
		int evolutionCounter = 0;
		while (scanner.hasNextLine()) {
		    final String linha = scanner.nextLine();
		    evolutionCounter = handleEvolutionsFromFile(evolutionCounter, linha);
		}
		scanner.close();
	}

	private int handleEvolutionsFromFile(int evolutionCounter, final String linha) {
		if(linha.charAt(0) == '-') {
			evolutionCounter = createNewEvolution(evolutionCounter);
		} 
		else {
			addNewTimeToEvolution(evolutionCounter, linha);
		}
		return evolutionCounter;
	}

	private int createNewEvolution(int evolutionCounter) {
		this.table.add(new ArrayList<String>());
		return evolutionCounter + 1;
	}

	private void addNewTimeToEvolution(int evolutionCounter, final String linha) {
		this.table.get(evolutionCounter - 1).add(linha);
	}
	
	public ArrayList<ArrayList<String>> getTable() {
		return this.table;
	}

	public void setTable(ArrayList<ArrayList<String>> table) {
		this.table = table;
	}
	
	  
	private void saveAsColumn() {
		final int numberOfEvolutions = table.size();
	    final int biggestEvolutionSize = findBiggestEvolution(numberOfEvolutions);
	    
	    try {
	    	FileWriter writer = new FileWriter(this.fileManipulation.output.getPath().toString());	      
	    	String[][] transposedTable = new String[biggestEvolutionSize][numberOfEvolutions];
	    	for(int i = 0; i < numberOfEvolutions; ++i) {
	    		writeEvolutionIndexToFile(writer, i+1);  
	    		addDelimiter(i+1, writer, numberOfEvolutions);
	    		populateTransposedTable(biggestEvolutionSize, transposedTable, i);
	    	}
			String result = makeResultString(numberOfEvolutions, biggestEvolutionSize, transposedTable);
			writer.write(result);
			writer.close();
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}

	private String makeResultString(final int numberOfEvolutions, final int biggestEvolutionSize,
			String[][] transposedTable) {
		StringBuilder sb = new StringBuilder("\n");
		for(int i = 0; i < biggestEvolutionSize; ++i) {
			for(int j = 0; j < numberOfEvolutions; ++j) {
				if (transposedTable[i][j] != IGNORE_TOKEN) {
					sb.append(transposedTable[i][j]);
					if (j+1 != numberOfEvolutions && transposedTable[i][j+1] != IGNORE_TOKEN) {
						sb.append(delimiter);
					}
				}
			}
			sb.append("\n");
		}
		
		String result = sb.toString().replaceAll(DELIMITER_TOKEN, "");
		return result;
	}

	private void populateTransposedTable(final int biggestEvolutionSize, String[][] transposedTable, int i) {
		for(int j = 0; j < biggestEvolutionSize; ++j) {
			if (j < table.get(i).size()) {
				transposedTable[j][i] = table.get(i).get(j);
				replaceIgnoreTokensWithDelimiterTokens(transposedTable, i, j);
			} else {
				transposedTable[j][i] = IGNORE_TOKEN;
			}
		}
	}

	private int findBiggestEvolution(final int numberOfEvolutions) {
		return IntStream.range(0, numberOfEvolutions)
	    		.map(i -> { return table.get(i).size(); })
	    		.max()
	    		.getAsInt();
	}

	private void replaceIgnoreTokensWithDelimiterTokens(String[][] transposedTable, int i, int j) {
		for(int z = 1; i-z >= 0 && transposedTable[j][i-z] == IGNORE_TOKEN; transposedTable[j][i-z] = DELIMITER_TOKEN, z++);
	}


	private void addDelimiter(final int evolution, FileWriter writer, int numberOfEvolutions) throws IOException {
		if(evolution != numberOfEvolutions) {
			writer.write(delimiter);
		}
	}

	private void writeEvolutionIndexToFile(FileWriter myWriter, int i) throws IOException {
		myWriter.write(Integer.toString(i));
	}
	
	private void saveAsRow() {
		final int numberOfEvolutions = table.size();
		
		try {
			FileWriter myWriter = new FileWriter(fileManipulation.output.getPath().toString());
		    for(int evolution = 1; evolution <= numberOfEvolutions; evolution++) {
		    	writeEvolutionIndexToFile(myWriter, evolution);
				
		    	final ArrayList<String> currentEvolution = table.get(evolution-1);
				final int sizeOfEvolution = currentEvolution.size();
				
				writeEvolutionNumbers(myWriter, sizeOfEvolution, currentEvolution);
			}
		    myWriter.close();
		} catch (IOException e) {
		    e.printStackTrace();
		}
	}
	
	private void writeEvolutionNumbers(FileWriter myWriter, int sizeOfEvolution, ArrayList<String> currentEvolution) throws IOException {
		for(int number = 0; number < sizeOfEvolution; number++) {
			myWriter.write(delimiter);
			myWriter.write(currentEvolution.get(number));
		}
		
		myWriter.write("\n");
	}
}
