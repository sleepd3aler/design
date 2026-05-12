package ru.algo.LeetCode;

import java.util.*;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        List<Node<Integer>> copy = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            copy.add(new Node<>(nums[i], i));
        }
        copy.sort(Comparator.comparing(Node::getValue));
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int sum = copy.get(left).getValue() + copy.get(right).getValue();
            if (sum == target) {
                int first = copy.get(left).getIndex();
                int second = copy.get(right).getIndex();
                return new int[]{Math.min(first, second), Math.max(first, second)};
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return null;
    }

    private static class Node<T> {
        T value;

        T index;

        public Node(T value, T index) {
            this.value = value;
            this.index = index;
        }

        public T getValue() {
            return value;
        }

        public T getIndex() {
            return index;
        }
    }
}
