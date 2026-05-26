package ru.algo.LeetCode;

public class VersionControl {
    static int version = 2;

    public static boolean isBadVersion(int n) {
        return n >= version;
    }

    static class FirstBadVersion extends VersionControl {
        // 1 2 3 4 5 6 t = 4
        public static int firstBadVersion(int n) {
            int start = 1;
            int end = n;
            int mid = (start + end) / 2;
            while (start < end) {
                if (isBadVersion(mid)) {
                    end = mid;
                } else {
                    start = mid + 1;
                }
                mid = (start + end) / 2;
            }
            return start;
        }
    }

    public static void main(String[] args) {
        System.out.println(FirstBadVersion.firstBadVersion(6));

    }
}
