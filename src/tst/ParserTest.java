package tst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import app.Parser;
class ParserTest {
	private Parser p;
	
	@Test
	void testSetFormat1() {
		p = new Parser();
		p.setFormatToSave("column");
		assertEquals("column", p.getFormatToSave());
	}

	@Test
	void testSetFormat2() {
		p = new Parser();
		p.setFormatToSave("row");
		assertNotEquals("column", p.getFormatToSave());
	}
	
	@Test
	void testSetFormat3() {
		p = new Parser();
		p.setFormatToSave("column");
		assertEquals("column", p.getFormatToSave());

		p.setFormatToSave("row");
		assertEquals("row", p.getFormatToSave());
		
		p.setFormatToSave("column");
		assertNotEquals("row", p.getFormatToSave());
		
		p.setFormatToSave("row");
		assertNotEquals("column", p.getFormatToSave());
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
