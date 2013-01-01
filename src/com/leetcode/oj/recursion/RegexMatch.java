package com.leetcode.oj.recursion;

/**
 * ¡®.¡¯ Matches any single character.
¡®*¡¯ Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).

The function prototype should be:
bool isMatch(const char *s, const char *p)

 * @author flu
 *
 */
public class RegexMatch {
	
	static boolean isMatch(String s, String p) throws Exception {
		if (s == null || p == null)
			return false;
		
		// need to simplify edge cases!
		if (p.equals(""))
			return s.equals(p);
		
		if (s.equals("")) {
			if (p.length() > 1 && p.charAt(1) == '*')
				return isMatch(s, p.substring(2));
			else
				return s.equals(p);
		}
		
		int p_len = p.length();
		
		if (p_len == 1) {
			if (p.charAt(0) == '*')
				return false;
			else if (p.charAt(0) == '.')
				return s.length() == 1;
			else
				return p.equals(s);				
		}
		
		if (p_len > 1 && p.charAt(1) != '*') {
			return (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')
					&& isMatch(s.substring(1), p.substring(1));
		}
		
		// next char is *
		while (s.length()>0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
			if (isMatch(s, p.substring(2)))
				return true;
			s = s.substring(1);
		}
		
		return isMatch(s, p.substring(2));		
	}
	
	public static void main(String[] args) throws Exception {
		String s = "";
		String p = "c*";
		
		System.out.println(RegexMatch.isMatch(s, p));
	}

}
