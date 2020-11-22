package app;

import java.io.File;

public class Parser {
	private File file;
	
	public void setFile(String fileName) {
		File file = new File(fileName);
		this.file = file;
	}
	
	public String getFile() {
		return "totalTime.out";
	}
}
