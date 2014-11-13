package com.leetcode.oj.list;

import com.leetcode.oj.util.ListNode;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
 * 
 * @author flu
 * 
 */
public class LinkedListCycle2 {

    public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null)
            return null;
            
        ListNode fast = head;
        ListNode slow = head;
        
        while (fast != null && slow != null) {
            fast = fast.next;
            slow = slow.next;
            
            if (fast != null && fast.next != null) {
                fast = fast.next;
                if (fast == slow) {
                    // catch up
                    break;
                }
            } else {
                // fast reaches end, no loop, return null;
                return null;
            }
        }
        
        ListNode node = head;
        while (node != fast) {
            node = node.next;
            fast = fast.next;
        }
        return node;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        n1.next = n2;
        n2.next = n3;
        // n2.next = n1;

        System.out.println(new LinkedListCycle2().detectCycle(n1));
    }

}
