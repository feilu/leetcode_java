package com.leetcode.oj.math;

import java.util.regex.Pattern;

/**
 * Implement atoi to convert a string to an integer. If no valid conversion could be performed, a zero value is
 * returned.
 * 
 * The string can contain additional characters after those that form the integral number, which are ignored and have no
 * effect on the behavior of this function.
 * 
 * @author flu
 * 
 */
public class AtoI {

    private static final Pattern INT_PATTERN = Pattern.compile("[+-]?\\d+.*");

    public int atoi(String str) {
        if (str == null)
            return 0;

        str = str.trim();
        if (!INT_PATTERN.matcher(str).matches())
            return 0;

        int positive = 1;
        if (str.charAt(0) == '-' || str.charAt(0) == '+') {
            positive = str.charAt(0) == '-' ? -1 : 1;
            str = str.substring(1);
        }

        int res = 0;
        for (char c : str.toCharArray()) {
            if (c < '0' || c > '9')
                break;

            int v = (c - '1' + 1);

            if ((Integer.MAX_VALUE - v) / 10 < res) {
                res = positive == 1 ? Integer.MAX_VALUE : Integer.MAX_VALUE + 1;
                break;
            } else
                res = v + res * 10;
        }

        return res * positive;
    }

    public static void main(String[] args) {
        String i = "  -332aa ";

        System.out.println(new AtoI().atoi(i));
    }

}
