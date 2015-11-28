package com.leetcode.oj.dp;

import java.util.ArrayList;
import java.util.List;

/*
 * http://www.cnblogs.com/springfor/p/3887908.html
 */
public class Triagle {

    /*
     * Dynamic programing. dp[i][j] = min(dp[i+1][j], dp[i+1][j+1]) + triangle[i][j]
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle.size() == 1)
            return triangle.get(0).get(0);

        List<Integer> dp = new ArrayList<>();

        int num = triangle.size();
        for (int i : triangle.get(num - 1)) {
            dp.add(i);
        }

        for (int i = num - 2; i >= 0; i--) {
            List<Integer> arr = triangle.get(i);
            for (int j = 0; j < arr.size(); j++) {
                dp.set(j, Math.min(dp.get(j), dp.get(j + 1)) + arr.get(j));
            }
        }

        return dp.get(0);
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
