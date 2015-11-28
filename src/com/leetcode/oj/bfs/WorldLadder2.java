package com.leetcode.oj.bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import com.google.common.collect.Sets;

/**
 * https://oj.leetcode.com/problems/word-ladder-ii/
 * 
 * http://blog.csdn.net/whuwangyi/article/details/21611433
 * 
 * @author flu
 * 
 */
public class WorldLadder2 {

    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // should validate start and end.

        Map<String, Node> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();

        Queue<String> queue = new ArrayDeque<>();
        queue.add(start);
        dict.remove(start);

        map.put(start, new Node(1, start));

        while (queue.size() > 0) {
            String str = queue.poll();
            if (str.equals(end)) {
                getPath(end, map, res, new ArrayDeque<String>());
                return res;
            }

            // not found, going one level deeper.
            for (int i = 0; i < str.length(); i++) {
                StringBuilder sb = new StringBuilder(str);
                for (char t = 'a'; t <= 'z'; t++) {
                    sb.setCharAt(i, t);
                    String s = sb.toString();

                    if (!dict.contains(s) || s.equals(str))
                        continue;

                    // s is in dict
                    Node prev = map.get(str);

                    if (map.containsKey(s)) {
                        Node n = map.get(s);

                        // this is how we find the shortest path, if no such check it will
                        // find all paths.
                        // no need to add to queue here as s must be added to queue earlier when it's firstly added to
                        // map
                        if (n.dist == prev.dist + 1)
                            n.prevNodes.add(prev);
                    } else {
                        // add to map
                        Node n = new Node(prev.dist + 1, s);
                        n.prevNodes.add(prev);
                        map.put(s, n);

                        // add to queue. only add it here.
                        queue.offer(s);
                    }
                }
            }
        }
        return res;
    }

    // recursive function to find the path from start to end
    private void getPath(String target, Map<String, Node> map, List<List<String>> res, Deque<String> currPath) {
        if (target == null) {
            List<String> tmp = new ArrayList<String>(currPath);
            res.add(tmp);
            return;
        }

        currPath.addFirst(target);

        Node n = map.get(target);
        if (n.prevNodes.size() > 0) {
            for (Node prev : n.prevNodes) {
                Deque<String> newCurrPath = new ArrayDeque<>(currPath);
                getPath(prev.str, map, res, newCurrPath);
            }
        } else {
            getPath(null, map, res, currPath);
        }
    }

    public static void main(String[] args) {
        String[] s = { "a", "b", "c" };
        Set<String> dict = Sets.newHashSet(s);
        List<List<String>> res = new WorldLadder2().findLadders("a", "c", dict);
        for (List<String> t : res)
            System.out.println(t);
    }

}

class Node {
    int dist;
    String str;
    List<Node> prevNodes;

    public Node(int d, String s) {
        dist = d;
        str = s;
        prevNodes = new ArrayList<>();
    }
}