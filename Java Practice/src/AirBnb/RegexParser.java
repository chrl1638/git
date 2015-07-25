/*
 * Problem Statement: Implement a simple regex parser which, given a string and a pattern, 
 * returns a boolean indicating whether the input matches the pattern. By simple, we mean that 
 * the regex can only contain special character: * (star), . (dot), + (plus). The star means 
 * what you'd expect, that there will be zero or more of previous character in that place in the 
 * pattern. The dot means any character for that position. The plus means one or more of 
 * previous character in that place in the pattern. 
 */
package AirBnb;

public class RegexParser {

	public static void main(String[] args) {
		String s = "This is a gooood teeeest.";
		String p = "This .. a go+d te*st.";
		System.out.println("[" + s + "][" + p + "] match is " + simpleRegex(s, 0, p, 0));
	}

	public static boolean simpleRegex(String str, int sIdx, String pattern, int pIdx) {
		if(sIdx >= str.length() || pIdx >= pattern.length())
			if(sIdx == str.length() && pIdx == pattern.length()) 
				return true;
			else
				return false;
		
		switch(pattern.charAt(pIdx)) {
		case '.':
			return simpleRegex(str, sIdx + 1, pattern, pIdx + 1);
		case '*':
			int i = sIdx;
			while( i < str.length() && str.charAt(i) == pattern.charAt(pIdx - 1) ) {
				if(simpleRegex(str, i, pattern, pIdx + 1))
					return true;
				i++;
			}
			return simpleRegex(str, i, pattern, pIdx + 1);
		case '+':
			if(str.charAt(sIdx - 1) != pattern.charAt(pIdx - 1))
				return false;
			String newPattern = pattern.substring(0, pIdx) + "*" + pattern.substring(pIdx + 1);
			return simpleRegex(str, sIdx, newPattern, pIdx);
		default:
			if (str.charAt(sIdx) == pattern.charAt(pIdx))
				return simpleRegex(str, sIdx + 1, pattern, pIdx + 1);
			else {
				if(pIdx + 1 < pattern.length()) {
					if(pattern.charAt(pIdx + 1) == '*') {
						return simpleRegex(str, sIdx, pattern, pIdx + 2);
					} else {
						return false;
					}
				} else
					return false;
			}
		}
	}
}
