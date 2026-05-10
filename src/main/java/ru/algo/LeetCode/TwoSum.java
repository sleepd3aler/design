package ru.algo.LeetCode;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> neededNums = new HashMap<>();
        int[] res = new int[2];
        int left = 0;
        while (left < nums.length) {
            int needed = target - nums[left];
            if (neededNums.get(needed) != null) {
                res[0] = neededNums.get(needed);
                res[1] = left;
                return res;
            } else {
                neededNums.put(nums[left], left);
            }
            left++;
        }
        return null;
    }
}
