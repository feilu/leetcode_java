package com.leetcode.oj.hash;

import java.util.HashMap;
import java.util.Map;

/**
 * Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following
 * operations: get and set.
 * 
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * set(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it
 * should invalidate the least recently used item before inserting a new item.
 * 
 * @author flu
 * 
 */
public class LRUCache {

    private int capacity;
    private Map<Integer, Node> map;
    private Node head;
    private Node tail;
    private int size;

    public LRUCache(int c) {
        this.capacity = c;
        size = 0;
        map = new HashMap<>();

        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    public synchronized int get(int key) {
        if (map.containsKey(key)) {
            Node n = map.get(key);
            updateList(n);

            return n.v;
        } else
            return -1;
    }

    public synchronized void set(int key, int value) {
        // create a new node
        Node n = new Node(key, value);

        if (map.containsKey(key)) {
            // key exists, get existing node and set new value.
            n = map.get(key);
            n.v = value;
        } else if (size == capacity) {
            // capacity reached, remove least used node.
            Node lr = head.next;
            int k = lr.k;

            // remove from map
            map.remove(k);

            // update linked list
            head.next = lr.next;
            head.next.prev = head;
            lr.next = null;
            lr.prev = null;
            lr = null;
        } else {
            size++;
        }

        map.put(key, n);
        updateList(n);
    }

    private void updateList(Node n) {
        Node prev = n.prev;
        Node next = n.next;

        if (prev != null)
            prev.next = next;

        if (next != null)
            next.prev = prev;

        // move n to tail
        n.next = tail;
        n.prev = tail.prev;
        tail.prev.next = n;
        tail.prev = n;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        System.out.println(cache.get(2));
        cache.set(2, 6);
        System.out.println(cache.get(1));
        cache.set(1, 5);
        cache.set(1, 2);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
    }
}

class Node {
    int k;
    int v;
    Node prev;
    Node next;

    public Node(int k, int v) {
        this.k = k;
        this.v = v;
    }
}
