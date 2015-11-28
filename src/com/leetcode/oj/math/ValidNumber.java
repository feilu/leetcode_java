package com.leetcode.oj.math;

import java.util.regex.Pattern;

public class ValidNumber {

    private static final String REGEX = "[+-]?\\d*\\.?\\d*e?[+-]?\\d*";
    private static final Pattern PATTERN = Pattern.compile(REGEX);

    public boolean isNumber(String s) {
        if (s == null || s.trim().isEmpty())
            return false;

        s = s.trim();

        // make sure only 1 '.' and 1 'e' in string.
        if (PATTERN.matcher(s).matches()) {
            char sign = s.charAt(0);
            if (sign == '+' || sign == '-')
                s = s.substring(1);
            
            int dot = s.indexOf(".");
            int e = s.indexOf("e");
            
            if (dot == -1 && e == -1)
                // pure int
                return isPositiveInt(s);
            else if (dot == -1)
                // pure scientific notion integer
                return isScientificInt(s, e);
            else if (e == -1) {
                // real number, no scientific
                return isRealNum(s, dot);
            } else if (dot < e) {
                // combination of fraction and scientific notion
                return isRealNum(s.substring(0, e), dot) && isInt(s.substring(e + 1));
            }
        }
        return false;
    }

    private boolean isScientificInt(String s, int e) {
        return isPositiveInt(s.substring(0, e)) && isInt(s.substring(e + 1));
    }

    private boolean isRealNum(String s, int dot) {
        if (dot == 0)
            return isPositiveInt(s.substring(1));
        else if (dot == s.length() - 1)
            return isPositiveInt(s.substring(0, s.length() - 1));
        else
            return isPositiveInt(s.substring(0, dot)) && isPositiveInt(s.substring(dot + 1));
    }

    private boolean isPositiveInt(String s) {
        if (s == null || s.isEmpty())
            return false;
        
        return s.matches("\\d+");
    }

    private boolean isInt(String s) {
        if (s == null || s.isEmpty())
            return false;

        return s.matches("[+-]?\\d+");
    }

    public static void main(String[] args) {

        String n = "0050e47.6";
        System.out.println(new ValidNumber().isNumber(n));
    }

}
