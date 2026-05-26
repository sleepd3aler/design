package ru.algo.LeetCode;

import java.util.Arrays;

public class TwoSum2 {
    public static int[] twoSum(int[] nums, int target) {
        int start = 0;
        int end = nums.length;
        while (start < end) {
            int first = nums[start];
            int second = nums[end - 1];
            if (first + second == target) {
                return new int[]{start + 1, end};
            }
            if (first + second > target) {
                end--;
            }
            if (first + second < target) {
                start++;
            }

        }
        return null;
    }

    public static void main(String[] args) {
        int[] res = twoSum(new int[]{3, 24, 50, 79, 88, 150, 345}, 200);
        System.out.println(Arrays.toString(res));
    }
}
