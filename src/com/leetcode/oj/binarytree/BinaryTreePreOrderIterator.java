package com.leetcode.oj.binarytree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;

public class BinaryTreePreOrderIterator implements Iterator<TreeNode> {
    private Deque<TreeNode> stack;
    private TreeNode current;

    public BinaryTreePreOrderIterator(TreeNode root) {
        this.stack = new ArrayDeque<>();
        this.current = root;
    }

    @Override
    public boolean hasNext() {
        return stack.size() > 0 || current != null;
    }

    @Override
    public TreeNode next() {
        TreeNode node = null;
        while (hasNext()) {
            if (current != null) {
                node = current;

                if (current.right != null)
                    stack.push(current.right);

                current = current.left;
                break;
            } else {
                current = stack.pop();
            }
        }
        return node;
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
        a.right = d;

        BinaryTreePreOrderIterator iter = new BinaryTreePreOrderIterator(n);
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }

}
