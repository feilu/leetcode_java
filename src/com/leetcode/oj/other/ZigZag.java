package com.leetcode.oj.other;


/**
 * https://oj.leetcode.com/problems/zigzag-conversion/
 * 
 * @author flu
 * 
 */
public class ZigZag {
    public String convert(String s, int nRows) {
        if (s == null || s.isEmpty() || nRows <= 0) {
            return "";
        }
        
        StringBuilder[] list = new StringBuilder[nRows];
        char[] chars = s.toCharArray();

        boolean goDown = true;
        int row = 0;
        for (int i = 0; i < chars.length; i++) {
            StringBuilder b = list[row];
            if (b == null) {
                b = new StringBuilder();
                list[row] = b;
            }
            b.append(chars[i]);

            if (goDown) {
                if (row + 1 == nRows) {
                    row = row - 1 > 0 ? row - 1 : 0;
                    goDown = false;
                } else {
                    row++;
                }
            } else {
                // go up
                if (row == 0) {
                    row = row + 1 < nRows ? row + 1 : 0;
                    goDown = true;
                } else {
                    row--;
                }
            }
        }

        StringBuilder r = new StringBuilder();
        for (StringBuilder b : list) {
            if (b != null) {
                r.append(b);
            }
        }
        return r.toString();
    }

    public static void main(String[] args) {
        System.out.println(new ZigZag().convert("ABCDE", 1));
    }
}
