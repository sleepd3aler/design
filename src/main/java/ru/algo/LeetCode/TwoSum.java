package ru.algo.LeetCode;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        int left = 0;
        int right = 1;
        while (left < nums.length) {
            if (nums[left] + nums[right] == target) {
                res[0] = left;
                res[1] = right;
                return res;
            }
            if (nums[right] < nums[left]) {
                left++;
            }
            right++;
        }
        return res;
    }
}
