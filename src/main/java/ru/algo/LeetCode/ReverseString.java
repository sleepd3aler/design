package ru.algo.LeetCode;

public class ReverseString {
    public static void reverseString(char[] s) {
        for (int i = 0, j = s.length - 1; i < j; i++, j--) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
        }
    }

    public static void main(String[] args) {
        char[] res = ("asad".toCharArray());
        reverseString(res);
        System.out.println(res);
    }
}
