package com.leetcode.oj.list;

public class CopyLinkListWithRandomPointer {

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null)
            return null;

        RandomListNode node = head;
        while (node != null) {
            // copy each node and link it to the next of current node.
            RandomListNode tmp = new RandomListNode(node.label);
            tmp.next = node.next;
            node.next = tmp;
            node = tmp.next;
        }

        // copy random pointer to its next
        node = head;
        while (node != null) {
            if (node.random != null)
                node.next.random = node.random.next;

            node = node.next.next;
        }

        // no separate two lists
        node = head;
        RandomListNode root = head.next;
        while (node != null) {
            RandomListNode tmp = node.next;
            node.next = tmp.next;

            // update node in the new list, dont forget.
            if (tmp.next != null)
                tmp.next = tmp.next.next;

            node = node.next;
        }
        return root;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub

    }

}

class RandomListNode {
    int label;
    RandomListNode next, random;

    RandomListNode(int x) {
        this.label = x;
    }
};
