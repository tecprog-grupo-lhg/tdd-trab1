package tst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import app.Parser;
import exceptions.DelimitadorInvalidoException;

class ParserTest {
	private Parser p;
	
	@Test
	void setDelimiterTest1() throws DelimitadorInvalidoException {
		p = new Parser();
		p.setDelimiter("\n");
		assertEquals('\n', p.getDelimiter());
	}
	
	@Test
	void setDelimiterTest2() throws DelimitadorInvalidoException {
		p = new Parser();
		Assertions.assertThrows(DelimitadorInvalidoException.class, () -> p.setDelimiter("abc"));
    }
	
    @Test
    public void setDelimiterTest3() throws DelimitadorInvalidoException {
        p = new Parser();

		p.setDelimiter("\n");
		assertEquals('\n', p.getDelimiter());

		p.setDelimiter(";");
		assertEquals(';', p.getDelimiter());

		Assertions.assertThrows(DelimitadorInvalidoException.class, () -> p.setDelimiter("abc"));
		Assertions.assertThrows(DelimitadorInvalidoException.class, () -> p.setDelimiter(""));
    }
}
