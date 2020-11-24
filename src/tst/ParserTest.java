package tst;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.nio.file.Path;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import app.Parser;

class ParserTest {
	private Parser p;
	
	@Test
	void testWritOutputFile() {
	      p = new Parser();
	      p.setFile("analysisTime.out");
	      
	      
	      p.setOutPut( new File(System.getProperty("user.dir")).toPath().toString());	      
	      Path actual = p.getPath();
	      
	      Path path =  new File(System.getProperty("user.dir") + "/analysisTimeTab.out").toPath();
	      
	      assertEquals(path, actual);
	}

	
}
