package com.leetcode.oj.hash;


public class SudokuValidation {

    // O(1) space
    public boolean isValidSudoku1(char[][] board) {
        // now check every cell value.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                char c = board[i][j];
                if (c == '.')
                    continue;

                if (!isValid(board, i, j))
                    return false;
            }
        }

        return true;
    }

    private boolean isValid(char[][] board, int i, int j) {
        char v = board[i][j];
        for (int t = 0; t < 9; t++) {
            int row = i / 3 * 3 + t / 3;
            int col = j / 3 * 3 + t % 3;

            if ((t != i && board[t][j] == v) || (t != j && board[i][t] == v)
                            || (row != i && col != j && board[row][col] == v))
                return false;
        }
        return true;
    }
}
