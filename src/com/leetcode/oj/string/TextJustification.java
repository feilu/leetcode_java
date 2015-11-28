package com.leetcode.oj.string;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * https://oj.leetcode.com/problems/text-justification/
 * 
 * @author flu
 * 
 */
public class TextJustification {

    public List<String> fullJustify(String[] words, int len) {
        List<String> res = new ArrayList<>();

        if (words == null || words.length == 0)
            return res;

        int i = 0;
        while (i < words.length) {
            // keep adding string till l reaches len
            int l = 0;
            Queue<String> arr = new ArrayDeque<>();
            while (i < words.length) {
                String str = words[i];
                if (l + str.length() <= len) {
                    i++;
                    arr.add(str);
                    l += str.length();

                    if (l == len) {
                        // l = len, so one space between each word.
                        println(arr, len, arr.size() - 1, i == words.length, res);
                        break;
                    } else
                        l++; // apply extra space for each word, keep going.
                } else {
                    // calculate empty space since we already count 1 space per word.
                    println(arr, len, len - l + arr.size(), i == words.length, res); // doesn't fit, print line
                    break;
                }
            }
            
            // last line
            if (arr.size() > 0)
                println(arr, len, len - l + arr.size(), i == words.length, res);
        }
        return res;
    }

    private void println(Queue<String> strings, int len, int spaceLen, boolean lastLine, List<String> res) {
        StringBuilder s = new StringBuilder();
        if (strings.size() == 1 || lastLine) {
            while (strings.size() > 0)
                s.append(strings.poll()).append(" ");
            while (s.length() < len)
                s.append(" ");

            // trim last empty space in case it goes over.
            if (s.length() > len)
                s.deleteCharAt(s.length() - 1);
        } else {
            while (strings.size() > 0) {
                s.append(strings.peek());

                // if last string, it takes all space left, otherwise divide by # of strings
                int nextSpace = strings.size() == 1 ? spaceLen : (int) Math.ceil((double) spaceLen
                                / (strings.size() - 1));
                for (int i = 0; i < nextSpace; i++)
                    s.append(" ");

                spaceLen -= nextSpace;
                strings.poll(); // remove the string from queue.
            }
        }
        res.add(s.toString());
    }

    public static void main(String[] args) {
        String[] w = { "" };
        List<String> r = new TextJustification().fullJustify(w, 0);
        for (String s : r)
            System.out.println(s);
    }

}
