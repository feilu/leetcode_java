package com.leetcode.oj.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class BinaryTreePostOrderIterator implements Iterator<TreeNode> {

    private Deque<TreeNode> stack;
    private TreeNode node;
    private TreeNode lastVisitedNode = null;

    public BinaryTreePostOrderIterator(TreeNode root) {
        stack = new ArrayDeque<>();
        node = root;
    }

    @Override
    public boolean hasNext() {
        return stack.size() > 0 || node != null;
    }

    @Override
    public TreeNode next() {
        TreeNode current = null;
        while (hasNext()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                TreeNode peek = stack.peek();
                if (peek.right != null && peek.right != lastVisitedNode) {
                    // visit right subtree
                    node = peek.right;
                } else {
                    current = stack.pop();
                    lastVisitedNode = current;
                    break;
                }
            }
        }
        return current;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        TreeNode n = new TreeNode(3);
        TreeNode a = new TreeNode(2);
        TreeNode b = new TreeNode(4);
        TreeNode c = new TreeNode(1);
        TreeNode d = new TreeNode(11);

        n.left = a;
        n.right = b;
        a.left = c;
        b.right = d;

        BinaryTreePostOrderIterator iter = new BinaryTreePostOrderIterator(n);
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

    }

}
