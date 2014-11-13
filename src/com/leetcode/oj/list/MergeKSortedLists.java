package com.leetcode.oj.list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import com.leetcode.oj.util.ListNode;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.
 * 
 * @author flu
 * 
 */
public class MergeKSortedLists {

    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0)
            return null;

        Queue<ListNode> heap = new PriorityQueue<ListNode>(lists.size(), new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        ListNode head = new ListNode(-1);
        ListNode n = head;

        // add the first pass of all nodes in the list
        // the rest can handle directly in the heap since these are head nodes of each linked list
        for (ListNode list : lists) {
            if (list != null) {
                heap.offer(list);
            }
        }

        while (heap.size() > 0) {
            ListNode tmp = heap.poll();
            n.next = tmp;
            n = n.next;

            if (tmp.next != null) {
                heap.offer(tmp.next);
            }
        }

        return head.next;
    }

    public static void main(String[] args) {
        int[] l = new int[] { 1, 3, 5, 9 };

        ListNode n = new ListNode(-1);
        ListNode head = n;
        for (int i : l) {
            ListNode d = new ListNode(i);
            n.next = d;
            n = n.next;
        }

        List<ListNode> list = new ArrayList<>();
        list.add(head.next);

        l = new int[] { 1, 5, 7, 10 };

        n = new ListNode(-1);
        head = n;
        for (int i : l) {
            ListNode d = new ListNode(i);
            n.next = d;
            n = n.next;
        }
        list.add(head.next);

        ListNode sorted = new MergeKSortedLists().mergeKLists(list);
        while (sorted != null) {
            System.out.print(sorted.val + ", ");
            sorted = sorted.next;
        }
    }

}
