package com.leetcode.oj.recursion;

/*
 * Print triangle stars without for/while loop.
 * 
 * eg = 4, print
 * 
 * ****
 * ***
 * **
 * *
 * 
 */
public class PrintStarsNoLoop {

    public void print(int n) {
        if (n == 1) {
            System.out.println("*");
            return;
        }

        printLine(n);
        print(n - 1);
    }

    private void printLine(int n) {
        if (n == 0) {
            System.out.println();
            return;
        }

        System.out.print("*");
        printLine(n - 1);
    }

    public static void main(String[] args) {
        new PrintStarsNoLoop().print(4);

    }

}
