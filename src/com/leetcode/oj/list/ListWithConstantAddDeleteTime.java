package com.leetcode.oj.list;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement a list with constant time to add, get, delete and return result; assuming no dupes, no null item.
 * 
 * @author flu
 * 
 */
public class ListWithConstantAddDeleteTime<T> {

    private Node<T> head;
    private Node<T> tail;   
    private Map<String, Node<T>> map; 
    
    public ListWithConstantAddDeleteTime() {
        head = new Node<>(null);
        tail = new Node<>(null);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }
    
    public T get(T t) {
        return map.get(t).val;
    }
    
    public void add(T t) {
        if (!map.containsKey(t)) {
            Node<T> n = new Node<>(t);
            tail.next = n;
            n.prev = tail;
            tail = tail.next;
        }
    }
    
    public T delete(T t) {
        if (map.containsKey(t)) {
            Node<T> n = map.remove(t);
            // remove n from linked list;
            Node<T> prev = n.prev;
            Node<T> next = n.next;
            prev.next = next;
            next.prev = prev;
            n.prev = null;
            n.next = null;

            return n.val;
        }
        return null;
    }
}

class Node<T> {
    T val;
    Node<T> prev;
    Node<T> next;

    public Node(T t) {
        this.val = t;
    }
}