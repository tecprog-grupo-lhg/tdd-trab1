package app;

import java.io.IOException;
import java.util.Scanner;

import exceptions.ArquivoNaoEncontradoException;
import exceptions.DelimitadorInvalidoException;
import exceptions.EscritaNaoPermitidaException;
import exceptions.FormatoDeSaídaInvalidoException;

public class Main {

	public static void main(String[] args) {
		Parser p = new Parser();
		Scanner sc = new Scanner(System.in);
		boolean invalido = true;
		
		// analysisTime.out
		// totalTime.out
		System.out.print("Insira o nome do arquivo que deseja abrir: ");
		while(invalido) {
			final String fileName = sc.next();

			try {
				p.fileManipulation.input.setFile(fileName);
				invalido = false;
			} catch (ArquivoNaoEncontradoException e) {
				System.out.print("O arquivo '" + fileName + "' não foi encontrado. Tente novamente: ");
			}
		}
		
		invalido = true;
		
		System.out.print("Insira o delimitador: ");
		while(invalido) {
			final String delimiter = sc.next();
			
			try {
				p.setDelimiter(delimiter);
				invalido = false;
			} catch (DelimitadorInvalidoException e) {
				System.out.print("O delimitador '" + delimiter + "' não é permitido. Tente novamente: ");
			}
		}
		
		// pegar \n
		sc.nextLine();
		invalido = true;
		
		// /home/henrique/Área de Trabalho/TPPE/Trab1
		System.out.print("Insira o caminho relativo do arquivo de saída: ");
		while(invalido) {
			final String output = sc.nextLine();
			
			try {
				p.fileManipulation.output.setPath(output);
				invalido = false;
			} catch (EscritaNaoPermitidaException e) {
				System.out.print("O caminho '" + output + "' não tem permissão de escrita. Tente novamente: ");
			}
		}

		invalido = true;
		System.out.print("Insira o formato de saída das sequencias de valores (column / row): ");
		while(invalido) {
			final String format = sc.next();
			
			try {
				p.setFormatToSave(format);
				invalido = false;
			} catch (FormatoDeSaídaInvalidoException e) {
				System.out.print("O formato '" + format + "' não é válido. Tente novamente: ");
			}
		}
		
		p.parse();
		p.save();
		
		System.out.print("Você gostaria de abrir o arquivo agora? s/n: ");
		final String escolha = sc.next();
				
		sc.close();
		
		if(escolha.equals("s")) {
			try {
				Runtime.getRuntime().exec(new String[] {"xdg-open", p.fileManipulation.output.getPath().toString()});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("Pronto!");
	}
}