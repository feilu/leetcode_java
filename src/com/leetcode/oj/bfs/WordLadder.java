package com.leetcode.oj.bfs;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Sets;

public class WordLadder {
    public int ladderLength(String start, String end, Set<String> dict) {
        if (start.isEmpty() || end.isEmpty() || dict.size() == 0 || start.length() != end.length()) {
            return 0;
        }
        
        start = start.toLowerCase();
        end = end.toLowerCase();
        dict.add(start);
        dict.add(end);

        List<String> queue = new LinkedList<>();
        List<Integer> steps = new LinkedList<>();
        queue.add(start);
        steps.add(1);

        while (queue.size() > 0) {
            String word = queue.remove(0);
            int step = steps.remove(0);

            if (word.equals(end)) {
                return step;
            }

            for (int i = 0; i < word.length(); i++) {
                char[] array = word.toCharArray();
                for (char c = 'a'; c <= 'z'; c++) {
                    array[i] = c;

                    String newWord = new String(array);

                    if (dict.contains(newWord)) {
                        queue.add(newWord);
                        steps.add(step + 1);
                        dict.remove(newWord);
                    }
                }
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        int s = new WordLadder().ladderLength("a", "c", Sets.newHashSet(new String[] { "a", "b", "c" }));
        System.out.println(s);
    }
}
