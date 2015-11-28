package com.leetcode.oj.recursion;


// https://oj.leetcode.com/problems/reorder-list/
public class ReorderList {

    public void reorderList(ListNode head) {
        ListNode mid = head;
        ListNode f = head;
        while (f.next != null && f.next.next != null) {
            mid = mid.next;
            f = f.next.next;
        }

        // s is now mid point. now reverse mid point from here
        f = mid.next;
        mid.next = null;

        ListNode secHead = reverseList(f);
        f = head;
        while (secHead != null) {
            ListNode tmp = f.next;
            ListNode tmp2 = secHead.next;
            f.next = secHead;
            secHead.next = tmp;
            f = tmp;
            secHead = tmp2;
        }
    }

    private ListNode reverseList(ListNode head) {
        ListNode current = head;
        ListNode next;
        ListNode prev = null;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode w = new ListNode(1);
        ListNode h = w;
        for (int i = 2; i <= 6; i++) {
            ListNode t = new ListNode(i);
            h.next = t;
            h = h.next;
        }

        new ReorderList().reorderList(w);
        while (w != null) {
            System.out.print(w.val + "->");
            w = w.next;
        }

    }

}
