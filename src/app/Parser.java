package app;

import java.io.File;

import exceptions.ArquivoNaoEncontradoException;

public class Parser {
	private File file;
	
	public void setFile(String fileName) throws ArquivoNaoEncontradoException{
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
}
