package com.leetcode.oj.recursion;

import java.util.ArrayList;
import java.util.List;


/**
 * https://oj.leetcode.com/problems/permutations/
 * 
 * this is very similar to {@link com.leetcode.oj.recursion.FactorCombination}
 * 
 * @author flu
 * 
 */
public class Permutation {

    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> current = new ArrayList<>();

        boolean[] visited = new boolean[num.length];

        perm(num, visited, 0, current, result);

        return result;
    }

    private void perm(int[] num, boolean[] visited, int step, List<Integer> current,
                    List<List<Integer>> result) {
        if (step == num.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < num.length; i++) {
            // skip if number is already visited.
            if (!visited[i]) {
                visited[i] = true;
                current.add(num[i]);
                perm(num, visited, step + 1, current, result);
                current.remove(current.size() - 1);
                visited[i] = false;
            }
        }
    }


    public static void main(String[] args) {
        int[] num = { 1, 2, 2 };
        List<List<Integer>> res = new Permutation().permute(num);
        for (List<Integer> tmp : res)
            System.out.println(tmp);

    }

}
