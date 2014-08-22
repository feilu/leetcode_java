package com.leetcode.oj.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 * 
 * For example, If n = 4 and k = 2, a solution is:
 * 
 * [ [2,4], [3,4], [2,3], [1,2], [1,3], [1,4], ]
 * 
 * https://oj.leetcode.com/problems/combinations/
 * 
 * c(n,k) = c(n-1, k-1) + c(n-1, k);
 * 
 * c(n, 0) = 1, c(n,1) = n;
 * 
 * @author flu
 * 
 */
public class Combinations {
    public List<List<Integer>> combine(int n, int k) {
        if (n < 0 || k < 0) {
            throw new RuntimeException("invalid input " + n + ", " + k);
        }

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (n == 0 || k == 0 || n < k) {
            List<Integer> array = new ArrayList<>();
            res.add(array);
            return res;
        }

        List<List<Integer>> tmpRes = combine(n - 1, k - 1);
        if (tmpRes != null) {
            for (List<Integer> tmp : tmpRes) {
                tmp.add(n);
                res.add(tmp);
            }
        }

        tmpRes = combine(n - 1, k);
        if (hasResult(tmpRes)) {
            res.addAll(tmpRes);
        }
        return res;
    }
    
    private boolean hasResult(List<List<Integer>> tmpRes) {
        return tmpRes != null && !tmpRes.isEmpty() && !tmpRes.get(0).isEmpty();            
    }

    public static void main(String[] args) {
        List<List<Integer>> res = new Combinations().combine(4, 2);
        for (List<Integer> tmp : res) {
            for (int i : tmp) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }
}
