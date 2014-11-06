package com.leetcode.oj.other;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array and a range [a, b]. Find all the missing numbers in the form of number range. e.g.
 * 
 * range = [1, 100], input [2, 3, 7, 33 50] output: [1, 4-6, 8-32, 34-49, 51-100].
 * 
 * Use the most efficient way when possible.
 * 
 * @author flu
 * 
 */
public class MissingNumber {

    public List<String> missingNumber(int[] a, int min, int max) {
        // validate a is sorted, empty, a[0] >= min, a[a.size-1] <= max, min <=max etc.
        // validateInput(a, min, max);

        List<String> res = new ArrayList<>();

        int start = min;
        for (int i : a) {
            if (i > start) {
                // i > start. it's impossible that i < start
                if (i - start == 1)
                    res.add(Integer.toString(start));
                else
                    res.add(new StringBuilder().append(start).append("-").append(i - 1).toString());

            }
            start = i + 1;
        }

        // last element
        if (start <= max) {
            if (max - start == 0)
                res.add(Integer.toString(max));
            else
                res.add(new StringBuilder().append(start).append("-").append(max).toString());
        }

        return res;
    }

    /*
     * n = 1..100 test case [1], [2], [1, 2], [98], [99], [100], [99, 100], [2, 3, 7, 33, 50]
     */
    public static void main(String[] args) {
        int min = 1, max = 100;
        int[] array = { 100 };

        System.out.println(new MissingNumber().missingNumber(array, min, max));
    }
}
