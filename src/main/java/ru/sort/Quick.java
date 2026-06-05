package ru.sort;

import java.util.Arrays;

public class Quick {
    public static void quickSort(int[] sequence) {
        quickSort(sequence, 0, sequence.length - 1);
    }

    private static void quickSort(int[] sequence, int start, int end) {
        if (start >= end) {
            return;
        }
        int h = breakPartition(sequence, start, end);
        quickSort(sequence, start, h - 1);
        quickSort(sequence, h + 1, end);
    }
            // {0, 5, -2, 7, 3, -2};

    private static int breakPartition(int[] sequence, int start, int end) {
        int beginToEnd = start + 1; // 1
        int supportElement = sequence[start]; //  0
        int endToBegin = end; // 5
        while (true) {
            while (beginToEnd < endToBegin && sequence[beginToEnd] < supportElement) {
                beginToEnd++;
            }
            while (sequence[endToBegin] > supportElement) {
                endToBegin--;
            }
            if (beginToEnd >= endToBegin) {
                break;
            }
            swap(sequence, beginToEnd++, endToBegin--);
        }
        swap(sequence, start, endToBegin);
        return endToBegin;
    }

    private static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = {0, 5, -2, 7, 3, -2};
        quickSort(array);
        System.out.println(Arrays.toString(array));
    }
}

