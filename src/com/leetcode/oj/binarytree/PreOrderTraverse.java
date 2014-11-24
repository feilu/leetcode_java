package com.leetcode.oj.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * https://oj.leetcode.com/problems/binary-tree-preorder-traversal/
 * http://en.wikipedia.org/wiki/Tree_traversal#Depth-first_2
 * 
 * @author flu
 * 
 */
public class PreOrderTraverse {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();

        while (stack.size() > 0 || root != null) {
            if (root != null) {
                // visit root, preorder
                res.add(root.val);

                // push right child to stack
                if (root.right != null)
                    stack.push(root.right);

                // keep going left subtree
                root = root.left;
            } else {
                // node is null, pop one from stack, start visiting right child
                root = stack.pop();
            }
        }
        return res;
    }

}
