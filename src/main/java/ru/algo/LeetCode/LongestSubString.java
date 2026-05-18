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
        int max = 0;
        for (int start = 0; start < s.length(); start++) {
            Set<Character> chars = new HashSet<>();
            for (int end = start; end < s.length(); end++) {
                char current = s.charAt(end);
                if (chars.contains(current)) {
                    break;
                } else {
                    chars.add(current);
                    max = Math.max(max, end - start + 1);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("bbbb"));
    }
}
