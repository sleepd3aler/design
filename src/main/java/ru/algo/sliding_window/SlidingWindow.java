package ru.algo.sliding_window;

public class SlidingWindow {
    public static int findMaxSum(int[] arr, int k) {
        if (arr.length < k) {
            throw new IllegalArgumentException("Размер окна должен быть меньше или равен длине массива");
        }
        // 1 2 3 4 5 6 7 8 9 0     k = 4
        int maxSum = 0;
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += arr[i];
        }
        maxSum = windowSum;
        for (int i = k; i < arr.length; i++) {
            windowSum += arr[i] - arr[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }
        return  maxSum;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int k = 3;
        System.out.println("Максимальная сумма подмассива длиной " + k + " равна " + findMaxSum(arr, k));
    }
}
