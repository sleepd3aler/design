package ru.algo.LeetCode;

public class Palindrome {
    public static boolean isPalindrome(String s) {
        String copy = s.toLowerCase().trim().replaceAll("[^a-z,A-Z]", "");

        boolean res = false;
        for (int i = 0, j = copy.length() - 1; i < copy.length() && j > 0; i++, j--) {
            res = copy.charAt(i) == copy.charAt(j);
        }
        return res;
    }

    public static void main(String[] args) {
        boolean res = isPalindrome("A man, a plan, a canal: Panama");
        System.out.println(res);
    }
}
