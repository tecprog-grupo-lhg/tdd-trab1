package app;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
		ArrayList<Integer> evolutionSizes = new ArrayList<Integer>();
		ArrayList<Boolean> hasEvolutionFinished = new ArrayList<Boolean>();
		
		try {
			FileWriter myWriter = new FileWriter(fileManipulation.output.getPath().toString());
			
			createHeaderRow(numberOfEvolutions, evolutionSizes, hasEvolutionFinished, myWriter);
			
			int remainingEvolutions = numberOfEvolutions;
			int index = 0;
			int lastEvolutionNotFinished = numberOfEvolutions;
			
			while(remainingEvolutions != 0) {
				for(int i = 1; i <= numberOfEvolutions; i++) {
					if(hasEvolutionFinished.get(i-1)) {
						if(i < lastEvolutionNotFinished && i != 1) {
							myWriter.write(delimiter);
						}
						continue;
					}
					
					if(i != 1) {
						myWriter.write(delimiter);
					}
					
					if(index < evolutionSizes.get(i-1)) {
						myWriter.write(table.get(i-1).get(index));
					}
					
					if(evolutionIsOver(index, i, evolutionSizes, hasEvolutionFinished)) {
						remainingEvolutions--;
						hasEvolutionFinished.set(i-1, true);
						lastEvolutionNotFinished = getLastEvolutionNotFinished(lastEvolutionNotFinished, hasEvolutionFinished);
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
	
	private Boolean evolutionIsOver(int index, int i, ArrayList<Integer> evolutionSizes,
			ArrayList<Boolean> hasEvolutionFinished) {
		return index == evolutionSizes.get(i-1) - 1 && !hasEvolutionFinished.get(i-1);
	}
	
	private int getLastEvolutionNotFinished(int lastEvolutionNotFinished, ArrayList<Boolean> hasEvolutionFinished) {
		for(int j = lastEvolutionNotFinished; j > 0; j--) {
			if(!hasEvolutionFinished.get(j-1)) {
				lastEvolutionNotFinished = j;
				break;
			}
		}
		
		return lastEvolutionNotFinished;
	}

	private void createHeaderRow(final int numberOfEvolutions, ArrayList<Integer> evolutionSizes,
			ArrayList<Boolean> hasEvolutionFinished, FileWriter writer) throws IOException {
		for(int evolution = 1; evolution <= numberOfEvolutions; evolution++) {
			writeEvolutionIndexToFile(writer, evolution);
			
			addEvolutionSize(evolutionSizes, evolution);
			
			hasEvolutionFinished.add(false);
			
			addDelimiter(numberOfEvolutions, writer, evolution);
		}
		
		writer.write("\n");
	}

	private void addEvolutionSize(ArrayList<Integer> evolutionSizes, int evolution) {
		int evolutionSize = table.get(evolution-1).size();
		evolutionSizes.add(evolutionSize);
	}

	private void addDelimiter(final int numberOfEvolutions, FileWriter writer, int evolution) throws IOException {
		if(isNotLastEvolution(numberOfEvolutions, evolution)) {
			writer.write(delimiter);
		}
	}

	private boolean isNotLastEvolution(final int numberOfEvolutions, int evolution) {
		return evolution != numberOfEvolutions;
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
