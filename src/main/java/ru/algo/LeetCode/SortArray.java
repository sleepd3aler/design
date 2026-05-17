package ru.algo.LeetCode;

import java.util.Arrays;

public class SortArray {
    public static int[] sortedSquares(int[] nums) {
        int[] res = new int[nums.length];
        //[-4, -1, 0, 3, 10]
        // 16, 1, 0, 9, 100
        //0,1,9,16,100]
        int left = 0;
        int right = nums.length - 1;
        int end = right;
        while (left <= right) {
            int first = (int) Math.pow(Math.abs(nums[left]), 2);
            int second = (int) Math.pow(Math.abs(nums[right]), 2);
            if (first > second) {
                res[end] = first;
                left++;
            } else {
                res[end] = second;
                right--;
            }
            end--;
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(sortedSquares(new int[]{-4, -1, 0, 3, 10})));
    }
}
