package tst;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import app.Parser;

class ParserTest {
	Parser p;

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

}
