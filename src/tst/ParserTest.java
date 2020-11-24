package tst;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import app.Parser;
import exceptions.FormatoDeSaídaInvalidoException;

class ParserTest {
	private Parser p;
	
	// @TODO: remove this block afterwards; only being used so that we can test the stdout
	// at "save" test { checking that the method is in fact correctly calling the correspondent
	// function
	private final PrintStream standardOut = System.out;
	private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
	@BeforeEach
	public void setUp() {
	    System.setOut(new PrintStream(outputStreamCaptor));
	}
	// -----------------------------------------------------------------------------------
	
	@Test
	void testSetFormat1() throws FormatoDeSaídaInvalidoException {
		p = new Parser();
		p.setFormatToSave("column");
		assertEquals("column", p.getFormatToSave());
	}

	@Test
	void testSetFormat2() throws FormatoDeSaídaInvalidoException {
		p = new Parser();
		Assertions.assertThrows(FormatoDeSaídaInvalidoException.class, () -> p.setFormatToSave("formato aleatorio"));
	}
    
	@Test
	void testSetFormat3() throws FormatoDeSaídaInvalidoException {
		p = new Parser();
		p.setFormatToSave("column");
		assertEquals("column", p.getFormatToSave());

		p.setFormatToSave("row");
		assertEquals("row", p.getFormatToSave());
		
		p.setFormatToSave("column");
		assertNotEquals("row", p.getFormatToSave());
		
		p.setFormatToSave("row");
		assertNotEquals("column", p.getFormatToSave());
	
		Assertions.assertThrows(FormatoDeSaídaInvalidoException.class, () -> p.setFormatToSave("outro formato"));
	}
	
    @ParameterizedTest
    @ValueSource(strings = { "row", "column" })
    void setFormatParameterizedWithAllowedFormats(String format) throws FormatoDeSaídaInvalidoException {
    	p = new Parser();
		p.setFormatToSave(format);
    	assertEquals(format, p.getFormatToSave());
    }

    @ParameterizedTest
    @ValueSource(strings = { "", "a", "abc", "outro formato", "formato invalido", "diagonal" })
    void setFormatParameterizedWithInvalidFormats(String format) throws FormatoDeSaídaInvalidoException {
    	p = new Parser();
		Assertions.assertThrows(FormatoDeSaídaInvalidoException.class, () -> p.setFormatToSave(format));
    }
    
	@Test
	void testSave() throws FormatoDeSaídaInvalidoException {
		p = new Parser();
		p.setFormatToSave("row");
		p.save();
		assertEquals("Saving as row", outputStreamCaptor.toString().trim());	
	}
}
