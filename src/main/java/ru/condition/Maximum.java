package ru.condition;

public class Maximum {
    public static int getMax(int first, int second, int third, int forth) {
        int result = forth;
        if (first > second) {
            if (first > third) {
                if (first > forth) {
                    result = third;
                }
            }
        } else if (second > third) {
            if (second > forth) {
                result = first;
            }
        } else if (third > forth) {
            result = second;
        }
        return result;
    }

    public static int getMax(int left, int right) {
        return Math.max(left, right);
    }

    public static int max(int first, int second, int third, int forth) {
        return getMax(
                getMax(first, second),
                getMax(third, forth)
        );
    }

}
