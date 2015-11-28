package com.leetcode.oj.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//https://oj.leetcode.com/problems/largest-number/ 
public class LargestNumber {

    public String largestNumber(int[] num) {
        List<String> str = new ArrayList<>(num.length);
        for (int i : num)
            str.add(String.valueOf(i));

        Collections.sort(str, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1 + s2).compareTo(s2 + s1);
            }
        });

        if (str.get(str.size() - 1).equals("0"))
            return "0";

        StringBuilder res = new StringBuilder();
        for (int i = str.size() - 1; i > 0; i--)
            res.append(str.get(i));

        return res.toString();
    }

    public static void main(String[] args) {
        Comparator<String> comp = new NumberComparator();

        System.out.println(comp.compare("30", "3"));
    }


}

class NumberComparator implements Comparator<String> {

    @Override
    public int compare(String i1, String i2) {
        return (i1 + i2).compareTo(i2 + i1);
    }
}