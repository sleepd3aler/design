package ru.algo.LeetCode;

import java.util.Arrays;

public class MissingNumber {
    public static int missingNumber(int[] nums) {
        Arrays.sort(nums);
        int start = 0;
        int end = nums.length;
        int mid = (end + start) / 2;
        while (start < end) {
            if (nums[mid] == mid) {
                start = mid + 1;
            } else {
                end = mid;
            }

            mid = (end + start) / 2;
        }
        return start;
    }

    public static void main(String[] args) {
        int res = missingNumber(new int[]{0, 1});
        System.out.println(res);
    }
}
