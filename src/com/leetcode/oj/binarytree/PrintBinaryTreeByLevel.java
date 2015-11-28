package com.leetcode.oj.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class PrintBinaryTreeByLevel {

    public void print(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);

        while (q.size() > 0) {
            int num = q.size();
            for (int i = 0; i < num; i++) {
                TreeNode n = q.poll();
                System.out.print(n.val + " ");
                if (n.left != null) {
                    q.offer(n.left);
                }

                if (n.right != null) {
                    q.offer(n.right);
                }
            }
            System.out.println();
        }
    }
}
