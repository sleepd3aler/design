package ru.algo.LeetCode;

import java.util.*;

public class TwoSum {
    public static int[] simpleTwoSum(int[] nums, int target) {
        //{3, 2, 5, 1, 5};
        // 0  1  2  3  4
        // 3  1  0  2  4
        for (int i = 0; i < nums.length; i++) {
            int sum;
            for (int j = i + 1; j < nums.length; j++) {
                sum = nums[i] + nums[j];
                if (sum == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static int[] comparedTwoSum(int[] nums, int target) {
        //{3, 2, 5, 1, 5};
        // 0  1  2  3  4
        // 3  1  0  2  4
        Integer[] indexes = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            indexes[i] = i;
        }
        Arrays.sort(indexes, Comparator.comparingInt(a -> nums[a]));
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int first = nums[indexes[left]];
            int second = nums[indexes[right]];
            int sum = first + second;
            if (sum == target) {
                return new int[]{Math.min(indexes[left], indexes[right]), Math.max(indexes[left], indexes[right])};
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return null;
    }

    public static int[] nodeTwoSum(int[] nums, int target) {
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

    public static int[] mapTwoSum(int[] nums, int target) {
        Map<Integer, Integer> neededNums = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int needed = target - nums[i];
            if (neededNums.get(needed) != null) {
                return new int[]{neededNums.get(needed), i};
            } else {
                neededNums.put(nums[i], i);
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
