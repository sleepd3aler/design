package ru.algo.LeetCode;

//Input: s = "abcabcbb"
//pwwkew
// bbbb
//Expected 3
//Output: 3
//Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.

import java.util.HashSet;
import java.util.Set;

public class LongestSubString {
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> chars = new HashSet<>();
        char[] string = s.toCharArray();
        int start = 0;
        int end = 1;
        int max = 0;
        while (start < string.length) {
            char current = string[start];
            chars.add(current);
            while (end < string.length) {
                char next = string[end];
                if (chars.contains(next)) {
                    max = Math.max(max, end - start);
                    break;
                } else {
                    chars.add(next);
                }
                end++;
            }
            chars.clear();
            start++;
            end = start + 1;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
}
