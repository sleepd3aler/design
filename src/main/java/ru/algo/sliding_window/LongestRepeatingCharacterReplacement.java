package ru.algo.sliding_window;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacterReplacement {
    public static int characterReplacement(String s, int k) {
        Map<Character, Integer> symbols = new HashMap<>();
        int maxLength = 0;
        int maxFreq = 0;
        int left = 0;
        int right = 0;

        while (right < s.length()) {
            symbols.put(s.charAt(right), symbols.getOrDefault(s.charAt(right), 0) + 1);

            maxFreq = Math.max(maxFreq, symbols.get(s.charAt(right)));
            if ((right - left + 1) - maxFreq > k) {
                symbols.put(s.charAt(left), symbols.get(s.charAt(left)) - 1);
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }

        return maxLength;
    }

    public static void main(String[] args) {
        System.out.println(characterReplacement("AABABBA", 1)); // Выведет: 4
    }
}
