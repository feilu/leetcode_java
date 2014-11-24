package com.leetcode.array;

public class Search2DArray {

    // convert matrix into 1D array, and this array is sorted.
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null)
            return false;

        int row = matrix.length;
        if (row == 0)
            return false;

        int column = matrix[0].length;
        if (column == 0)
            return false;

        int total = row * column;
        return find(target, 0, total - 1, column, matrix);
    }

    private boolean find(int target, int start, int end, int column, int[][] matrix) {
        if (start > end)
            return false;

        int mid = (end - start) / 2 + start;
        int r = mid / column; // get row #
        int c = mid % column; // get column #
        if (matrix[r][c] == target)
            return true;
        else if (matrix[r][c] < target)
            return find(target, mid + 1, end, column, matrix);
        else
            return find(target, start, mid - 1, column, matrix);
    }

    public static void main(String[] args) {
        int[][] m = new int[2][2];
        m[0][0] = 2;
        m[0][1] = 2;
        m[1][0] = 3;
        m[1][1] = 4;
        System.out.println(new Search2DArray().searchMatrix(m, 4));
    }

}
