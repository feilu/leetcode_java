package com.leetcode.oj.other;

/**
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each
 * of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 
 * Output: 7 -> 0 -> 8
 * 
 * see {@link com.leetcode.oj.recursion.AddTwoNumber} for recursive solution.
 * 
 * @author flu
 * 
 */
public class AddTwoNumber {

    private ListNode head = null;
    private ListNode curr = null;
    private int carry = 0;

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }

        while (l1 != null && l2 != null) {
            int t = l1.val + l2.val + carry;
            if (t >= 10) {
                carry = 1;
                t = t % 10;
            } else {
                carry = 0;
            }

            if (head == null) {
                head = new ListNode(t);
                curr = head;
            } else {
                curr.next = new ListNode(t);
                curr = curr.next;
            }

            l1 = l1.next;
            l2 = l2.next;
        }

        append(l1);
        append(l2);

        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return head;
    }

    private void append(ListNode l) {
        while (l != null) {
            int t = l.val + carry;
            if (t >= 10) {
                carry = 1;
                t = t % 10;
            } else {
                carry = 0;
            }

            if (head == null) {
                head = new ListNode(t);
                curr = head;
            } else {
                curr.next = new ListNode(t);
                curr = curr.next;
            }

            l = l.next;
        }
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(9);
        a.next = new ListNode(9);
        ListNode b = new ListNode(1);

        new AddTwoNumber().addTwoNumbers(a, b);
    }

}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
        next = null;
    }
}