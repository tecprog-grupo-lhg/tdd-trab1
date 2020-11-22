package tst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import app.Parser;

class ParserTest {
	private Parser p;
	
	@Test
	void setDelimiterTest() {
		p = new Parser();
		p.setDelimiter();
		assertEquals('x', p.getDelimiter());
	}
}
