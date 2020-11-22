package tst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import app.Parser;
import exceptions.DelimitadorInvalidoException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

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

    @ParameterizedTest
    @ValueSource(strings = {
    		"a", "b", "c", "d", "e",
    		"f", "g", "h", "i", "j",
    		"k", "l", "m", "n", "o",
    		"p", "q", "r", "s", "t",
    		"u", "v", "w", "x", "y",
    		"z", "!", "@", "#", "$",
    		"%", "&", "*", "(", ")",
    		"-", "_", "=", "+", "`",
    		"[", "{", "}", "]", "~",
    		"^", ".", ",", "<", ">",
    		";", ":", "/", 
    		"\t", "\b", "\n", "\r",
    		"\f", "\'", "\"", "\\",
    		"\\t", "\\b", "\\n", "\\r",
    		"\\f", "\\\'", "\\\"", "\\\\"
    	})
    void setDelimiterParameterizedTestValidDelimiters(String delimiter) throws DelimitadorInvalidoException {
    	p = new Parser();
    	p.setDelimiter(delimiter);
    	char delimiterAsChar = delimiter.charAt(0);
		assertEquals(delimiterAsChar, p.getDelimiter());
    }
    
    @ParameterizedTest
    @ValueSource(strings = {
    		"", "ab", "abc", "\nabc",
    		"\nn", "\\nn", "\\c", "\\d",
    		"\\e", "\\g", "\\h", "\\i",
    		"\\j", "\\k", "\\l", "\\m",
    		"\\o", "\\p", "\\q", "\\s",
    		"\\u", "\\v", "\\x", "\\w",
    		"\\y", "\\z", "\\@", "\\!",
    		"\\#", "\\$", "\\%", "\\&",
    		"\\*", "\\(", "\\)", "\\-",
    		"\\=", "\\[", "\\]", "\\{",
    		"\\}", "\\`", "\\,", "\\.",
    		"\\;", "\\ "
    })
    void setDelimiterParameterizedTestInvalidDelimiters(String delimiter) throws DelimitadorInvalidoException {
    	p = new Parser();
    	Assertions.assertThrows(DelimitadorInvalidoException.class, () -> p.setDelimiter(delimiter));
    }
    
}
