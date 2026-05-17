package ru.algo.LeetCode;

//Input: s = "abcabcbb"
//pwwkew
// bbbb
//Expected 3
//Output: 3
//Explanation: The answer is "abc", with the length of 3. Note that "bca" and "cab" are also correct answers.

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LongestSubString {
    public static int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        List<Integer> lengths = new ArrayList<>();
        char[] string = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            map.put(string[i], i);
        }
        if (map.size() == 1) {
            return 1;
        }
        int counter = 0;
        for (int i = 0; i < string.length; i++) {
            char current = string[i];
            if (map.get(current) == i) {
                lengths.add(counter);
                counter = 0;
            }
            counter++;
        }
        return lengths.stream().max(Integer::compareTo).orElse(0);
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("bbbbb"));
    }
}
