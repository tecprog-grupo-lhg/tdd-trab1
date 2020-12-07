package app;

import java.io.IOException;
import java.util.Scanner;

import exceptions.ArquivoNaoEncontradoException;
import exceptions.DelimitadorInvalidoException;
import exceptions.EscritaNaoPermitidaException;
import exceptions.FormatoDeSaidaInvalidoException;

public class Main {
	public static void main(String[] args) {
		Parser parser = new Parser();
		Scanner scanner = new Scanner(System.in);
		
		chooseFile(parser, scanner);
		chooseDelimiter(parser, scanner);
		chooseOutputPath(parser, scanner);
		chooseSequenceFormatValues(parser, scanner);
		readFileAndWriteResults(parser);
		openOutputFile(parser, scanner);
		
		System.out.println("Pronto!");
	}

	static void chooseFile(Parser parser, Scanner scanner) {
		boolean isValid = false;

		System.out.print("Insira o nome do arquivo que deseja abrir: ");
		while(!isValid) {
			final String fileName = scanner.next();

			try {
				parser.setFile(fileName);
				isValid = true;
			} catch (ArquivoNaoEncontradoException e) {
				System.out.print("O arquivo '" + fileName + "' não foi encontrado. Tente novamente: ");
			}
		}
	}

	static void chooseDelimiter(Parser parser, Scanner scanner) {
		boolean isValid = false;
		System.out.print("Insira o delimitador: ");
		while(!isValid) {
			final String delimiter = scanner.next();
			
			try {
				parser.setDelimiter(delimiter);
				isValid = true;
			} catch (DelimitadorInvalidoException e) {
				System.out.print("O delimitador '" + delimiter + "' não é permitido. Tente novamente: ");
			}
		}

		scanner.nextLine();
	}

	static void chooseOutputPath(Parser parser, Scanner scanner) {
		boolean isValid = false;
		System.out.print("Insira o caminho relativo do arquivo de saída: ");
		while(!isValid) {
			final String output = scanner.nextLine();
			
			try {
				parser.setOutput(output);
				isValid = true;
			} catch (EscritaNaoPermitidaException e) {
				System.out.print("O caminho '" + output + "' não tem permissão de escrita. Tente novamente: ");
			}
		}

		isValid = false;
	}

	static void chooseSequenceFormatValues(Parser parser, Scanner scanner) {
		boolean isValid = false;
		System.out.print("Insira o formato de saída das sequencias de valores (column / row): ");
		while(!isValid) {
			final String format = scanner.next();
			
			try {
				parser.setFormatToSave(format);
				isValid = true;
			} catch (FormatoDeSaidaInvalidoException e) {
				System.out.print("O formato '" + format + "' não é válido. Tente novamente: ");
			}
		}
	}

	static void readFileAndWriteResults(Parser parser) {
		parser.parse();
		parser.save();
	}

	static void openOutputFile(Parser parser, Scanner scanner) {
		System.out.print("Você gostaria de abrir o arquivo agora? s/n: ");
		final String escolha = scanner.next();
				
		scanner.close();
		
		if(escolha.equals("s")) {
			try {
				Runtime.getRuntime().exec(new String[] {"xdg-open", parser.getPath().toString()});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}