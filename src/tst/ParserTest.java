package tst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import app.Parser;

class ParserTest {

	private Parser p;

    @Test
    public void setFileTest1() {
      String fileName = "totalTime.out";
      p = new Parser();
      p.setFile(fileName);
      assertEquals(fileName, p.getFile());
    }

}
