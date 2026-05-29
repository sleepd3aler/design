package ru.algo.LeetCode;

import java.util.ArrayList;
import java.util.List;

public class LongestIncreasingSubsequence {
    // 2 5 3 7 101
    public static int lengthOfLIS(int[] nums) {
        List<Integer> copy = new ArrayList<>();
        for (int num : nums) {
            if (copy.isEmpty()) {
                copy.add(num);
                continue;
            }
            int last = copy.get(copy.size() - 1);
            if (num > last) {
                copy.add(num);
                continue;
            }

            int left = 0;
            int right = copy.size();
            // 2 5   [3]
            if (num <= last) {
                while (left < right) {
                    int mid = (right + left) / 2;
                    if (copy.get(mid) < num) {
                        left = mid + 1;
                    }
                    if (copy.get(mid) >= num) {
                        right = mid;
                    }
                }
                copy.set(right, num);
            }
        }
        return copy.size();
    }

    public static void main(String[] args) {
        int res = lengthOfLIS(new int[]{4, 10, 4, 3, 8, 9});
        // 3 8 9
        System.out.println(res);
    }
}
