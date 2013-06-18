package com.leetcode.oj.recursion;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a 2D board containing 'X' and 'O', capture all regions surrounded by
 * 'X'. A region is captured by flipping all 'O's into 'X's in that surrounded
 * region .
 * 
X X X X
X O O X
X X O X
X O X X
After running your function, the board should be:
X X X X
X X X X
X X X X
X O X X  

DFS all 'O' on edges to find connected 'O' and mark them as '+'. After the scan any 'O' left
should be replaced by 'X' (and return '+' back to 'O').

 * 
 * @author flu
 * 
 */
public class SurroundedRegions {

	public void solve(char[][] board) {		
		if (board == null || board.length < 2 || board[0].length < 2)
			return;		
		
		int m = board.length;
		int n = board[0].length;		
		for (int i = 0; i < m; i++) {
			if (board[i][0] == 'O')
				dfs(board, i, 0); // left 
			if (board[i][n-1] == 'O')
				dfs(board, i, n-1); // right
		}		
		for (int i = 0; i < n; i++) {
			if (board[0][i] == 'O')
				dfs(board, 0, i); // top
			if (board[m-1][i] == 'O')
				dfs(board, m-1, i); // bottom
		}
		
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == '+')
					board[i][j] = 'O';
				else if (board[i][j] == 'O')
					board[i][j] = 'X';
			}
		}
    }			

	// iterative DFS
	private void dfs(char[][] board, int i, int j) {					
		Set<Cordinate> set = new HashSet<Cordinate>();
		set.add(new Cordinate(i, j));
		
		int m = board.length;
		int n = board[0].length;		
		while (set.size() > 0) {
			Cordinate c = set.iterator().next();
			set.remove(c);
			board[c.x][c.y] = '+';
			
			// check neighbors
			if (c.x - 1 >= 0 && board[c.x - 1][c.y] == 'O' && !set.contains(new Cordinate(c.x - 1, c.y)))
				set.add(new Cordinate(c.x - 1, c.y));				
			
			if (c.x + 1 < m && board[c.x + 1][c.y] == 'O' && !set.contains(new Cordinate(c.x + 1, c.y)))
				set.add(new Cordinate(c.x + 1, c.y));				
			
			if (c.y - 1 >= 0 && board[c.x][c.y - 1] == 'O' && !set.contains(new Cordinate(c.x, c.y - 1)))
				set.add(new Cordinate(c.x, c.y - 1));				
			
			if (c.y + 1 < n && board[c.x][c.y + 1] == 'O' && !set.contains(new Cordinate(c.x, c.y + 1)))
				set.add(new Cordinate(c.x, c.y + 1));							
		}		 
	}
	
	private class Cordinate {
		int x, y;
		public Cordinate(int x, int y) {
			this.x = x;
			this.y = y;	
		}
		
		@Override
		public int hashCode() {
			return (x + "-" + y).hashCode();
		}
		
		@Override
		public boolean equals(Object o) {
			if (o == this)
				return true;
			
			if (o instanceof Cordinate) {
				Cordinate c = (Cordinate) o;
				if (this.x == c.x && this.y == c.y) {
					return true;
				}
			}
			return false;
		}
	}
	
	/*// recursive DFS. This won't pass large judge because of stack overflow.
	   private void dfs(char[][] board, int i, int j) {
		board[i][j] = '+';
		if (i-1 >=0 && board[i-1][j] == 'O')
			dfs(board, i-1, j);
		if (i+1 < board.length && board[i+1][j] == 'O')
			dfs(board, i+1, j);
		if (j-1 >=0 && board[i][j-1] == 'O')
			dfs(board, i, j-1);
		if (j+1 < board[0].length && board[i][j+1] == 'O')
			dfs(board, i, j+1);
	}*/
		
	public static void main(String[] args) {		
		String[] s = {"XXXXXXXOXXXXXXXXXXXX","XXXXXXOXXXOXOXOXXXXX","XXXXXXXXXXXOOOXXXXOO","XOXOXXXXOXOXOXXOXXXX","XOXXXXXXOXXOOXXXOXXX","OXXXXXOXXOXOOXXXOXXX","XXXXXXXXOXXXOXOXXXXX","XXXXXOXXXXXOXOXOXOXX","OXXXXXXXOXOXXOXXXOOX","OXOOOXXXXXXOXXXOXOXO","OXXXXXXXXXOOOOXXXXXX","XOXXOXXXXXXXXXXXOXOX","XXXXXXOXOXXOXOXXXXXX","XXXXXXXXXXXOXXXXXXXO","XXOXOXXXOXXXXXXXXXXX","XXXXXXOXXXOXXXOXXOOX","XOXXXXXXXXXXOOXXXXXX","XOOXXXOOXXOXXXOOXXXX","XXXOXXOOOXOOXXXXOOXX","XXXXXXXXOXXXXXXXOXOX"};
		char[][] board = new char[s.length][s[0].length()];
		for (int i=0; i<s.length; i++) {
			board[i] = s[i].toCharArray();
		}
					
		long start = System.currentTimeMillis();
		new SurroundedRegions().solve(board);
		System.out.println("time " + (double)(System.currentTimeMillis() - start)/1000 + " sec");

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}		
}
