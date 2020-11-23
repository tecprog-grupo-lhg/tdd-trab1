package tst;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import app.Parser;

@RunWith(Parameterized.class)
class ParserTest {
	private Object[][] parameters = new Object[][]{
		{
			"analysis.out",
			Arrays.asList(
				new ArrayList<String>(Arrays.asList("333", "807", "123")),
				new ArrayList<String>(Arrays.asList("333", "807")),
				new ArrayList<String>(Arrays.asList("333"))
			)
		},
		{
			"analysis2.out",
			Arrays.asList(
				new ArrayList<String>(Arrays.asList("243", "645", "123", "567")),
				new ArrayList<String>(Arrays.asList("333", "807", "756", "234", "345")),
				new ArrayList<String>(Arrays.asList("333", "756", "123"))
			)
		},
		{
			"analysis3.out",
			Arrays.asList(
				new ArrayList<String>(Arrays.asList("333", "807", "123")),
				new ArrayList<String>(Arrays.asList("333", "807")),
				new ArrayList<String>(Arrays.asList("333")),
				new ArrayList<String>(Arrays.asList("333", "807", "123", "432", "867", "122", "98")),
				new ArrayList<String>(Arrays.asList()),
				new ArrayList<String>(Arrays.asList("333", "54"))
			)
		},
		{
			"analysis4.out",
			Arrays.asList(
				new ArrayList<String>(Arrays.asList("12345"))
			)
		},
		{
			"analysis5.out",
			Arrays.asList(
				new ArrayList<String>(Arrays.asList("123")),
				new ArrayList<String>(Arrays.asList("123")),
				new ArrayList<String>(Arrays.asList("123")),
				new ArrayList<String>(Arrays.asList("123")),
				new ArrayList<String>(Arrays.asList("123")),
				new ArrayList<String>(Arrays.asList("123")),
				new ArrayList<String>(Arrays.asList("123")),
				new ArrayList<String>(Arrays.asList("123")),
				new ArrayList<String>(Arrays.asList("123")),
				new ArrayList<String>(Arrays.asList("123")),
				new ArrayList<String>(Arrays.asList("123")),
				new ArrayList<String>(Arrays.asList("123")),
				new ArrayList<String>(Arrays.asList("123")),
				new ArrayList<String>(Arrays.asList("123")),
				new ArrayList<String>(Arrays.asList("123")),
				new ArrayList<String>(Arrays.asList("123")),
				new ArrayList<String>(Arrays.asList("123")),
				new ArrayList<String>(Arrays.asList("123")),
				new ArrayList<String>(Arrays.asList("123")),
				new ArrayList<String>(Arrays.asList("123"))
			)
		},
	};
	private Parser p;

	@Test
    public void parseTest1() {
		p = new Parser();
		File file = new File("analysis.out");
		p.setFile(file);
		p.parse();
		
		ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>(
			Arrays.asList(
				new ArrayList<String>(Arrays.asList("333", "807", "123")),
				new ArrayList<String>(Arrays.asList("333", "807")),
				new ArrayList<String>(Arrays.asList("333"))
			)
		);

		assertEquals(expected, p.getTable());
    }

	@Test
    public void parseTest2() {
		p = new Parser();
		File file = new File("analysis2.out");
		p.setFile(file);
		p.parse();
		
		ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>(
			Arrays.asList(
				new ArrayList<String>(Arrays.asList("243", "645", "123", "567")),
				new ArrayList<String>(Arrays.asList("333", "807", "756", "234", "345")),
				new ArrayList<String>(Arrays.asList("333", "756", "123"))
			)
		);

		assertEquals(expected, p.getTable());
    }
	
	@Test
    public void parseTest3() {
		p = new Parser();
		File file = new File("analysis.out");
		p.setFile(file);
		p.parse();
		
		ArrayList<ArrayList<String>> expected = new ArrayList<ArrayList<String>>(
			Arrays.asList(
				new ArrayList<String>(Arrays.asList("333", "807", "123")),
				new ArrayList<String>(Arrays.asList("333", "807")),
				new ArrayList<String>(Arrays.asList("333"))
			)
		);

		assertEquals(expected, p.getTable());
		
		file = new File("analysis2.out");
		p.setFile(file);
		p.parse();
		
		expected = new ArrayList<ArrayList<String>>(
			Arrays.asList(
				new ArrayList<String>(Arrays.asList("243", "645", "123", "567")),
				new ArrayList<String>(Arrays.asList("333", "807", "756", "234", "345")),
				new ArrayList<String>(Arrays.asList("333", "756", "123"))
			)
		);

		assertEquals(expected, p.getTable());
    }
	
	@ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4})
    void parseParameterizedTest(int index) {
		p = new Parser();
		File file = new File((String)parameters[index][0]);
		p.setFile(file);
		p.parse();
		
		Object obj = parameters[index][1];

		assertEquals(obj, p.getTable());
    }
}
