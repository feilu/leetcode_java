package com.leetcode.oj.dp;

/**
 * https://oj.leetcode.com/problems/decode-ways/
 * 
 * @author flu
 * 
 */
public class DecodeWays {

    /*
     * '0' is a possible input.
     * 
     * num[0..n] = (num[1..n] | num[0] > 0) + (num[2..n]| 1<=s[0,1]<=26)
     * 
     * num[n..n] = 0, if s[n] == 0; 1, otherwise;
     */
    public int numDecodings(String s) {
        if (s == null || s.isEmpty())
            return 0;
        
        int n = s.length();
        int[] num = new int[n + 1];
        num[n] = 1;
        num[n - 1] = s.charAt(n - 1) == '0' ? 0 : 1;
        
        for (int i = n-2; i >=0; i--) {
            // if starts with '0', no decoding.
            if (s.charAt(i) == '0') {
                num[i] = 0;
                continue;
            } else
                num[i] = num[i + 1];

            int tmp = Integer.parseInt(s.substring(i, i + 2));
            if (tmp <= 26 && tmp > 0) {
                num[i] += num[i + 2];
            }
        }
        
        return num[0];
    }

    public static void main(String[] args) {
        String s = "101";
        System.out.println(new DecodeWays().numDecodings(s));
    }

}
