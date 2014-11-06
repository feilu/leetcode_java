package onlinetest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

/**
 * 从stdin读入一行String，String中同时包括单词和数字，以空格分开。需要将输入进行排序，要求：（1）字母和数字分别顺序排序（2）输入中原本是字母/数字的地方依然是字母/数字。
 * 
 * 比如输入：d 3 c 2 1 a 输出为 a 1 c 2 3 d
 * 
 * 输入：add 6 abb at 输出为 abb 6 add at
 * 
 * @author flu
 * 
 */
public class SortLetterNum {
    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String line = reader.readLine();
            if (line == null || line.trim().isEmpty())
                return;

            String res = mixSort(line);
            System.out.println(res);
        }
    }

    private static String mixSort(String line) {
        if (line == null || line.isEmpty()) {
            return null;
        }
        
        String[] items = line.trim().split(" ");
        boolean[] isNum = new boolean[items.length];
        LinkedList<String> words = new LinkedList<>();
        LinkedList<Integer> nums = new LinkedList<>();
        for (int i = 0; i < items.length; i++) {
            if (items[i].matches("[+-]?\\d+")) {
                isNum[i] = true;
                nums.add(Integer.parseInt(items[i]));
            } else {
                words.add(items[i]);
            }
        }
        
        Collections.sort(words);
        Collections.sort(nums);
        
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < isNum.length; i++) {
            String tmp = isNum[i] ? nums.poll().toString() : words.poll();
            res.append(tmp).append(" ");
        }
        return res.toString().trim();
    }

}
