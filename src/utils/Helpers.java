package utils;

public class Helpers {
	public static Boolean arrayContainsChar(char ch, char[] charArray) {
		for (char x : charArray) {
		    if (ch == x) {
		        return true;
		    }
		}
		return false;
	}
}
