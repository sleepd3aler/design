package ru.algo.LeetCode;

import java.util.HashSet;
import java.util.Set;

public class IntersectionLinkedList {
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        //[4,1,8,4,5]    [5,6,1,8,4,5]

        Set<ListNode> set = new HashSet<>();
        ListNode first = headA;
        ListNode second = headB;
        while (first != null) {
            set.add(first);
            first = first.next;
        }
        while (second != null) {
            if (set.contains(second)) {
                return second;
            }
            second = second.next;
        }
        return null;
    }

    public static class ListNode {
        int value;
        ListNode next;

        public ListNode(int value, ListNode next) {
            this.value = value;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode a1 = new ListNode(4, null);
        ListNode a2 = new ListNode(1, null);
        ListNode a3 = new ListNode(8, null);
        ListNode a4 = new ListNode(4, null);
        ListNode a5 = new ListNode(5, null);
        a1.next = a2;
        a2.next = a3;
        a3.next = a4;
        a4.next = a5;

        ListNode b1 = new ListNode(5, null);
        ListNode b2 = new ListNode(6, null);
        ListNode b3 = new ListNode(1, null);
        b1.next = b2;
        b2.next = b3;
        b3.next = a3;

        ListNode result = getIntersectionNode(a1, b1);
        System.out.println(result == a3);
    }
}


