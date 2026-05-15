package ru.algo.LeetCode;

public class LongestCommonPref {
    public static String longestCommonPrefix(String[] strings) {
        char[] prefix = strings[0].toCharArray(); // flower
        for (int i = 1; i < strings.length; i++) {
            char[] current = strings[i].toCharArray(); // flow
            int matcher = 0;
            for (int j = 0; j < current.length; j++) {
                if (prefix[j] == current[j]) {
                    matcher++;
                } else {
                    break;
                }
            }
            prefix = strings[i].substring(0, matcher).toCharArray();
            if (prefix.length == 0) {
                return "";
            }
        }
        return new String(prefix);
    }

    public static void main(String[] args) {
        String[] strings = {"flower", "flow", "flight"};
        String[] string = {"dog", "racecar", "car"};
        System.out.println(longestCommonPrefix(strings));
    }
}
