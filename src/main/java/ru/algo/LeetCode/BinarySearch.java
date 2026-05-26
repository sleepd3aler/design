package ru.algo.LeetCode;

public class BinarySearch {
    public static int search(int[] nums, int target) {
        int start = 0;
        int end = nums.length;
        int mid = (end - start) / 2;

        while (start < end) {
            if (target == nums[start]) {
                return start;
            }
            if (target <= nums[mid]) {
                end = mid;
            }
            if (target > nums[mid]) {
                start = mid + 1;

            }
            mid = (end + start) / 2;
        }
        return -1;
    }

    public static void main(String[] args) {
        int res = search(new int[]{-1, 0, 3, 5, 9, 12}, -1);
        System.out.println(res);
    }
}
