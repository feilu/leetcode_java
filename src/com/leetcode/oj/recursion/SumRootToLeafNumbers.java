package com.leetcode.oj.recursion;


/**
 * https://oj.leetcode.com/problems/sum-root-to-leaf-numbers/
 * 
 * @author flu
 * 
 */
public class SumRootToLeafNumbers {
    public int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;

        return sumRec(root, 0, 0);
    }

    private int sumRec(TreeNode node, int pathSum, int sum) {
        if (node == null) {
            return sum + pathSum;
        }

        pathSum = pathSum * 10 + node.val;

        if (node.left == null && node.right == null) {
            return sum + pathSum;
        }

        if (node.left != null) {
            sum = sumRec(node.left, pathSum, sum);
        }

        if (node.right != null) {
            sum = sumRec(node.right, pathSum, sum);
        }

        return sum;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
    }
}
