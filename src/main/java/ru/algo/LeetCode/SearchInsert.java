package ru.algo.LeetCode;

public class SearchInsert {
    public static int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        int mid = (left + right) / 2;
        int res = 0;
        while (left < right) {
            if (target <= nums[mid]) {
                right = mid;
                res = right;
            }
            if (target > nums[mid]) {
                left = mid + 1;
                res = left;
            }
            mid = (left + right) / 2;
        }
        return res;
    }

    public static void main(String[] args) {
        int res = searchInsert(new int[]{1, 3, 5, 6}, 7);
        System.out.println(res);
    }
}
