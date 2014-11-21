package com.leetcode.oj.recursion;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 打印一个数的所有乘数组合 不要有重复
 * 
 * 24=2*2*2*3
 * 
 * =2*3*4
 * 
 * =2*12
 * 
 * =3*8
 * 
 * =4*6
 * 
 * @author flu
 * 
 */
public class FactorCombination {

    private void print(int n) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        List<Integer> current = new ArrayList<>();
        getFactor(n, 2, current, result);

        for (List<Integer> l : result)
            System.out.println(l);
    }

    // use current to store temp solution
    private void getFactor(int target, int start, List<Integer> current, List<List<Integer>> result) {
        // termination condition
        if (target == 1) {
            // copy current into a new array and add to result.
            // this is necessary as values in current will be removed in ln 51.
            result.add(new ArrayList<Integer>(current));
            return;
        }

        // begin from start (min num) to avoid duplicated answers, always look for higher number
        for (int i = start; i <= target; i++) {
            if (target % i == 0) {
                current.add(i);
                getFactor(target / i, i, current, result);

                // pop the last item (largest), before the next loop.
                current.remove(current.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int a = 32;
        new FactorCombination().print(a);
    }
}
