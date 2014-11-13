package com.leetcode.stack;

import java.util.LinkedList;

class MinStack {
    LinkedList<Integer> stack = new LinkedList<>();
    LinkedList<Integer> minStack = new LinkedList<>();
    
    public void push(int x) {
        stack.add(x);
        if (minStack.size() == 0 || minStack.getLast() >= x) {
            minStack.add(x);
        }
    }

    public void pop() {
        int i = stack.pollLast();
        if (i == minStack.getLast())
            minStack.pollLast();
    }

    public int top() {
        return stack.getLast();
    }

    public int getMin() {
        return minStack.getLast();
    }

    public static void main(String[] args) {
        MinStack s = new MinStack();
        s.push(2147483646);
        s.push(2147483646);
        s.push(2147483647);

        System.out.println(s.top());
        s.pop();
        System.out.println(s.getMin());
        s.pop();
        System.out.println(s.getMin());
        s.pop();

        s.push(2147483647);

        System.out.println(s.top());
        System.out.println(s.getMin());

        s.push(-2147483648);

        System.out.println(s.top());
        System.out.println(s.getMin());
        s.pop();
        System.out.println(s.getMin());
    }
}
