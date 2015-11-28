package com.leetcode.greedy;

/*
 *  https://oj.leetcode.com/problems/jump-game-ii/
 */

public class JumpGame2 {

    public int jump(int[] a) {
        int jump = 0;
        int cover = 0;
        int n = a.length - 1;

        int i = 0;
        while (i <= n) {
            if ((a[i] == 0 && cover < n) || (i > cover))
                return -1; // no where to go, return;

            if (cover >= n)
                return jump;

            cover = Math.max(cover, i + a[i]);

            // greedy, find the furthest jump
            int tmp = 0;
            for (int t = i + 1; t <= cover && t <= n; t++) {
                if (a[t] + t > tmp) {
                    tmp = a[t] + t;
                    i = t;
                }
            }
            jump++;
        }

        return -1;
    }

    public static void main(String[] args) {
        int[] a = { 2, 1 };
        System.out.println(new JumpGame2().jump(a));

    }

}
