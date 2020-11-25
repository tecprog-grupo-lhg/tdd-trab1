package app;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import exceptions.EscritaNaoPermitidaException;

public class Parser {
	
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
}

