package com.leetcode.oj.recursion;

/**
 * '?' Matches any single character.
 * 
 * '*' Matches any sequence of characters (including the empty sequence).
 * 
 * The matching should cover the entire input string (not partial).
 * 
 * The function prototype should be:
 * 
 * bool isMatch(const char *s, const char *p)
 * 
 * Some examples:
 * 
 * isMatch("aa","a") ¡ú false
 * 
 * isMatch("aa","aa") ¡ú true
 * 
 * isMatch("aaa","aa") ¡ú false
 * 
 * isMatch("aa", "*") ¡ú true
 * 
 * isMatch("aa", "a*") ¡ú true
 * 
 * isMatch("ab", "?*") ¡ú true
 * 
 * isMatch("aab", "c*a*b") ¡ú false
 * 
 * @author flu
 * 
 */
public class WildcardMatching {

    /*
     * Using a "greedy" approach, where we try to reach the end of p, saving the last occurrence of '*' but match an *
     * by the empty string. In the case that we arrive to a state we cannot go on, we retrieve the saved state and we
     * make the * to match one character more each than the previous time.
     */
    public boolean isMatch(String s, String p) {
        if (p == null || s == null)
            return false;
        
        int si = 0, pi = 0, lasts = -1, lastp = -1;
        while (si < s.length()) {
            if (pi < p.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '?')) {
                si++;
                pi++;
                continue;
            }

            // greedy, skip * if possible
            if (pi < p.length() && p.charAt(pi) == '*') {
                lastp = pi++;
                lasts = si;
                continue;
            }
            
            // no longer has *, now back trace last *
            if (lastp != -1 && lastp < p.length()) {
                si = lasts++; // move lasts forward
                pi = lastp + 1; // keep lastp to potentially match more char
                continue;
            }
            return false;
        }
        
        // check last section of p they all need to be * otherwise no match.
        while (pi < p.length() && p.charAt(pi) == '*') {
            pi++;
        }
        return pi == p.length();
    }

    public boolean isMatch2(String s, String p) {
        int si = 0, pi = 0;
        int ss = -1, pp = -1;
        while (si < s.length()) {
            if (si < s.length() && pi < p.length() && s.charAt(si) == p.charAt(pi)) {
                si++;
                pi++;
                continue;
            }
            if (si < s.length() && pi < p.length() && p.charAt(pi) == '?') {
                si++;
                pi++;
                continue;
            }
            if (si < s.length() && pi < p.length() && p.charAt(pi) == '*') {
                ss = si;
                pp = pi++;
                continue;
            }
            if (pp != -1 && pp < p.length()) {
                si = ss++;
                pi = pp + 1;
                continue;
            }
            return false;
        }
        while (pi < p.length() && p.charAt(pi) == '*') {
            pi++;
        }
        return pi == p.length();
    }

    public static void main(String[] args) {
        String s = "ab";
        String p = "*?*?*";
        System.out.println(new WildcardMatching().isMatch(s, p));
    }

}
