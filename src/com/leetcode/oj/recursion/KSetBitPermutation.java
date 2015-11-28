package com.leetcode.oj.recursion;

import java.util.ArrayList;
import java.util.List;

// output all n-bit binary number with k set bits.
public class KSetBitPermutation {

    public List<String> permRec(int n, int k) {
        List<String> res = new ArrayList<>();

        if (n < k)
            return res;

        if (k == 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++)
                sb.append('0');
            res.add(sb.toString());
            return res;
        }

        List<String> tmp = permRec(n - 1, k - 1);
        StringBuilder sb = new StringBuilder("1");
        for (String s : tmp) {
            res.add(sb.append(s).toString());
            sb.delete(1, sb.length());
        }

        tmp = permRec(n - 1, k);
        sb = new StringBuilder("0");
        for (String s : tmp) {
            res.add(sb.append(s).toString());
            sb.delete(1, sb.length());
        }
        return res;
    }

    public static void main(String[] args) {
        List<String> perm = new KSetBitPermutation().permRec(5, 2);
        System.out.println(perm);
    }

}
