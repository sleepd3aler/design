package ru.algo.sliding_window;

import java.util.*;

public class SlidingWindowMaximum {
    //1, 3, -1, -3, 5, 3, 6, 7
    public static int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> maxNums = new ArrayList<>();
        PriorityQueue<Node<Integer>> window = new PriorityQueue<>((a, b) -> Integer.compare(b.value, a.value));

        for (int i = 0; i < k; i++) {
            window.add(new Node<>(nums[i], i));
        }
        maxNums.add(window.peek().value);
        for (int i = k; i < nums.length; i++) {
            while (!window.isEmpty() && window.peek().index < i - k + 1) {
                window.poll();
            }
            window.add(new Node<>(nums[i], i));
            maxNums.add(window.peek().value);

        }
        int[] res = new int[maxNums.size()];
        for (int i = 0; i < maxNums.size(); i++) {
            res[i] = maxNums.get(i);
        }
        return res;
    }

    private static List<Integer> dequeWindow(int[] nums, int k) {
        List<Integer> maxNums = new ArrayList<>();
        ArrayDeque<Integer> window = new ArrayDeque<>();
        for (int i = 0; i < nums.length; i++) {
            while (!window.isEmpty() && nums[window.getLast()] <= nums[i]) {
                window.removeLast();
            }
            window.addLast(i);
            if (window.getFirst() < i - k + 1) {
                window.removeFirst();
            }
            if (i >= k - 1) {
                maxNums.add(nums[window.getFirst()]);
            }
        }
        return maxNums;

    }

    private static class Node<T> {
        int value;
        int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }
}
