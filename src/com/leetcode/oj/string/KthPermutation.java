package com.leetcode.oj.string;

public class KthPermutation {

    public String getPermutation(int n, int k) {
        if (n < 1 || n > 9)
            throw new IllegalArgumentException("invalid n " + n);

        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= n; i++)
            s.append(i);

        // get k-1 permutation
        while (k-- > 1) {
            s = getNextPerm(s);
            if (s == null)
                break;
        }
        return s.toString();
    }

    private StringBuilder getNextPerm(StringBuilder s) {
        int n = s.length();

        // find the highest index i such that s[i] < s[i+1]
        int i = n;
        for (int t = 0; t < n - 1; t++) {
            if (s.charAt(t) < s.charAt(t + 1))
                i = t;
        }

        // no such i, return null;
        if (i == n)
            return null;

        // find highest index j such that s[i] < s[j]. j exists as at least it's i+1
        int j = 0;
        for (int t = i + 1; t < n; t++) {
            if (s.charAt(i) < s.charAt(t))
                j = t;
        }

        // swap s[i], s[j]
        char tmp = s.charAt(i);
        s.setCharAt(i, s.charAt(j));
        s.setCharAt(j, tmp);

        // now reverse char from i+1
        StringBuilder res = new StringBuilder(s.substring(0, i + 1)).append(new StringBuilder(s.substring(i + 1))
                        .reverse());
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new KthPermutation().getPermutation(9, 331987));

    }

}
