package tst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import utils.Helpers;

public class HelpersTest {

	@Test
	void arrayContainsCharTest1() {
		char[] charArray = new char[] { 'a' };
		char searchedChar = 'a';
		assertEquals(true, Helpers.arrayContainsChar(searchedChar, charArray));
	}
	
	@Test
	void arrayContainsCharTest2() {
		char[] charArray = new char[] { 'b' };
		char searchedChar = 'a';
		assertEquals(false, Helpers.arrayContainsChar(searchedChar, charArray));
	}
	
	@Test
	void arrayContainsCharTest3() {
		char[] charArray = new char[] { 'a', 'b' };
		assertEquals(true, Helpers.arrayContainsChar('a', charArray));
		assertEquals(true, Helpers.arrayContainsChar('b', charArray));
		assertEquals(false, Helpers.arrayContainsChar('c', charArray));
	}
}
