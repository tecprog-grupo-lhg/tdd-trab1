package tst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import app.Parser;
class ParserTest {
	private Parser p;
	
	@Test
	void testSetFormat() {
		p = new Parser();
		p.setFormatToSave("column");
		assertEquals("column", p.getFormatToSave());
	}
	
	@Test
	void testSave() {
		fail("Not yet implemented");
	}
	
	@Test
	void testSaveAsColumn() {
		fail("Not yet implemented");
	}

	@Test
	void testSaveAsRow() {
		fail("Not yet implemented");
	}
}
