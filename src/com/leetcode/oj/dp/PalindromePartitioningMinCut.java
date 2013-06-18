package com.leetcode.oj.dp;

/**
 * Given a string s, partition s such that every substring of the partition is a
 * palindrome.
 * 
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * 
 * For example, given s = "aab", Return 1 since the palindrome partitioning
 * ["aa","b"] could be produced using 1 cut.
 * 
 * http://www.geeksforgeeks.org/dynamic-programming-set-17-palindrome-
 * partitioning/
 * 
 * let minCut[i] be min cut for s[i, n] (n is length of s). Then
 * 
 * minCut[i] is at most n-i-1 because each char is palindrome by itself. Then
 * minCut[i] = Min { minCut[i], 1 + minCut[j+1]}, if s[i,j] is palindrome for
 * all i <= j < n;
 * 
 * palindrome[i, j] = 1 if i == j; palindrome[i, j] = s[i] == s[j] &&
 * palindrome[i+1, j-1] otherwise;
 * 
 * O(n^2)
 * 
 * @author flu
 * 
 */
public class PalindromePartitioningMinCut {

    public int minCut(String s) {
        int n = s.length();

        int[] minCut = new int[n + 1];

        boolean[][] p = new boolean[n][n];

        for (int i = 0; i <= n; i++) {
            minCut[i] = n - i - 1;
        }

        // starting from n-2 because we already know minCut[n-1] = 0.
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j) && (j < i + 2 || p[i + 1][j - 1])) {
                    p[i][j] = true;
                    minCut[i] = Math.min(minCut[i], 1 + minCut[j + 1]);
                }
            }
        }

        return minCut[0];
    }

    public static void main(String[] args) {
        PalindromePartitioningMinCut p = new PalindromePartitioningMinCut();
        long start = System.currentTimeMillis();
        String s = "leet";
        System.out.println(p.minCut(s));
        System.out.println(System.currentTimeMillis() - start + "ms");
    }
}
