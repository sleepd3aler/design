package ru.algo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class IntervalMerge {
    //{{1, 2}, {3, 4}, {5, 6}};
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> maxIntervals = new ArrayList<>();
        int[] current = intervals[0]; // 0 2
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= current[1]) {
                current[1] = Math.max(current[1], intervals[i][1]);
            } else {
                maxIntervals.add(current);
                current = intervals[i];
            }
        }
        maxIntervals.add(current);
        return maxIntervals.toArray(new int[maxIntervals.size()][]);
    }

    public static void main(String[] args) {
        IntervalMerge merger = new IntervalMerge();
        int[][] intervals = new int[][]{{1, 4}, {0, 2}, {3, 5}};
        //[[0, 2], [1, 4], [3, 5]]
        int[][] res = merger.merge(intervals);
    }
}
