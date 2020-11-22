package tst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import utils.Helpers;

public class HelpersTest {

	@Test
	void arrayContainsCharTest() {
		char[] charArray = new char[] { 'a' };
		char searchedChar = 'a';
		assertEquals(true, Helpers.arrayContainsChar(searchedChar, charArray));
	}
}
