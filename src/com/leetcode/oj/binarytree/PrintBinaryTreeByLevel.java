package com.leetcode.oj.binarytree;

import java.util.LinkedList;
import java.util.Queue;

public class PrintBinaryTreeByLevel {

    private static final int LINE_BREAK = Integer.MIN_VALUE;
    public void print(TreeNode root) {
        if (root == null) {
            return;
        }

        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.offer(root);
        q.offer(new TreeNode(LINE_BREAK));

        while (q.size() > 0) {
            TreeNode n = q.poll();
            if (n.val == LINE_BREAK) {
                System.out.println();

                // only insert if this is not the last line breaker
                if (q.size() > 0) {
                    q.offer(new TreeNode(LINE_BREAK));
                }
                continue;
            }

            System.out.print(n.val + " ");
            if (n.left != null) {
                q.offer(n.left);
            }

            if (n.right != null) {
                q.offer(n.right);
            }
        }
    }
}
