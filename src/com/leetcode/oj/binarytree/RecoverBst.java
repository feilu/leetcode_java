package com.leetcode.oj.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://oj.leetcode.com/problems/recover-binary-search-tree/
 * 
 * @author flu
 * 
 */
public class RecoverBst {

    public void recoverTree(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev = null;
        TreeNode curr = root;

        // in order traverse
        while (curr != null || stack.size() > 0) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                if (prev != null && prev.val > curr.val) {
                    if (first == null) {
                        first = prev;
                    }
                    second = curr; // this needs to be outside of if
                }

                prev = curr;
                curr = curr.right;
            }
        }

        // now swap first and second
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(1);
        new RecoverBst().recoverTree(root);
    }

}
