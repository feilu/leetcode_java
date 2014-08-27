package com.leetcode.oj.binarytree;

/*
 * https://oj.leetcode.com/problems/symmetric-tree/
 * 
 * Iterative solution would be print tree by level, each level should be a palindrome string.
 */
public class IsBinaryTreeSymetric {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;

        return recursive(root.left, root.right);
    }

    private boolean recursive(TreeNode left, TreeNode right) {
        if (left == null) {
            if (right == null)
                return true;
            else
                return false;
        } else if (right == null) {
            return false;
        }

        return (left.val == right.val) && recursive(left.right, right.left) && recursive(left.left, right.right);
    }
}
