package com.leetcode.oj.dp;

/**
 * https://oj.leetcode.com/problems/maximal-rectangle/
 * 
 * This one also solves max histogram problem in O(n)
 * 
 * @author flu
 * 
 */
public class MaxRectangleInMatrix {
    public int maximalRectangle(char[][] matrix) {
        if (invalidInput(matrix)) {
            return 0;
        }

        int[][] hist = initHistogram(matrix);
        int maxRecArea = 0;

        // go through every histogram and find max rectangle
        for (int i = 0; i < hist.length; i++) {
            int tempMax = findMaxHistogram(hist[i]);
            maxRecArea = tempMax > maxRecArea ? tempMax : maxRecArea;
        }
        return maxRecArea;
    }

    public int findMaxHistogram(int[] histogram) {
        /**
         * use DP to find max histogram.
         * 
         * maxHist = max ( (sr(i) - sl(i) - 1) * hist[i] for i 0..n, where -
         * sr(i): index of the first number on ith right that is smaller than
         * hist[i] - sl(i): index of the first number on ith left that is
         * smaller than hist[i]
         * 
         * To get sl we can use DP: sl(i+1) = sl(i), if hist[i+1] > hist[i],
         * otherwise sl(i+1) = keep checking hist[sl(i)], then hist[sl(sl(i))]
         * till find j such that hist[i+1] > hist[j].
         * 
         * sl(0) = -1; sr(n) = n+1;
         */
        int n = histogram.length;
        int[] sl = new int[n];
        int[] sr = new int[n];
        sl[0] = -1;
        sr[n - 1] = n;

        // get sl
        for (int i = 1; i < n; i++) {
            if (histogram[i] > histogram[i - 1]) {
                sl[i] = i - 1;
            } else {
                int tmp = sl[i - 1];
                while (tmp >= 0 && histogram[tmp] >= histogram[i]) {
                    tmp = sl[tmp];
                }
                sl[i] = tmp;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            if (histogram[i] > histogram[i + 1]) {
                sr[i] = i + 1;
            } else {
                int tmp = sr[i + 1];
                while (tmp < n && histogram[tmp] >= histogram[i]) {
                    tmp = sr[tmp];
                }
                sr[i] = tmp;
            }
        }

        // now get the area;
        int max = 0;
        for (int i = 0; i < n; i++) {
            int tmp = (sr[i] - sl[i] - 1) * histogram[i];
            max = tmp > max ? tmp : max;
        }
        return max;
    }

    private int[][] initHistogram(char[][] matrix) {
        // convert to histogram
        int[][] hist = new int[matrix.length][matrix[0].length];

        // go from all vertical columns
        for (int j = 0; j < matrix[0].length; j++) {
            // init first row
            hist[0][j] = matrix[0][j] == '0' ? 0 : 1;
        }

        // from second row
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                hist[i][j] = 0; // init
                hist[i][j] = matrix[i][j] == '0' ? 0 : hist[i - 1][j] + 1;
            }
        }

        return hist;
    }

    private boolean invalidInput(char[][] matrix) {
        if (matrix == null) {
            return true;
        }

        // should check matrix all item should be '0' or '1'

        return false;
    }

    public static void main(String[] args) {
        // int[] hist = { 3, 3, 4 };
        // System.out.println(new MaxRectangleInMatrix().findMaxHistogram(hist));

        char[][] i = new char[4][3];
        i[0] = new char[] { '1', '1', '1' };
        i[1] = new char[] { '1', '1', '0' };
        i[2] = new char[] { '1', '1', '1' };
        i[3] = new char[] { '1', '1', '1' };

        System.out.println(new MaxRectangleInMatrix().maximalRectangle(i));
    }
}
