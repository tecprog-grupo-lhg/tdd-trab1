package app;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import exceptions.EscritaNaoPermitidaException;
import exceptions.DelimitadorInvalidoException;
import utils.Helpers;

public class Parser {
	
	private char delimiter;
	private File file;
	private Path output;
	
	public void setFile(String fileName) {
		File file = new File(fileName);
		this.file = file;
	}
	
	public void setOutPut(String output) throws EscritaNaoPermitidaException {
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
		char[] escapableChars = new char[] { 't', 'b', 'n', 'r', 'f', '\'', '"', '\\' };
		
		if (!Helpers.arrayContainsChar(escaped_delimiter, escapableChars)) {
			throw new DelimitadorInvalidoException(delimiter);
		}
	}
}

