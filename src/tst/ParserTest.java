package tst;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import app.Parser;
import exceptions.ArquivoNaoEncontradoException;

@RunWith(Parameterized.class)
class ParserTest {

	private Parser p;

    @Test
    public void setFileTest1() throws ArquivoNaoEncontradoException {
    	String fileName = "totalTime.out";
    	p = new Parser();
    	p.setFile(fileName);
      	assertEquals(fileName, p.getFile());
    }
    
    @Test
    public void setFileTest2() throws ArquivoNaoEncontradoException {
    	p = new Parser();
    	Assertions.assertThrows(ArquivoNaoEncontradoException.class, () -> p.setFile("thisfiledoesnotexistsforsure.txt"));
    }
    
    @Test
    public void setFileTest3() throws ArquivoNaoEncontradoException {
        p = new Parser();

        p.setFile("totalTime.out");
        assertEquals("totalTime.out", p.getFile());
        
        p.setFile("analysisTime.out");
        assertEquals("analysisTime.out", p.getFile());

        Assertions.assertThrows(ArquivoNaoEncontradoException.class, () -> p.setFile("thisfiledoesnotexistsforsure.txt"));
    }
    
    @ParameterizedTest
    @ValueSource(strings = {
    		"totalTime.out",
    		"analysisTime.out",
    		"analysis/analysis1.out", "analysis/analysis2.out", "analysis/analysis3.out", "analysis/analysis4.out", "analysis/analysis5.out", "analysis/analysis6.out", "analysis/analysis7.out", "analysis/analysis8.out", "analysis/analysis9.out", "analysis/analysis10.out", "analysis/analysis11.out", "analysis/analysis12.out", "analysis/analysis13.out", "analysis/analysis14.out", "analysis/analysis15.out", "analysis/analysis16.out", "analysis/analysis17.out", "analysis/analysis18.out", "analysis/analysis19.out", "analysis/analysis20.out", "analysis/analysis21.out", "analysis/analysis22.out", "analysis/analysis23.out", "analysis/analysis24.out", "analysis/analysis25.out", "analysis/analysis26.out", "analysis/analysis27.out", "analysis/analysis28.out", "analysis/analysis29.out", "analysis/analysis30.out", "analysis/analysis31.out", "analysis/analysis32.out", "analysis/analysis33.out", "analysis/analysis34.out", "analysis/analysis35.out", "analysis/analysis36.out", "analysis/analysis37.out", "analysis/analysis38.out", "analysis/analysis39.out", "analysis/analysis40.out", "analysis/analysis41.out", "analysis/analysis42.out", "analysis/analysis43.out", "analysis/analysis44.out", "analysis/analysis45.out", "analysis/analysis46.out", "analysis/analysis47.out", "analysis/analysis48.out", "analysis/analysis49.out", "analysis/analysis50.out",
    		})
    void setFileParameterizedTestExistingFiles(String filePath) throws ArquivoNaoEncontradoException {
    	p = new Parser();
    	p.setFile(filePath);
    	String[] token = filePath.split("/");
    	String fileName = token[token.length-1];
    	assertEquals(fileName, p.getFile());
    }
    
    @ParameterizedTest
    @ValueSource(strings = {
    		"thisfiledoesnotexistsforsure.txt",
    		"abcd.out",
    		"xyz.out",
    		"totalTime2.out",
    		"analysisTime2.out",
    		})
    void setFileParameterizedTestNotExistingFiles(String fileName) throws ArquivoNaoEncontradoException {
    	p = new Parser();
    	Assertions.assertThrows(ArquivoNaoEncontradoException.class, () -> p.setFile(fileName));
    }
}

