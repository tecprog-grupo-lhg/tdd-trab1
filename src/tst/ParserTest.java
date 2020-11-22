package tst;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Path;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import app.Parser;
import exceptions.EscritaNaoPermitidaException;
import exceptions.DelimitadorInvalidoException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

class ParserTest {
		private Parser p;
	
		@Test
		void testWritOutputFile() throws EscritaNaoPermitidaException {
					p = new Parser();
					p.setFile("analysisTime.out");
					
					
					p.setOutPut( new File(System.getProperty("user.dir")).toPath().toString());	      
					Path actual = p.getPath();
					
					Path path =  new File(System.getProperty("user.dir") + "/analysisTimeTab.out").toPath();
					
					assertEquals(path, actual);
		}
	
		@Test
		void testWritOutputFile2() throws EscritaNaoPermitidaException {
					p = new Parser();
					p.setFile("analysisTime.out");
					
					Assertions.assertThrows(EscritaNaoPermitidaException.class, () -> p.setOutPut("~/root"));
		}

		@Test
		void testWritOutputFile3() throws EscritaNaoPermitidaException {
					p = new Parser();
					p.setFile("analysisTime.out");
					
					p.setOutPut( new File(System.getProperty("user.dir")).toPath().toString());	      
					Path actual = p.getPath();
					
					Path path =  new File(System.getProperty("user.dir") + "/analysisTimeTab.out").toPath();
					
					assertEquals(path, actual);
					Assertions.assertThrows(EscritaNaoPermitidaException.class, () -> p.setOutPut("~/root"));
		}
	
    @ParameterizedTest
    @CsvSource( {
    		"totalTime.out,/totalTimeTab.out",
    		"analysisTime.out,/analysisTimeTab.out",
    		})
    void testWriteOutputFileValidPaths(String input, String correctOutput) throws EscritaNaoPermitidaException {
    	p = new Parser();
    	p.setFile(input);
    	p.setOutPut( new File(System.getProperty("user.dir")).toPath().toString());
    	
    	Path actual = p.getPath();
    	 Path path =  new File(System.getProperty("user.dir") + correctOutput).toPath();
    	
    	assertEquals(path, actual);
    }
    
    @ParameterizedTest
    @CsvSource( {
    		"totalTime.out,/totalTimeTab.out",
    		"analysisTime.out,/analysisTimeTab.out",
    		})
    void testWriteOutputFileValidPaths2(String input, String correctOutput) throws EscritaNaoPermitidaException {
    	p = new Parser();
    	p.setFile(input);

    	Assertions.assertThrows(EscritaNaoPermitidaException.class, () -> p.setOutPut("~/root"));
    }

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
