package com.leetcode.oj.recursion;

/**
 * https://oj.leetcode.com/problems/partition-list/
 * 
 * @author flu
 * 
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null)
            return head;

        ListNode startLeft = null, left = null;
        ListNode startRight = null, right = null;

        while (head != null) {
            if (head.val < x) {
                if (startLeft == null) {
                    startLeft = new ListNode(head.val);
                    left = startLeft;
                } else {
                    left.next = new ListNode(head.val);
                    left = left.next;
                }
            } else {
                if (startRight == null) {
                    startRight = new ListNode(head.val);
                    right = startRight;
                } else {
                    right.next = new ListNode(head.val);
                    right = right.next;
                }
            }
            head = head.next;
        }

        // combine lists
        if (startLeft != null) {
            left.next = startRight;
            return startLeft;
        } else {
            return startRight;
        }
    }

    private static class ListNode {
        int val;
        ListNode next;

        public ListNode(int x) {
            this.val = x;
        }
    }

    public static void main(String[] args) {
        int[] i = { 1, 3, 5, 2, 2, 0 };
        ListNode list = new ListNode(i[0]);
        ListNode head = list;
        for (int k = 1; k < i.length; k++) {
            list.next = new ListNode(i[k]);
            list = list.next;
        }

        ListNode res = new PartitionList().partition(head, 4);
        while (res != null) {
            System.out.print(res.val + "->");
            res = res.next;
        }
    }
}
