package com.leetcode.oj.recursion;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * Program an iterator for a List which may include nodes or List
 * 
 * i.e. {0,{1,2}, 3 ,{4,{5, 6}}} Iterator returns 0 - 1 - 2 - 3 - 4 - 5 - 6
 * 
 * @author flu
 * 
 * @param <Object>
 */
@SuppressWarnings("hiding")
public class DeepIterator<Object> implements Iterator<Object> {
    LinkedList<Iterator<Object>> stack;
    Iterator<Object> iter;

    public DeepIterator(List<Object> l) {
        stack = new LinkedList<>();
        iter = l.iterator();
    }

    @Override
    public boolean hasNext() {
        if (iter.hasNext())
            return true;

        if (stack.size() > 0) {
            iter = stack.pollLast();
            return hasNext();
        }

        return false;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Object next() {
        if (!hasNext())
            return null;

        Object next = iter.next();
        if (next instanceof List<?>) {
            // push current iterator to stack
            stack.add(iter);

            // set current iterator to next list's iterator
            iter = ((List<Object>) next).iterator();
            return next();
        } else {
            return next;
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        // {0,{1,2}, 3 ,{4,{5, 6}}}
        List<Integer> a = ImmutableList.of(1, 2);
        List<Integer> b = ImmutableList.of(5, 6);
        List<?> c = ImmutableList.of(4, b);
        List<?> array = ImmutableList.of(0, a, 3, c);
        DeepIterator<?> t = new DeepIterator<>(array);
        while (t.hasNext()) {
            System.out.print(t.next() + ", ");
        }
    }

}
