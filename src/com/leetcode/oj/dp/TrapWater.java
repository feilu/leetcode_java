package com.leetcode.oj.dp;

/*
 * https://oj.leetcode.com/problems/trapping-rain-water/
 */
public class TrapWater {

    public int trap(int[] a) {
        if (a == null)
            return 0;

        int n = a.length;
        if (n <= 2)
            return 0;

        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = a[0];
        rightMax[n - 1] = a[n - 1];

        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], a[i]);
            rightMax[n - 1 - i] = Math.max(rightMax[n - i], a[n - 1 - i]);
        }

        int water = 0;
        for (int i = 0; i < n; i++) {
            int tmp = Math.min(leftMax[i], rightMax[i]) - a[i];
            water += tmp > 0 ? tmp : 0;
        }

        return water;
    }

    public static void main(String[] args) {
        int[] a = { 5, 4, 1, 2 };
        System.out.println(new TrapWater().trap(a));

    }

}
