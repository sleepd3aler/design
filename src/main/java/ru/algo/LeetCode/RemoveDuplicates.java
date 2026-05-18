package ru.algo.LeetCode;

import java.util.Arrays;

public class RemoveDuplicates {
    // 1 1 2
    // 1 2 output 2

    //0, 0, 1, 1, 1, 2, 2, 3, 3, 4
    //0, 1, 1, 1, , , , , , , , ,
    //5
    public static int removeDuplicates(int[] nums) {
        int start = 0;
        for (int i = start + 1; i < nums.length; i++) {
            if (nums[start] != nums[i]) {
                start++;
                nums[start] = nums[i];
            }

        }
        System.out.println(Arrays.toString(nums));
        return start + 1;
    }

    public static void main(String[] args) {
        int res = removeDuplicates(new int[]{1, 2, 3, 4});
        System.out.println(res);
    }
}
