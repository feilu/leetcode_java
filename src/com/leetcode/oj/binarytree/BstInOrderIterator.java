package com.leetcode.oj.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BstInOrderIterator implements Iterator<TreeNode> {

    private Deque<TreeNode> stack;

    public BstInOrderIterator(TreeNode root) {
        stack = new ArrayDeque<TreeNode>();
        pushLeft(root);
    }

    @Override
    public boolean hasNext() {
        return stack.size() > 0;
    }

    @Override
    public TreeNode next() {
        if (stack.size() == 0) {
            throw new NoSuchElementException();
        }

        TreeNode node = stack.pop();
        pushLeft(node.right);
        return node;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

    private void pushLeft(TreeNode node) {
        // find left most leaf, store nodes in the middle.
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
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

        BstInOrderIterator iter = new BstInOrderIterator(n);
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
