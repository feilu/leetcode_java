package com.leetcode.oj.binarytree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * https://oj.leetcode.com/problems/binary-tree-postorder-traversal/
 * http://en.wikipedia.org/wiki/Tree_traversal#Depth-first_2
 * 
 */
public class PostOrderTraverse {

    /*
     * Iterative traverse
     */
    public List<Integer> postorderTraversal(TreeNode node) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode lastVisited = null;

        while (stack.size() > 0 || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left; // keep going left
            } else {
                // has no left child, now check the first node in stack
                TreeNode peek = stack.peek();

                // peek has right child, and it has not been visited yet
                // visiting right subtree
                if (peek.right != null && peek.right != lastVisited)
                    node = peek.right;
                else {
                    // has no right child, or right subtree has been visited.
                    // now visit current node
                    TreeNode tmp = stack.pop();
                    res.add(tmp.val);
                    lastVisited = tmp; // update last visited node
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}
