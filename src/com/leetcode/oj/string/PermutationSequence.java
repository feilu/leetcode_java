package com.leetcode.oj.string;

/**
 * https://oj.leetcode.com/problems/permutation-sequence/
 * 
 * The set [1,2,3,…,n] contains a total of n! unique permutations.
 * 
 * By listing and labeling all of the permutations in order, We get the following sequence (ie, for n = 3):
 * 
 * "123" "132" "213" "231" "312" "321"
 * 
 * Given n and k, return the kth permutation sequence.
 * 
 * Note: Given n will be between 1 and 9 inclusive.
 * 
 * http://www.cnblogs.com/tenosdoit/p/3721918.html
 * 
 * http://en.wikipedia.org/wiki/Lehmer_code
 * 
 * @author flu
 * 
 */
public class PermutationSequence {

    private static final String STR = "123456789";
    public String getPermutation(int n, int k) {
        int total = factorial(n);
        StringBuilder candidate = new StringBuilder(STR.substring(0, n));

        // 因为数组是从0到n-1的 所以基数从 0到k-1
        k--;
        
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < n; i++) {
            total /= (n - i);
            int digit = k / total;
            res.append(candidate.charAt(digit));

            candidate.deleteCharAt(digit); // remove digit from candidate string

            k -= digit * total;
        }

        return res.toString();
    }
    
    private int factorial(int n) {
        int f = 1;
        for (int i = 1; i <= n; i++)
            f = f * i;
        return f;
    }

    public static void main(String[] args) {
        System.out.println(new PermutationSequence().getPermutation(9, 331987));

    }

}
