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
}
