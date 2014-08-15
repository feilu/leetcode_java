package com.leetcode.oj.recursion;


/**
 * https://oj.leetcode.com/problems/n-queens-ii/
 * 
 * @author flu
 * 
 */
public class NQueens2 {

    private int num = 0;

    public int totalNQueens(int n) {
        // a[i] = j means place Q in ith row, jth column.
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = -1;
        }

        nQueenRec(a, 0, n);
        return num;
    }

    private void nQueenRec(int[] a, int i, int n) {
        if (i == n) {
            num++;
            return;
        }

        // check each column
        for (int c = 0; c < n; c++) {
            a[i] = c;
            if (isValid(a, i)) {
                nQueenRec(a, i + 1, n);
            }
        }
    }

    private boolean isValid(int[] a, int i) {
        // check all rows up to ith row
        for (int c = 0; c < i; c++) {
            if (a[c] == a[i] || Math.abs(a[c] - a[i]) == i - c) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
        System.out.println(new NQueens2().totalNQueens(4));
    }
}
