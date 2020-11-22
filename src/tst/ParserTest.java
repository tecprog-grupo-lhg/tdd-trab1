package tst;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import app.Parser;
import exceptions.ArquivoNaoEncontradoException;

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
}
