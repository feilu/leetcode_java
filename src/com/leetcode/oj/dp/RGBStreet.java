package com.leetcode.oj.dp;

/**
 * http://community.topcoder.com/stat?c=problem_statement&pm=6680
 * 
 * http://learn.hackerearth.com/forum/601/minimum-cost-for-painting-a-row-houses-in-three-different-colors/ 
 * @author flu
 *
 */
public class RGBStreet {

    // Red - 0; Green - 1; Blue - 2;
    private static final int R = 0;
    private static final int G = 1;
    private static final int B = 2;
    
    /**
     * 
     * @param cost
     *            [c][i] - cost of ith house to paint in color c.
     * @return
     */
    public static int estimateCost(int[][] cost) {
        int m = cost.length;
        int n = cost[0].length;
        
        // totalCost[c][i] - total cost till ith house to pain in color c.
        int[][] totalCost = new int[m][n];
        
        // init
        totalCost[R][0] = cost[R][0];
        totalCost[G][0] = cost[G][0];
        totalCost[B][0] = cost[B][0];
        
        for (int i = 1; i < n; i++) {
            totalCost[R][i] = cost[R][i] + Math.min(totalCost[G][i - 1], totalCost[B][i - 1]);
            totalCost[G][i] = cost[G][i] + Math.min(totalCost[R][i - 1], totalCost[B][i - 1]);
            totalCost[B][i] = cost[B][i] + Math.min(totalCost[R][i - 1], totalCost[G][i - 1]);
        }

        int minCost = Math.min(totalCost[R][n - 1], Math.min(totalCost[G][n - 1], totalCost[B][n - 1]));
        return minCost;
    }

    public static void main(String[] args) {
        int[][] c = new int[3][2];
        c[R] = new int[] { 10, 15, 20 };
        c[G] = new int[] { 20, 15, 25 };
        c[B] = new int[] { 50, 1, 60 };

        System.out.println(estimateCost(c));
    }
}
