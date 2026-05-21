package ru.algo.LeetCode;

import java.util.HashSet;
import java.util.Set;

public class LinkedListCycle {
    // 3 -> 2 -> 0 -> -4 = true
    public static boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        set.add(head);
        ListNode next = head.next;
        while (next != null) {
            set.add(next);
            next = next.next;
            if (set.contains(next)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode negative = new ListNode(-4, null);
        ListNode zero = new ListNode(0, negative);
        ListNode two = new ListNode(2, zero);
        ListNode three = new ListNode(3, two);
        negative.next = two;
        boolean res = hasCycle(three);
        System.out.println(res);
    }

    public static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }
}
