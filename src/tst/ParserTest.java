package tst;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import app.Parser;

class ParserTest {
	@SuppressWarnings("unchecked")
	private ArrayList<ArrayList<String>>[] parametersTable = new ArrayList[] {
		new ArrayList<ArrayList<String>>( Arrays.asList(
			new ArrayList<String>(Arrays.asList("333", "807", "123")),
			new ArrayList<String>(Arrays.asList("333", "807")),
			new ArrayList<String>(Arrays.asList("333")))
		),
		new ArrayList<ArrayList<String>>( Arrays.asList(
			new ArrayList<String>(Arrays.asList("333")),
			new ArrayList<String>(Arrays.asList("333", "807")),
			new ArrayList<String>(Arrays.asList("333", "807", "123")))
		),
		new ArrayList<ArrayList<String>>( Arrays.asList(
			new ArrayList<String>(Arrays.asList("333", "807", "123", "333", "807", "123")),
			new ArrayList<String>(Arrays.asList("333", "807")),
			new ArrayList<String>(Arrays.asList("333", "333", "807", "123")),
			new ArrayList<String>(Arrays.asList("333", "807")))
		),
	};
	@SuppressWarnings("unchecked")
	private ArrayList<String>[][] parametersAnswer = new ArrayList[][] {
		{
			new ArrayList<String>(Arrays.asList(
				"1;333;807;123",
				"2;333;807",
				"3;333")),
			new ArrayList<String>(Arrays.asList(
				"1;2;3",
				"333;333;333",
				"807;807",
				"123"))
		},
		{
			new ArrayList<String>(Arrays.asList(
				"1;333",
				"2;333;807",
				"3;333;807;123")),
			new ArrayList<String>(Arrays.asList(
				"1;2;3",
				"333;333;333",
				";807;807",
				";;123"))
		},
		{
			new ArrayList<String>(Arrays.asList(
				"1;333;807;123;333;807;123",
				"2;333;807",
				"3;333;333;807;123",
				"4;333;807")),
			new ArrayList<String>(Arrays.asList(
				"1;2;3;4",
				"333;333;333;333",
				"807;807;333;807",
				"123;;807",
				"333;;123",
				"807",
				"123"))
		}
	};
	private Parser p;

