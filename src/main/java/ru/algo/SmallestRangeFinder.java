package ru.algo;

import java.util.Arrays;

public class SmallestRangeFinder {
    /**
     * Добавьте поля класса здесь, если это необходимо
     */
    private static final int[] smallestRange = new int[2];

    public static int[] findSmallestRange(int[] nums, int k) {
        int left = 0;
        int right = 1;
        int counter = 0;
        int start = 0;
        int end;
        while (left < nums.length) {
            if (right == nums.length) {
                return null;
            }
            if (nums[left] != nums[right]) {
                counter++;
            } else {
                counter = 1;
                start = right;
            }
            if (counter == k) {
                end = start + k - 1;
                smallestRange[0] = start;
                smallestRange[1] = end;
                break;
            }
            left++;
            right++;

    }
        return smallestRange;
}

public static void main(String[] args) {
    int[] nums = {1, 2, 3, 3, 3};
    int k = 3;
    int[] result = findSmallestRange(nums, k);
    if (result != null) {
        System.out.println("Наименьший диапазон с " + k + " различными элементами: " + Arrays.toString(result));
    } else {
        System.out.println("Такой диапазон не существует.");
    }
}
}
