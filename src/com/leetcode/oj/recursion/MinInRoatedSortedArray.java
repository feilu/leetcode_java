package com.leetcode.oj.recursion;

/**
 * Suppose a sorted array is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Find the minimum element.
 * 
 * You may assume no duplicate exists in the array.
 * 
 * @author flu
 * 
 */
public class MinInRoatedSortedArray {

    public int findMin(int[] num) {
        // already sorted
        if (num[0] < num[num.length - 1])
            return num[0];

        return findMin(num, 0, num.length - 1);
    }

    private int findMin(int[] num, int s, int e) {
        if (s == e)
            return num[s];

        if (s + 1 == e)
            return Math.min(num[s], num[e]);

        int mid = s + (e - s) / 2;

        // found rotation point if n[m] > n[m+1]
        if (num[mid] > num[mid + 1]) {
            return num[mid + 1];
        } else {
            // n[m] < n[m+1].
            if (num[s] < num[mid]) { // this is the important part!
                // left part is sorted, answer is in right hand side
                return findMin(num, mid, e);
            } else
                return findMin(num, s, mid);
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] i = new int[] { 3, 1, 2 };
        System.out.println(new MinInRoatedSortedArray().findMin(i));
    }

}
