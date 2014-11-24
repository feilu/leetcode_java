package com.leetcode.array;

/**
 * https://oj.leetcode.com/problems/first-missing-positive/
 * 
 * Given an unsorted integer array, find the first missing positive integer.
 * 
 * For example,
 * 
 * Given [1,2,0] return 3,
 * 
 * and [3,4,-1,1] return 2.
 * 
 * Your algorithm should run in O(n) time and uses constant space.
 */
public class FirstMissingPositive {

    // keep swapping a[i] to a[a[i]-1], ignore negative or numbers > a.length
    public int firstMissingPositive(int[] a) {
        if (a == null || a.length == 0)
            return 1;

        for (int i = 0; i < a.length; i++) {
            if (a[i] < 1 || a[i] > a.length - 1)
                continue;

            while (a[i] != i + 1 && a[i] > 0 && a[i] < a.length) {
                // a[a[i] - 1] == a[i] already, meaning dupe value exists, skip.
                if (a[a[i] - 1] == a[i])
                    break;

                // swap a[i] to a[a[i]-1]
                int tmp = a[i];
                a[i] = a[a[i] - 1];
                a[tmp - 1] = tmp;
            }
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] != i + 1)
                return i + 1;
        }

        // all numbers are in place, n+1 is the missing number
        return a.length + 1;
    }

    public static void main(String[] args) {
        int[] a = { 1, 2, 3 };
        System.out.println(new FirstMissingPositive().firstMissingPositive(a));
    }

}
