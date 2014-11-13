package com.leetcode.oj.list;

import com.leetcode.oj.util.ListNode;

/**
 * Sort a linked list in O(n log n) time using constant space complexity.
 * 
 * @author flu
 * 
 */
public class SortLinkedList {

    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) 
            return head;
        
        // how many nodes do we have
        int count = 0;
        ListNode n = head;
        while (n != null) {
            count++;
            n = n.next;
        }

        ListNode left = head;

        int middle = count / 2;
        ListNode s = head;
        while (middle > 1) {
            middle--;
            s = s.next;
        }
        ListNode right = s.next;
        s.next = null;

        ListNode h1 = sortList(left);
        ListNode h2 = sortList(right);

        return merge(h1, h2);
    }

    private ListNode merge(ListNode h1, ListNode h2) {
        ListNode head = new ListNode(Integer.MIN_VALUE);
        ListNode n = head;

        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                n.next = h1;
                h1 = h1.next;
            } else {
                n.next = h2;
                h2 = h2.next;
            }
            n = n.next;
        }

        while (h1 != null) {
            n.next = h1;
            h1 = h1.next;
            n = n.next;
        }

        while (h2 != null) {
            n.next = h2;
            h2 = h2.next;
            n = n.next;
        }

        return head.next;
    }

    public static void main(String[] args) {
        int[] l = new int[] { 3, 1, 5, 9 };

        ListNode n = new ListNode(-1);
        ListNode head = n;
        for (int i : l) {
            ListNode d = new ListNode(i);
            n.next = d;
            n = n.next;
        }

        ListNode sorted = new SortLinkedList().sortList(head.next);
        while (sorted != null) {
            System.out.print(sorted.val + ", ");
            sorted = sorted.next;
        }
    }

}
