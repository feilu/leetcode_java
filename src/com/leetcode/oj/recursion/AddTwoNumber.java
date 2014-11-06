package com.leetcode.oj.recursion;

/**
 * You are given two linked lists representing two non-negative numbers. The digits are stored in reverse order and each
 * of their nodes contain a single digit. Add the two numbers and return it as a linked list.
 * 
 * Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 
 * Output: 7 -> 0 -> 8
 * 
 * see {@link com.leetcode.oj.other.AddTwoNumber} for iterative solution.
 * 
 * @author flu
 * 
 */
public class AddTwoNumber {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return recursive(l1, l2, 0);
    }

    private ListNode recursive(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null)
            return carry > 0 ? new ListNode(carry) : null;

        if (l1 == null)
            return recursive(l2, l1, carry);

        int t = 0;
        if (l2 != null) {
            t = l1.val + l2.val + carry;
        } else {
            t = l1.val + carry;
        }

        if (t >= 10) {
            t = t % 10;
            carry = 1;
        } else {
            carry = 0;
        }

        ListNode res = new ListNode(t);
        res.next = recursive(l1.next, l2 == null ? null : l2.next, carry);
        return res;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(1);
        a.next = new ListNode(8);
        ListNode b = new ListNode(0);

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