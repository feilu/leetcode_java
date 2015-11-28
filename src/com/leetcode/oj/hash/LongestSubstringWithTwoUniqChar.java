package com.leetcode.oj.hash;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithTwoUniqChar {

    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null)
            return 0;
        if (s.length() < 2)
            return s.length();

        Map<Character, Integer> map = new HashMap<>();
        int length = -1;

        int p = 0, q = 1;
        map.put(s.charAt(p), 1);

        while (q < s.length()) {
            char j = s.charAt(q);
            if (map.size() < 2) {
                map.put(j, map.containsKey(j) ? map.get(j) + 1 : 1);
            } else if (map.size() == 2 && map.containsKey(j)) {
                map.put(j, map.get(j) + 1);
            } else {
                map.put(j, 1);
                while (map.size() > 2) {
                    char i = s.charAt(p);
                    if (map.get(i) == 1)
                        map.remove(i);
                    else
                        map.put(i, map.get(i) - 1);
                    p++;
                }

            }

            q++;

            if (map.size() <= 2) {
                length = Math.max(length, q - p); // keep max length
            }
        }

        return length;
    }

    public static void main(String[] args) {
        String s = "aa";
        System.out.println(new LongestSubstringWithTwoUniqChar().lengthOfLongestSubstringTwoDistinct(s));
    }

}
