package com.leetcode.oj.recursion;

/**
 * multiple two int without using muliplication '*'. can only use +, -
 * 
 * @author flu
 * 
 */
public class IntegerMuliplication {

    public int multiply(int a, int b) {
        if (a == 0 || b == 0)
            return 0;

        boolean sign = true;

        if ((a > 0 && b < 0) || (a < 0 && b > 0))
            sign = false;

        int res = recursive(Math.abs(a), Math.abs(b));
        return sign ? res : -res;
    }

    private int recursive(int a, int b) {
        if (b == 1)
            return a;

        int tmp = recursive(a, b / 2);

        if (b % 2 == 0)
            return tmp + tmp;
        else
            return tmp + tmp + a;
    }

    public static void main(String[] args) {
        System.out.println(new IntegerMuliplication().multiply(3, -6));
    }

}
