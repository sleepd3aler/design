package ru.algo.LeetCode;

public class RemoveNode {
    //Input: head = [1,2,3,4,5], n = 2
    //Output: [1,2,3,5]
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode left = head;
        ListNode right = head;
        int counter = 0;

        while (right != null) {
            right = right.next;
            counter++;

            if (counter > n + 1) {
                left = left.next;
            }
        }

        if (counter == n) {
            return head.next;
        }

        left.next = left.next.next;
        return head;
    }

    public static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        ListNode five = new ListNode(5, null);
        ListNode four = new ListNode(4, five);
        ListNode three = new ListNode(3, four);
        ListNode two = new ListNode(2, three);
        ListNode one = new ListNode(1, two);
        removeNthFromEnd(one, 2);

        ListNode f1 = new ListNode(1, null);
        removeNthFromEnd(f1, 1);
    }
}