	@Test
    public void saveRowTest1() throws IOException {
		p = new Parser();
		File output = new File("analysisTimeTab.out");
		p.setOutput(output.getAbsolutePath());
		p.setFormat("linhas");
		
		ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>(
			Arrays.asList(
				new ArrayList<String>(Arrays.asList("333", "807", "123")),
				new ArrayList<String>(Arrays.asList("333", "807")),
				new ArrayList<String>(Arrays.asList("333"))
			)
		);
		
		p.setTable(table);
		
		p.setDelimiter(';');
		p.save();
		
		ArrayList<String> expected = new ArrayList<String>(Arrays.asList(
				"1;333;807;123",
				"2;333;807",
				"3;333"));

		ArrayList<String> linhas = new ArrayList<String>();
		
		try {
			Scanner sc = new Scanner(output);
			while (sc.hasNextLine()) {
		        final String linha = sc.nextLine();
		        linhas.add(linha);
		    }
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		output.delete();
		assertEquals(expected, linhas);
    }
	
	@Test
    public void saveRowTest2() throws IOException {
		p = new Parser();
		File output = new File("analysisTimeTab.out");
		p.setOutput(output.getAbsolutePath());
		p.setFormat("linhas");
		
		ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>(
			Arrays.asList(
				new ArrayList<String>(Arrays.asList("333")),
				new ArrayList<String>(Arrays.asList("333", "807")),
				new ArrayList<String>(Arrays.asList("333", "807", "123"))
			)
		);
		
		p.setTable(table);
		
		p.setDelimiter(',');
		p.save();
		
		ArrayList<String> expected = new ArrayList<String>(Arrays.asList(
				"1,333",
				"2,333,807",
				"3,333,807,123"));

		ArrayList<String> linhas = new ArrayList<String>();
		
		try {
			Scanner sc = new Scanner(output);
			while (sc.hasNextLine()) {
		        final String linha = sc.nextLine();
		        linhas.add(linha);
		    }
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		output.delete();
		assertEquals(expected, linhas);
    }
	
	@Test
    public void saveRowTest3() throws IOException {
		p = new Parser();
		File output = new File("analysisTimeTab.out");
		p.setOutput(output.getAbsolutePath());
		p.setFormat("linhas");
		
		ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>(
			Arrays.asList(
				new ArrayList<String>(Arrays.asList("333", "807", "123")),
				new ArrayList<String>(Arrays.asList("333", "807")),
				new ArrayList<String>(Arrays.asList("333"))
			)
		);
		
		p.setTable(table);
		
		p.setDelimiter('x');
		p.save();
		
		ArrayList<String> expected = new ArrayList<String>(Arrays.asList(
				"1x333x807x123",
				"2x333x807",
				"3x333"));

		ArrayList<String> linhas = new ArrayList<String>();
		
		try {
			Scanner sc = new Scanner(output);
			while (sc.hasNextLine()) {
		        final String linha = sc.nextLine();
		        linhas.add(linha);
		    }
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		output.delete();
		assertEquals(expected, linhas);
		
		p = new Parser();
		output = new File("analysisTimeTab.out");
		p.setOutput(output.getAbsolutePath());
		p.setFormat("linhas");
		
		table = new ArrayList<ArrayList<String>>(
			Arrays.asList(
				new ArrayList<String>(Arrays.asList("333")),
				new ArrayList<String>(Arrays.asList("333", "807")),
				new ArrayList<String>(Arrays.asList("333", "807", "123"))
			)
		);
		
		p.setTable(table);
		
		p.setDelimiter(',');
		p.save();
		
		expected = new ArrayList<String>(Arrays.asList(
				"1,333",
				"2,333,807",
				"3,333,807,123"));

		linhas = new ArrayList<String>();
		
		try {
			Scanner sc = new Scanner(output);
			while (sc.hasNextLine()) {
		        final String linha = sc.nextLine();
		        linhas.add(linha);
		    }
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		output.delete();
		assertEquals(expected, linhas);
    }
	
	@Test
    public void saveColumnTest1() throws IOException {
		p = new Parser();
		File output = new File("analysisTimeTab.out");
		p.setOutput(output.getAbsolutePath());
		p.setFormat("colunas");
		
		ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>(
			Arrays.asList(
					new ArrayList<String>(Arrays.asList("333", "807", "123")),
					new ArrayList<String>(Arrays.asList("333", "807")),
					new ArrayList<String>(Arrays.asList("333"))
			)
		);
		
		p.setTable(table);
		
		p.setDelimiter(';');
		p.save();
		
		ArrayList<String> expected = new ArrayList<String>(Arrays.asList(
				"1;2;3",
				"333;333;333",
				"807;807",
				"123"));

		ArrayList<String> linhas = new ArrayList<String>();
		
		try {
			Scanner sc = new Scanner(output);
			while (sc.hasNextLine()) {
		        final String linha = sc.nextLine();
		        linhas.add(linha);
		    }
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		output.delete();
		assertEquals(expected, linhas);
    }
	
	@Test
    public void saveColumnTest2() throws IOException {
		p = new Parser();
		File output = new File("analysisTimeTab.out");
		p.setOutput(output.getAbsolutePath());
		p.setFormat("colunas");
		
		ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>(
			Arrays.asList(
					new ArrayList<String>(Arrays.asList("333")),
					new ArrayList<String>(Arrays.asList("333", "807")),
					new ArrayList<String>(Arrays.asList("333", "807", "123"))
			)
		);
		
		p.setTable(table);
		
		p.setDelimiter(',');
		p.save();
		
		ArrayList<String> expected = new ArrayList<String>(Arrays.asList(
				"1,2,3",
				"333,333,333",
				",807,807",
				",,123"));

		ArrayList<String> linhas = new ArrayList<String>();
		
		try {
			Scanner sc = new Scanner(output);
			while (sc.hasNextLine()) {
		        final String linha = sc.nextLine();
		        linhas.add(linha);
		    }
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		output.delete();
		assertEquals(expected, linhas);
    }
	
	@Test
    public void saveColumnTest3() throws IOException {
		p = new Parser();
		File output = new File("analysisTimeTab.out");
		p.setOutput(output.getAbsolutePath());
		p.setFormat("colunas");
		
		ArrayList<ArrayList<String>> table = new ArrayList<ArrayList<String>>(
			Arrays.asList(
					new ArrayList<String>(Arrays.asList("333", "807", "123")),
					new ArrayList<String>(Arrays.asList("333", "807")),
					new ArrayList<String>(Arrays.asList("333"))
			)
		);
		
		p.setTable(table);
		
		p.setDelimiter(';');
		p.save();
		
		ArrayList<String> expected = new ArrayList<String>(Arrays.asList(
				"1;2;3",
				"333;333;333",
				"807;807",
				"123"));

		ArrayList<String> linhas = new ArrayList<String>();
		
		try {
			Scanner sc = new Scanner(output);
			while (sc.hasNextLine()) {
		        final String linha = sc.nextLine();
		        linhas.add(linha);
		    }
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		output.delete();
		assertEquals(expected, linhas);
		
		p = new Parser();
		output = new File("analysisTimeTab.out");
		p.setOutput(output.getAbsolutePath());
		p.setFormat("colunas");
		
		table = new ArrayList<ArrayList<String>>(
			Arrays.asList(
					new ArrayList<String>(Arrays.asList("333")),
					new ArrayList<String>(Arrays.asList("333", "807")),
					new ArrayList<String>(Arrays.asList("333", "807", "123"))
			)
		);
		
		p.setTable(table);
		
		p.setDelimiter(',');
		p.save();
		
		expected = new ArrayList<String>(Arrays.asList(
				"1,2,3",
				"333,333,333",
				",807,807",
				",,123"));

		linhas = new ArrayList<String>();
		
		try {
			Scanner sc = new Scanner(output);
			while (sc.hasNextLine()) {
		        final String linha = sc.nextLine();
		        linhas.add(linha);
		    }
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		output.delete();
		assertEquals(expected, linhas);
    }
	
	@ParameterizedTest
    @ValueSource(ints = {0, 1, 2})
    void saveParameterizedTest(int index) {
		p = new Parser();
		File output = new File("analysisTimeTab.out");
		p.setOutput(output.getAbsolutePath());
		p.setDelimiter(';');
		
		ArrayList<ArrayList<String>> table = parametersTable[index];
		
		p.setTable(table);
		
		// linha
		p.setFormat("linhas");
		p.save();
		ArrayList<String> linhas = new ArrayList<String>();
		
		try {
			Scanner sc = new Scanner(output);
			while (sc.hasNextLine()) {
		        final String linha = sc.nextLine();
		        linhas.add(linha);
		    }
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		assertEquals(parametersAnswer[index][0], linhas);
		
		// coluna
		p.setFormat("colunas");
		p.save();
		linhas = new ArrayList<String>();
		
		try {
			Scanner sc = new Scanner(output);
			while (sc.hasNextLine()) {
		        final String linha = sc.nextLine();
		        linhas.add(linha);
		    }
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		output.delete();
		assertEquals(parametersAnswer[index][1], linhas);
    }
}
