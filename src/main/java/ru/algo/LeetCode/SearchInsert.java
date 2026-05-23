package ru.algo.LeetCode;

public class SearchInsert {
    public static int searchInsert(int[] nums, int target) {
        int start = nums.length / 2 - 1;
        int mid = nums[start];
        if (target < mid) {
            start = 0;
        }
        for (int i = start; i < nums.length; i++) {
        if (target == nums[i]) {
            return i;
        }
        if (target < nums[i]) {
            return i;
        }

        }
        return nums.length;
    }

    public static void main(String[] args) {
        int res = searchInsert(new int[]{1, 3, 5, 6}, 7);
        System.out.println(res);
    }
}
