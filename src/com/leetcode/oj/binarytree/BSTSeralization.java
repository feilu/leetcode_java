package com.leetcode.oj.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

/*
 * http://leetcode.com/2010/09/saving-binary-search-tree-to-file.html
 * 
 * This is BST not BT serialization.
 * 
 * In order traversal.
 */
public class BSTSeralization {

    public String serialize(TreeNode root) {

        StringBuilder res = new StringBuilder();
        serializeRec(root, res);
        return res.toString().trim();
    }

    private void serializeRec(TreeNode root, StringBuilder res) {
        if (root == null)
            return;

        res.append(root.val + " ");
        serializeRec(root.left, res);
        serializeRec(root.right, res);
    }

    public TreeNode deserialize(String s) {
        if (s == null || s.isEmpty())
            return null;

        String[] nodes = s.split(" ");
        Deque<Integer> queue = new ArrayDeque<>();
        for (String n : nodes)
            queue.offer(new Integer(n));

        return deserializeSec(queue, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode deserializeSec(Deque<Integer> queue, int min, int max) {
        if (queue.size() == 0)
            return null;
        
        int v = queue.peek();
        if (min <= v && v <= max) {
            queue.poll(); // dequeue
            TreeNode n = new TreeNode(v);
            n.left = deserializeSec(queue, min, v);
            n.right = deserializeSec(queue, v, max);
            return n;
        }
        return null;
    }

    public static void main(String[] args) {
        TreeNode n = new TreeNode(3);
        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(5);
        TreeNode c = new TreeNode(1);
        TreeNode d = new TreeNode(11);

        n.left = a;
        n.right = b;
        a.left = c;
        b.right = d;

        BSTSeralization helper = new BSTSeralization();

        String s = helper.serialize(n);
        System.out.println(s);

        helper.deserialize(s);


    }

}
