package com.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ��һ��Ƕ��list���� {{1 1} 2 {1 1}}��ÿһ��list���Ԫ����ӳ��������͡�
 * 
 * ��� ���ӵĻ��ǣ���1+1��*1 +��������������������������ײ�list�����1��֮ǰ�澭�� ������������1�����
 * 
 * @author flu
 * 
 */
public class NetsedListCalculation {

    // assuming bottom list weight is 1.
    public int sum(String list) {
        char[] chars = list.toCharArray();

        // get max depth of this list
        int depth = getDepth(chars, list);

        Deque<Character> stack = new ArrayDeque<>();
        int sum = 0;
        for (char c : chars) {
            if (c == ' ')
                continue;

            if (c == '{') {
                depth--;
                stack.push(c);
            } else if (c == '}') {
                // pop stack and do calculation
                int tmp = 0;
                while (true) {
                    char next = stack.pop();
                    if (next == '{') {
                        // because depth-- for the first {, so we need to add 1 here.
                        sum += tmp * (depth + 1);
                        break;
                    } else
                        tmp += next - '0';
                }
                depth++; // out of one nested list, increase depth;
            } else {
                stack.push(c);
            }
        }
        return sum;
    }

    // assuming top list weight is 1
    public int sum2(String list) {
        Deque<Character> stack = new ArrayDeque<>();
        char[] chars = list.toCharArray();

        int sum = 0;
        int depth = 0;
        for (char c : chars) {
            if (c == ' ')
                continue;

            if (c == '{') {
                depth++;
                stack.push(c);
            } else if (c == '}') {
                // popping number till {
                int tmp = 0;
                while (true) {
                    char t = stack.pop();
                    if (t == '{') {
                        break;
                    } else
                        tmp += t - '0';
                }
                sum += tmp * depth;
                depth--;
            } else {
                stack.push(c);
            }
        }
        return sum;
    }

    private int getDepth(char[] chars, String list) {
        int maxDepth = 0;
        int tmp = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '{') {
                tmp++;
                maxDepth = tmp > maxDepth ? tmp : maxDepth;
            } else if (chars[i] == '}')
                tmp--;
        }
        return maxDepth;
    }

    public static void main(String[] args) {
        String str = "{{1 1} 3 {{1} 1 1}}";
        System.out.println(new NetsedListCalculation().sum2(str));
    }

}
