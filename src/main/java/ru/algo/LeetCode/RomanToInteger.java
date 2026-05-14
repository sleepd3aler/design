package ru.algo.LeetCode;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    private static final Map<Character, Integer> ROMANIAN_VALUES = new HashMap<>();
    static char[] romanian;

    public static int romanToInteger(String s) {
        int sum = 0;
        // MCMXCIV
        initRomanianTable();
        romanian = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            int next = i + 1;
            int first = convertToArabian(romanian[i]);
            int second = next < s.length() ? convertToArabian(romanian[next]) : 0;
            if (first > second) {
                sum += first;
                continue;
            }
            if (first < second) {
                sum += second - first;
                i++;
            } else {
                sum += first;
            }
        }
        return sum;
    }

    public static int romanToInt(String s) {
        int sum = 0;
        // MCMXCIV
        initRomanianTable();
        romanian = s.toCharArray();
        for (int i = romanian.length - 1; i >= 0; i--) {
            int first = convertToArabian(romanian[i]);
            if (i == 0) {
                sum += first;
                break;
            }
            int next = i - 1;
            int second = convertToArabian(romanian[next]);
            if (first < second) {
                sum += first;
            }
            if (first == second) {
                sum += first;
            }
            if (first > second) {
                sum += first - next;
                i--;
            }
        }
        return sum;
    }

    private static int convertToArabian(char ch) {
        return ROMANIAN_VALUES.get(ch);
    }

    private static void initRomanianTable() {
        ROMANIAN_VALUES.put('I', 1);
        ROMANIAN_VALUES.put('V', 5);
        ROMANIAN_VALUES.put('X', 10);
        ROMANIAN_VALUES.put('L', 50);
        ROMANIAN_VALUES.put('C', 100);
        ROMANIAN_VALUES.put('D', 500);
        ROMANIAN_VALUES.put('M', 1000);
    }

    public static void main(String[] args) {
        int res = romanToInt("III");
        int res2 = romanToInteger("MCMXCIV");
        System.out.println(res2);
        System.out.println(res);
    }
}
