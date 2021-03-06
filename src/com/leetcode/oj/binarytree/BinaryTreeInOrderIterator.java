package com.leetcode.oj.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class BinaryTreeInOrderIterator implements Iterator<TreeNode> {
    private Deque<TreeNode> stack;
    private TreeNode node;

    public BinaryTreeInOrderIterator(TreeNode root) {
        stack = new ArrayDeque<TreeNode>();
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
                current = stack.pop();
                node = current.right;
                break;
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
        b.left = d;

        BinaryTreeInOrderIterator iter = new BinaryTreeInOrderIterator(n);
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}
