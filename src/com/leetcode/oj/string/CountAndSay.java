package com.leetcode.oj.string;

/**
 * https://oj.leetcode.com/problems/count-and-say/
 * 
 * @author flu
 * 
 */
public class CountAndSay {

    public String countAndSay(int n) {
        if (n < 1)
            return null;

        String res = "1";
        int i = 1;
        while (i < n) {
            StringBuilder tmp = new StringBuilder();
            int j = 0;
            while (j < res.length()) {
                char nextChar = res.charAt(j);
                int count = 1;
                while (j + 1 < res.length() && res.charAt(j + 1) == nextChar) {
                    j++;
                    count++;
                }
                tmp.append(count).append(nextChar);
                j++;
            }
            res = tmp.toString();
            i++;
        }

        return res;
    }

    public static void main(String[] args) {
        int n = 4;
        System.out.println(new CountAndSay().countAndSay(n));
    }

}
