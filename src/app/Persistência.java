package app;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

import exceptions.ArquivoNaoEncontradoException;
import exceptions.EscritaNaoPermitidaException;

public class Persistência {
	public FileInput input = new FileInput();
	public PathOutput output = new PathOutput();
	
	public class PathOutput {
		private Path path;

		public void setPath(String output) throws EscritaNaoPermitidaException {
			Path path = new File(output).toPath();
			
			if(!Files.isWritable(path)) {
				throw new EscritaNaoPermitidaException(output);
			}
			
			if(input.file.getName().equals("analysisTime.out")) {
				this.path = new File(output + "/analysisTimeTab.out").toPath();
			}
			else {
				this.path = new File(output + "/totalTimeTab.out").toPath();
			}
		}

		public Path getPath() {
			return path;
		}
	}

	public static class FileInput {
		private File file;

		void checkIfFileExists(File file) throws ArquivoNaoEncontradoException {
			if(!file.exists()) {
				throw new ArquivoNaoEncontradoException(file.getName());
			}
		}

		public void setFile(String fileName) throws ArquivoNaoEncontradoException {
			File file = new File(fileName);
			checkIfFileExists(file);
			this.file = file;
		}

		public File getFile() {
			return file;
		}
	}
}