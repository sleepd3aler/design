package ru.sort;

import java.util.Arrays;

public class Merge {

    public static int[] mergesort(int[] array) {
        int[] result = array;
        int n = array.length;
        if (n > 1) {
            int[] left = mergesort(Arrays.copyOfRange(array, 0, n / 2));
            int[] right = mergesort(Arrays.copyOfRange(array, n / 2, n));
            result = merge(left, right);
        }
        return result;
    }

    private static int[] merge(int[] left, int[] right) {
        Arrays.sort(left);
        Arrays.sort(right);
        int[] res = new int[left.length + right.length];
        int indexLeft = 0;
        int indexRight = 0;
        int i;
        for (i = 0; i < res.length; i++) {
            int leftEl = left[indexLeft];
            int rightEl = right[indexRight];
            if (leftEl <= rightEl) {
                res[i] = leftEl;
                indexLeft++;
            } else {
                res[i] = rightEl;
                indexRight++;
            }
            if (indexLeft == left.length || indexRight == right.length) {
                break;
            }
        }

        for (int j = indexLeft + indexRight; j < res.length; j++) {
            if (indexLeft == left.length) {
                res[j] = right[indexRight++];
            } else {
                res[j] = left[indexLeft++];
            }
        }

        //{10, 4, 6, 4, 8, -13, 2, 3}
        return res;
    }
}
