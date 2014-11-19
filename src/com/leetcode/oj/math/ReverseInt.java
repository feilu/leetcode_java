package com.leetcode.oj.math;

public class ReverseInt {

    public int reverse(int x) {
        if (x == 0)
            return 0;

        boolean neg = false;

        if (x < 0) {
            neg = true;
            x = -x;
        }

        int res = 0;
        while (x > 0) {
            res = res * 10 + x % 10;
            x = x / 10;
        }

        return neg ? -res : res;
    }

    public static void main(String[] args) {
        System.out.println(new ReverseInt().reverse(1563847412));

    }

}
