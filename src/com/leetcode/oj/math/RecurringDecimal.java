package com.leetcode.oj.math;

import java.util.HashMap;
import java.util.Map;

/*
 * https://oj.leetcode.com/problems/fraction-to-recurring-decimal/ 
 */
public class RecurringDecimal {
    public String fractionToDecimal(int n, int d) {
        if (d == 0)
            throw new IllegalArgumentException("cannot be 0");

        boolean isNeg = (n > 0 && d < 0) || (n < 0 && d > 0);

        // math.abs can go overflow for -2147483648. Convert them into long.
        long numerator = Math.abs((long) n);
        long denominator = Math.abs((long) d);

        long remainder = numerator % denominator;
        long quotient = numerator / denominator;

        StringBuilder res = new StringBuilder();
        if (isNeg)
            res.append("-");
        res.append(quotient);

        if (remainder == 0)
            return res.toString();
        
        res.append(".");

        StringBuilder frac = new StringBuilder();
        Map<Long, Integer> prevRemainders = new HashMap<>();

        int pos = 0;
        while (true) {
            prevRemainders.put(remainder, pos);
            remainder *= 10;
            quotient = remainder / denominator;
            remainder = remainder % denominator;

            frac.append(quotient);

            if (remainder == 0)
                break;

            // repeating
            if (prevRemainders.containsKey(remainder)) {
                frac.insert(prevRemainders.get(remainder), "(").append(")");
                break;
            }

            pos++;
        }

        res.append(frac);

        return res.toString();
    }

    public static void main(String[] args) {
        int n = -2147483648;
        int d = 1;

        System.out.println(new RecurringDecimal().fractionToDecimal(n, d));

    }

}
