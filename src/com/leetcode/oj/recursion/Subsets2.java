package com.leetcode.oj.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://oj.leetcode.com/problems/subsets-ii/
 * 
 * @author flu
 * 
 */
public class Subsets2 {
    public List<List<Integer>> subsetsWithDupRecur(int[] num) {
        Arrays.sort(num);
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        res.add(new ArrayList<Integer>());
        return subsetsWithDupRecur(res, num, 0, 0);
    }

    private List<List<Integer>> subsetsWithDupRecur(List<List<Integer>> res, int[] num, int n, int lastDeltaStart) {
        if (n >= num.length)
            return res;

        int size = res.size();
        while (lastDeltaStart < size) {
            List<Integer> list = new ArrayList<>();
            list.addAll(res.get(lastDeltaStart));
            list.add(num[n]);
            res.add(list);
            lastDeltaStart++;
        }

        // is next element the same?
        lastDeltaStart = (n < num.length - 1 && num[n] == num[n + 1]) ? size : 0;

        return subsetsWithDupRecur(res, num, ++n, lastDeltaStart);
    }

    // non recursion
    public List<List<Integer>> subsetsWithDup(int[] num) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0) {
            return res;
        }

        // sort num
        Arrays.sort(num);
        List<Integer> first = new ArrayList<>();
        res.add(first);
        first = new ArrayList<>();
        first.add(num[0]);
        res.add(first);

        // store difference between subset(n-1) and subset(n-2)
        List<List<Integer>> lastDelta = new ArrayList<List<Integer>>();
        lastDelta.add(first);
        for (int i = 1; i < num.length; i++) {
            if (num[i] > num[i - 1]) {
                // different num. Add num[i] to every list of current result
                lastDelta = nextNum(res, num[i], false);
            } else {
                // if the same num. Only add num[i] to eveyr list of previous result
                lastDelta = nextNum(lastDelta, num[i], true);
            }
            res.addAll(lastDelta);
        }
        return res;
    }

    private List<List<Integer>> nextNum(List<List<Integer>> lastDelta, int num, boolean isSame) {
        List<List<Integer>> delta = new ArrayList<List<Integer>>();        

        if (!isSame) {
            // add num itself
            List<Integer> tmp = new ArrayList<>();
            tmp.add(num);
            delta.add(tmp);
        }

        for (List<Integer> lst : lastDelta) {
            // hack skip empty list
            if (lst.size() == 0)
                continue;

            List<Integer> newList = new ArrayList<>();
            newList.addAll(lst); // should use guava ImmutableList.copyOf()
            newList.add(num);
            delta.add(newList);
        }
        return delta;
    }

    public static void main(String[] args) {
        int[] num = { 1, 2, 3 };
        System.out.println(new Subsets2().subsetsWithDup(num));

        System.out.println(new Subsets2().subsetsWithDupRecur(num));
    }
}
