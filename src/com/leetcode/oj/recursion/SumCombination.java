package com.leetcode.oj.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * see {@link FactorCombination}.
 * 
 * here instead of factors, use sum
 * 
 * @author flu
 * 
 */
public class SumCombination {

    private void print(int n) {
        if (n < 0)
            return;

        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Integer> cur = new ArrayList<>();

        res = findAll(n, 1, cur, res);

        for (List<Integer> l : res)
            System.out.println(l);
    }

    private List<List<Integer>> findAll(int target, int start, List<Integer> cur, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(cur));
            return res;
        }

        for (int i = start; i <= target; i++) {
            cur.add(i);
            findAll(target - i, i, cur, res);
            cur.remove(cur.size() - 1);
        }

        return res;
    }

    public static void main(String[] args) {
        int a = 5;
        new SumCombination().print(a);
    }

}
