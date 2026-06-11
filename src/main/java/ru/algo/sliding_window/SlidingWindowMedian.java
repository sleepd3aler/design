package ru.algo.sliding_window;

import java.util.*;

public class SlidingWindowMedian {
    //[1  3  -1] -3  5  3  6  7
    // 10 20 1 2
    //1, 3, -1, -3, 5, 3, 6, 7
    public static double[] medianSlidingWindow(int[] nums, int k) {
        PriorityQueue<Integer> minWindow = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> maxWindow = new PriorityQueue<>();
        List<Double> list = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (minWindow.isEmpty() || minWindow.element() >= nums[i]) {
                minWindow.add(nums[i]);
            } else {
                maxWindow.add(nums[i]);
            }
        }
        rebalance(minWindow, maxWindow);
        addToRes(k, minWindow, maxWindow, list);
        for (int i = k; i < nums.length; i++) {
            if (nums[i - k] <= minWindow.element()) {
                minWindow.remove(nums[i - k]);
            } else {
                maxWindow.remove(nums[i - k]);
            }
            if (minWindow.isEmpty() || minWindow.element() >= nums[i]) {
                minWindow.add(nums[i]);
            } else {
                maxWindow.add(nums[i]);
            }
            rebalance(minWindow, maxWindow);
            addToRes(k, minWindow, maxWindow, list);
        }
        double[] res = new double[list.size()];
        for (int i = 0; i < list.size(); i++) {
            res[i] = list.get(i);
        }
        return res;
    }

    private static void rebalance(PriorityQueue<Integer> minWindow, PriorityQueue<Integer> maxWindow) {
        if (minWindow.size() > maxWindow.size() + 1) {
            maxWindow.add(minWindow.poll());
        } else if (maxWindow.size() > minWindow.size()) {
            minWindow.add(maxWindow.poll());
        }
    }

    private static void addToRes(int k, PriorityQueue<Integer> minWindow, PriorityQueue<Integer> maxWindow, List<Double> list) {
        if (k % 2 == 0) {
            list.add(((long) minWindow.element() + maxWindow.element()) / 2.0);
        } else {
            list.add((double) minWindow.element());
        }
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(medianSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3)));
    }
}
