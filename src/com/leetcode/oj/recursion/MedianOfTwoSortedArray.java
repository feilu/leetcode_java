package com.leetcode.oj.recursion;

/**
 * There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. The
 * overall run time complexity should be O(log (m+n)).
 * 
 * @author flu
 * 
 */
public class MedianOfTwoSortedArray {

    public double findMedianSortedArrays(int a[], int b[]) {
        if ((a == null || a.length == 0) && (b == null || b.length == 0))
            return 0;

        if (a == null || a.length == 0)
            return b.length % 2 == 1 ? b[b.length / 2] : (b[b.length / 2] + b[b.length / 2 - 1]) / 2.0;

        if (b == null || b.length == 0)
            return findMedianSortedArrays(b, a);

        int m = a.length, n = b.length;
        if (m > n)
            return find(a, b, 0, m - 1);
        else
            return find(b, a, 0, n - 1);

    }

    private double find(int[] a, int[] b, int start, int end) {
        if (start > end)
            return find(b, a, 0, b.length - 1); // median in array b

        int i = (end + start) / 2;
        int j = (a.length + b.length) / 2 - i - 1;

        if (j >= b.length || (j >= 0 && b[j] > a[i]))
            return find(a, b, i + 1, end); // a[i] < mediam
        else if (j < -1 || (j < b.length - 1 && b[j + 1] < a[i])) // smallest possible j is -1, see ln 35
            return find(a, b, start, i - 1);

        if ((a.length + b.length) % 2 == 1)
            return a[i];

        if (j >= 0 && (i == 0 || b[j] > a[i - 1]))
            return (b[j] + a[i]) / 2.0;
        else
            return (a[i - 1] + a[i]) / 2.0;
    }

    public static void main(String[] args) {
        int[] a = new int[] { 3 };
        int[] b = new int[] { 1, 2 };
        System.out.println(new MedianOfTwoSortedArray().findMedianSortedArrays(a, b));
    }
}
