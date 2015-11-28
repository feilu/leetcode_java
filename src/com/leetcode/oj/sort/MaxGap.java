package com.leetcode.oj.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://oj.leetcode.com/problems/maximum-gap/
 * 
 * @author flu
 * 
 */
public class MaxGap {

    public int maximumGap(int[] num) {
		List<Integer> list = radixSort(num);
		int maxGap = 0;
        for (int i = 1; i < list.size(); i++) {
			maxGap = Math.max(maxGap, list.get(i) - list.get(i-1));
		}
		return maxGap;
	}

	private List<Integer> radixSort(int[] array) {
		int max = Integer.MIN_VALUE;

		List<Integer> tmp = new ArrayList<>();
		// find max
		for (int i : array) {
			max = Math.max(i, max);
			tmp.add(i);
		}

		for (int i = 1; max / i > 0; i *= 10) {
			tmp = countSort(tmp, i);
		}

		return tmp;
	}

	private List<Integer> countSort(List<Integer> array, int index) {
		Map<Integer, List<Integer>> map = new HashMap<>();

		// record how many number in each bucket 0-9;
		for (int i : array) {
			int pos = (i / index) % 10;
			if (!map.containsKey(pos)) {
				List<Integer> list = new ArrayList<>();
				list.add(i);
				map.put(pos, list);
			} else {
				map.get(pos).add(i);
			}

		}

		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
            if (map.containsKey(i))
                res.addAll(map.get(i));
		}
		return res;
	}

	public static void main(String[] args) {
		int[] num = { 170, 45, 75, 90, 802, 2, 24, 66 };
        System.out.println(new MaxGap().maximumGap(num));

	}

}
