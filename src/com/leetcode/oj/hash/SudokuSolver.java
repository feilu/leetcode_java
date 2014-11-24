package com.leetcode.oj.hash;

import java.util.ArrayList;
import java.util.List;

public class SudokuSolver {

    public void solveSudoku(char[][] board) {
        List<Integer> empty = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.')
                    empty.add(i * 9 + j); // save empty cell coordinates
            }
        }

        dfs(empty, board, 0);
    }

    private boolean dfs(List<Integer> empty, char[][] board, int current) {
        // all empty cells are filled.
        if (current == empty.size())
            return true;

        int row = empty.get(current) / 9;
        int col = empty.get(current) % 9;

        for (char i = '1'; i <= '9'; i++) {
            // set [row, col] to i and validate current board, then do it recursively.
            board[row][col] = i;
            if (isValid(board, row, col) && dfs(empty, board, current + 1))
                return true;
            else
                board[row][col] = '.'; // no good, set it back to empty and try another value.
        }
        return false;
    }

    // valid board after adding value in [i, j]
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
