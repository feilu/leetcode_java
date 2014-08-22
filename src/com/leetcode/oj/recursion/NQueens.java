package com.leetcode.oj.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * https://oj.leetcode.com/problems/n-queens/
 * 
 * @author flu
 * 
 */
public class NQueens {

    private List<String[]> res = new ArrayList<>();

    public List<String[]> solveNQueens(int n) {
        // A[i] = j means the queen in ith row is in jth column.
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = -1; // init
        }

        nQueenRec(a, 0, n);
        return res;
    }

    private void nQueenRec(int[] a, int i, int n) {
        if (i == n) {
            addToResult(a);
            return;
        }

        // test each column in ith row
        for (int k = 0; k < n; k++) {
            a[i] = k; // place queen in ith row to column k
            if (isValid(a, i)) {
                nQueenRec(a, i + 1, n);
            }
        }
    }

    // check if placement is correct up to kth row
    private boolean isValid(int[] a, int k) {
        for (int i = 0; i < k; i++) {
            if (a[i] == a[k] || Math.abs(a[i] - a[k]) == k - i) {
                return false;
            }
        }
        return true;
    }

    private void addToResult(int[] a) {
        String[] s = new String[a.length];

        char[] def = new char[a.length];
        for (int i = 0; i < a.length; i++) {
            def[i] = '.';
        }

        for (int i = 0; i < a.length; i++) {
            String t = new String(def);
            char[] array = t.toCharArray();
            array[a[i]] = 'Q';
            t = new String(array);
            s[i] = t;
        }
        res.add(s);
    }

    public static void main(String[] args) {
        List<String[]> res = new NQueens().solveNQueens(4);

        for (String[] t : res) {
            System.out.println("[");
            for (String w : t) {
                System.out.println(w);
            }
            System.out.println("]");
        }
    }
}
