package com.leetcode.oj.binarytree;

import java.util.LinkedList;
import java.util.Queue;

import com.google.common.base.Strings;

/**
 * Serialize/deserialize a binary tree. http://leetcode.com/2010/09/serializationdeserialization-of-binary.html
 * 
 * @author flu
 * 
 */
public class BinaryTreeSerializationDfs {

    
    public String serializeDfs(TreeNode root) {
        if (root == null) {
            return null;
        }

        StringBuilder res = new StringBuilder();
        serializerRec(root, res);
        return res.toString();
    }

    public TreeNode deserializeDfs(String s) {
        if (Strings.isNullOrEmpty(s)) {
            return null;
        }

        // convert string into linkedlist
        Queue<String> q = new LinkedList<String>();
        for (String c : s.split(" ")) {
            q.add(c.trim());
        }
        return deserializeDfsRec(q);
    }

    private TreeNode deserializeDfsRec(Queue<String> q) {
        String v = q.poll();
        if (v.trim().equals("#")) {
            return null;
        }

        TreeNode n = new TreeNode(Integer.parseInt(v));
        n.left = deserializeDfsRec(q);
        n.right = deserializeDfsRec(q);
        return n;
    }

    /*
     * DFS serialization.
     */
    private void serializerRec(TreeNode root, StringBuilder res) {
        if (root == null) {
            res.append("# ");
            return;
        }

        res.append(root.val + " ");
        serializerRec(root.left, res);
        serializerRec(root.right, res);
    }

    public static void main(String[] args) {
        TreeNode n = new TreeNode(3);
        TreeNode a = new TreeNode(51);
        TreeNode b = new TreeNode(4);
        TreeNode c = new TreeNode(1);
        TreeNode d = new TreeNode(11);

        n.left = a;
        n.right = b;
        a.left = c;
        b.right = d;

        String s = new BinaryTreeSerializationDfs().serializeDfs(n);
        System.out.println(s);

        TreeNode root = new BinaryTreeSerializationDfs().deserializeDfs(s);

        new PrintBinaryTreeByLevel().print(root);
    }

}
