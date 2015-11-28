package com.leetcode.oj.math;

public class FindCubicRoot {
    private static final double DIFF = 0.00001;

    // newton's method.
    public double curibRoot(int x) {
        double res = x; // start with x

        while (Math.abs(Math.pow(res, 3) - x) > DIFF) {
            res = res - (Math.pow(res, 3) - x) / (3 * Math.pow(res, 2));
        }

        return res;
    }

    public static void main(String[] args) {
        int n = 5;
        System.out.println(new FindCubicRoot().curibRoot(n));
    }

}
