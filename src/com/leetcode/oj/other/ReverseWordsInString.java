package com.leetcode.oj.other;

import java.util.LinkedList;

/**
 * Given an input string, reverse the string word by word.
 * 
 * For example,
 * 
 * Given s = "the sky is blue",
 * 
 * return "blue is sky the".
 * 
 * @author flu
 * 
 */
public class ReverseWordsInString {

    public String reverseWords(String s) {
        if (s == null || s.length() == 0)
            return s;

        LinkedList<Character> list = new LinkedList<>();
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                list.add(c);
            } else {
                // dedupe multiple spaces.
                if (list.size() == 0)
                    continue;

                while (list.size() > 0)
                    res.append(list.pollLast());
                res.append(" ");
            }
        }

        while (list.size() > 0)
            res.append(list.pollLast());

        // reverse before return!
        return res.reverse().toString().trim();
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
