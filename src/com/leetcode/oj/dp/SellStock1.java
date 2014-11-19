package com.leetcode.oj.dp;

public class SellStock1 {

    /*
     * DP. max[i] is the index of the highest price after ith day. -1 if not exists.
     */
    public int maxProfit(int[] prices) {
        int n = prices.length;

        if (prices == null || n < 2)
            return 0;

        int[] max = new int[n];
        max[n - 1] = -1; // last day
        int profit = 0;

        for (int i = n - 2; i >= 0; i--) {
            if (prices[i] <= prices[i + 1]) {
                max[i] = max[i + 1] > 0 ? max[i + 1] : i + 1;
                profit = Math.max(profit, prices[max[i]] - prices[i]);
            } else {
                int k = max[i + 1];

                // keep looking at right till find the next k;
                while (k > 0 && k <= n - 1 && prices[i] > prices[k]) {
                    k = max[k];
                }

                max[i] = k;
                if (k > 0 && k <= n - 1)
                    profit = Math.max(profit, prices[k] - prices[i]);
            }
        }
        return profit;
    }

    public static void main(String[] args) {
        int[] p = { 4, 2, 1 };
        System.out.println(new SellStock1().maxProfit(p));

    }

}
