package com.leetcode.oj.recursion;

/**
 * ¡®.¡¯ Matches any single character. ¡®*¡¯ Matches zero or more of the preceding
 * element. The matching should cover the entire input string (not partial).
 * 
 * The function prototype should be: bool isMatch(const char *s, const char *p)
 * 
 * @author flu
 * 
 */
public class RegexMatch {

    public boolean isMatch(String s, String p) {
        if (s == null || p == null)
            return false;

        if (p.isEmpty())
            return p.equals(s);

        if (s.isEmpty()) {
            if (p.length() > 1 && (p.charAt(0) != '*' && p.charAt(1) == '*'))
                return isMatch(s, p.substring(2));
            else
                return s.equals(p);
        }

        if (p.length() == 1) {
            return p.equals(s) || (p.equals(".") && s.length() == 1);
        }

        if (p.charAt(1) != '*')
            return (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0)) && isMatch(s.substring(1), p.substring(1));
        else {
            while (s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
                if (isMatch(s, p.substring(2)))
                    return true;
                else
                    s = s.substring(1);
            }
        }

        return isMatch(s, p.substring(2));
    }

    public static void main(String[] args) throws Exception {
        String s = "";
        String p = "**";

        System.out.println(new RegexMatch().isMatch(s, p));
    }

}
