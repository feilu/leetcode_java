package com.leetcode.greedy;

/**
 * https://oj.leetcode.com/problems/candy/
 * 
 * http://yucoding.blogspot.com/2014/02/leetcode-question-candy.html
 * 
 * @author flu
 * 
 */
public class Candy {

    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 1)
            return ratings == null ? 0 : 1;

        int[] left = new int[ratings.length];
        int[] right = new int[ratings.length];

        left[0] = 1;
        for (int i = 1; i <= ratings.length - 1; i++) {
            if (ratings[i] > ratings[i - 1])
                left[i] = left[i - 1] + 1;
            else
                left[i] = 1;
        }

        right[ratings.length - 1] = 1;
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1])
                right[i] = right[i + 1] + 1;
            else
                right[i] = 1;
        }

        int sum = 0;
        for (int i = 0; i < ratings.length; i++) {
            sum += Math.max(left[i], right[i]);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] c = { 1, 1, 1 };
        System.out.println(new Candy().candy(c));
    }

}
