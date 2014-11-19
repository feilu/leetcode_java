package com.leetcode.oj.recursion;

public class SearchInsertPosition {

    public int searchInsert(int[] a, int target) {
        if (a == null || a.length == 0)
            return 0;

        return search(a, target, 0, a.length - 1);
    }

    private int search(int[] a, int target, int start, int end) {
        if (start > end) {
            int t = end;
            if (end < 0)
                t = start;

            if (a[t] < target)
                return t + 1;
            else
                return t;
        }

        int mid = (start + end) / 2;
        if (a[mid] == target)
            return mid;
        else if (a[mid] > target)
            return search(a, target, start, mid - 1);
        else
            return search(a, target, mid + 1, end);
    }

    public static void main(String[] args) {
        int[] p = { 1, 3, 5, 6 };
        System.out.println(new SearchInsertPosition().searchInsert(p, 2));

    }

}
