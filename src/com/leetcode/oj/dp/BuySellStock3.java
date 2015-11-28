package com.leetcode.oj.dp;

/**
 * https://oj.leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/
 * http://blog.csdn.net/fightforyourdream/article/details/14503469
 * 
 * @author flu
 * 
 */
public class BuySellStock3 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;

        int n = prices.length;

        // max profit from day [0..i]
        int[] left = new int[n];

        // max profit from day [i..n]
        int[] right = new int[n];

        left[0] = 0;
        right[n - 1] = 0;

        int min = prices[0];
        for (int i = 1; i < n; i++) {
            left[i] = Math.max(left[i - 1], prices[i] - min);
            min = Math.min(min, prices[i]);
        }

        int max = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], max - prices[i]);
            max = Math.max(max, prices[i]);
        }

        int maxProfit = 0;
        for (int i = 0; i < n; i++) {
            maxProfit = Math.max(maxProfit, left[i] + right[i]);
        }

        return maxProfit;
    }
}
