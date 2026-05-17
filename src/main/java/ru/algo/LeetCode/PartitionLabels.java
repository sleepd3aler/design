package ru.algo.LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PartitionLabels {
    //ababcbaca defegde hijhklij
    public static List<Integer> partitionLabels(String s) {
        Map<Character, Integer> lastInString = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            lastInString.put(chars[i], i);
        }
        System.out.println(lastInString);
        int start = 0;
        int end = 0;
        for (int i = 0; i < chars.length; i++) {
            char current = chars[i];
            end = Math.max(end, lastInString.get(current));
            if (i == end) {
                res.add(end - start + 1);
                start = i + 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
//        partitionLabels("ababcbacadefegdehijhklij");
    }
}
