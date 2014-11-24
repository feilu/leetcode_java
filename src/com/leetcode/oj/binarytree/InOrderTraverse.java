package com.leetcode.oj.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * https://oj.leetcode.com/problems/binary-tree-inorder-traversal/
 * http://en.wikipedia.org/wiki/Tree_traversal#Depth-first_2
 * 
 * @author flu
 * 
 */
public class InOrderTraverse {

    /*
     * http://en.wikipedia.org/wiki/Tree_traversal#Depth-first_2
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (stack.size() > 0 || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                // visit node
                TreeNode node = stack.pop();
                res.add(node.val);

                // go to right subtree
                root = node.right;
            }
        }
        return res;
    }

    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null)
            return res;

        Deque<TreeNode> stack = new ArrayDeque<>();

        while (root != null) {
            stack.push(root);
            root = root.left;
        }

        while (stack.size() > 0) {
            TreeNode n = stack.pop();
            res.add(n.val);

            n = n.right;
            while (n != null) {
                stack.push(n);
                n = n.left;
            }
        }
        return res;
    }
}
