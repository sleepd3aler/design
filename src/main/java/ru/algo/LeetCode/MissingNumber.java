package ru.algo.LeetCode;

import java.util.HashSet;
import java.util.Set;

public class MissingNumber {
    public static int missingNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int i = 0; i <= nums.length; i++) {
            if (set.add(i)) {
                return i;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        int res = missingNumber(new int[]{0, 1});
        System.out.println(res);
    }
}
