package com.leetcode.oj.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition is a
 * palindrome.
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * For example, given s = "aab", Return
 * 
 * [ ["aa","b"], ["a","a","b"] ]
 * 
 * @author flu
 * 
 */
public class PalindromePartitioning {	
	public ArrayList<ArrayList<String>> partition(String s) {
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		if (s == null || s.isEmpty()) {
			return result;
		}

		for (int i = 1; i < s.length(); i++) {
			if (!isPalindrom(s.substring(0, i))) {
				continue;
			}
			ArrayList<ArrayList<String>> subResult = partition(s.substring(i));
			if (subResult != null) {
				for (ArrayList<String> tmp : subResult) {
					ArrayList<String> solution = new ArrayList<String>();
					solution.add(s.substring(0, i));
					solution.addAll(tmp);
					result.add(solution);
				}
			}
		}

		if (isPalindrom(s)) {
			ArrayList<String> solution = new ArrayList<String>();
			solution.add(s);
			result.add(solution);
		}
		return result;
	}

	private boolean isPalindrom(String s) {		
		// Use a hashmap might in theory speed up a bit.
		 int i = 0, j = s.length() - 1; 
		 while (i < j) 
			 if (s.charAt(i++) != s.charAt(j--)) return false; 
		 return true;
	}

	public static void main(String[] args) {
		PalindromePartitioning p = new PalindromePartitioning();
		String s = "aaba";
		List<ArrayList<String>> r = p.partition(s);

		for (List<String> t : r) {
			System.out.println(t);
		}
	}
}
