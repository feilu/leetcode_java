package com.leetcode.oj.dp;

/**
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * 
 * For example, given the array [2,3,-2,4],
 * 
 * the contiguous subarray [2,3] has the largest product = 6.
 * 
 * @author flu
 * 
 */
public class MaxProductSubarray {

    public int maxProduct(int[] a) {
        if (a == null || a.length == 0)
            throw new IllegalArgumentException("invalid input");

        if (a.length == 1)
            return a[0];

        int res = a[0], max = a[0], min = a[0];
        for (int i = 1; i < a.length; i++) {
            int tmpMax = max, tmpMin = min;
            max = Math.max(Math.max(tmpMax * a[i], tmpMin * a[i]), a[i]);
            min = Math.min(Math.min(tmpMin * a[i], tmpMax * a[i]), a[i]);
            res = Math.max(res, max);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] a = new int[] { -4, -3 };
        System.out.println(new MaxProductSubarray().maxProduct(a));
    }

}
