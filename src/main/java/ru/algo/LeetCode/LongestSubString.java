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
        int max = 0;
        for (int start = 0; start < s.length(); start++) {
            char current = s.charAt(start);
            chars.add(current);
            for (int end = start + 1; end < s.length(); end++) {
                char next = s.charAt(end);
                max = Math.max(max, 1);
                if (chars.contains(next)) {
                    break;
                } else {
                    chars.add(next);
                    max = Math.max(max, end - start + 1);
                }
            }
            chars = new HashSet<>();
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
}
