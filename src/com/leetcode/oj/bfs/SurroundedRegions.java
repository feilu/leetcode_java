package com.leetcode.oj.bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * https://oj.leetcode.com/problems/surrounded-regions/
 * 
 * @author flu
 * 
 */
public class SurroundedRegions {

    public void solve(char[][] board) {
        if (board == null || board.length <= 1 || board[0].length <= 1)
            return;

        int m = board.length;
        int n = board[0].length;
        
        Queue<Point> queue = new ArrayDeque<Point>();

        for (int i = 0; i < m; i++) {
            if (board[i][0] == 'O')
                queue.add(new Point(i, 0));
            if (board[i][n - 1] == 'O')
                queue.add(new Point(i, n - 1));
        }

        for (int j = 1; j < n - 1; j++) {
            if (board[0][j] == 'O')
                queue.add(new Point(0, j));
            if (board[m - 1][j] == 'O')
                queue.add(new Point(m - 1, j));
        }

        while (queue.size() > 0) {
            Point p = queue.poll();
            int x = p.x, y = p.y;
            board[x][y] = '-';
            
            if (x - 1 >= 0 && board[x - 1][y] == 'O')
                queue.add(new Point(x - 1, y));
            if (x + 1 < m && board[x + 1][y] == 'O')
                queue.add(new Point(x + 1, y));
            if (y - 1 >= 0 && board[x][y - 1] == 'O')
                queue.add(new Point(x, y - 1));
            if (y + 1 < n && board[x][y + 1] == 'O')
                queue.add(new Point(x, y + 1));
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == '-')
                    board[i][j] = 'O';
                else if (board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }
    }

    public static void main(String[] args) {
        String[] b = { "XOOOOOOOOOOOOOOOOOOO", "OXOOOOXOOOOOOOOOOOXX", "OOOOOOOOXOOOOOOOOOOX", "OOXOOOOOOOOOOOOOOOXO",
                        "OOOOOXOOOOXOOOOOXOOX", "XOOOXOOOOOXOXOXOXOXO", "OOOOXOOXOOOOOXOOXOOO", "XOOOXXXOXOOOOXXOXOOO",
                        "OOOOOXXXXOOOOXOOXOOO", "XOOOOXOOOOOOXXOOXOOX", "OOOOOOOOOOXOOXOOOXOX", "OOOOXOXOOXXOOOOOXOOO",
                        "XXOOOOOXOOOOOOOOOOOO", "OXOXOOOXOXOOOXOXOXOO", "OOXOOOOOOOXOOOOOXOXO", "XXOOOOOOOOXOXXOOOXOO",
                        "OOXOOOOOOOXOOXOXOXOO", "OOOXOOOOOXXXOOXOOOXO", "OOOOOOOOOOOOOOOOOOOO", "XOOOOXOOOXXOOXOXOXOO" };

        char[][] board = new char[b.length][b[0].length()];
        for (int i = 0; i < b.length; i++) {
            board[i] = b[i].toCharArray();
        }

        new SurroundedRegions().solve(board);

        int m = board.length;
        int n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
