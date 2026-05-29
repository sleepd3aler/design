package ru.algo.hash;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LongestUniqueSubstring {
    // abcbcde      - > bcde
    public static String longestUniqueSubstring(String str) {
        Set<Character> uniqueChars = new LinkedHashSet<>();
        char[] string = str.toCharArray();
        int start = 0;
        for (int end = 0; end < string.length; end++) {
            if (!uniqueChars.contains(string[end])) {
                uniqueChars.add(string[end]);
            } else {
                end--;
                while (start <= end) {
                    uniqueChars.remove(string[start++]);
                }
            }
        }

        return uniqueChars
                .stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }

}
