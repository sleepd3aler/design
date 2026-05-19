package ru.algo.LeetCode;

public class Palindrome {
    public static boolean isPalindrome(String s) {
        String copy = s.toLowerCase().trim().replaceAll("[^a-zA-Z]", "");
        System.out.println(copy);
        for (int i = 0, j = copy.length() - 1; i < copy.length() && j > 0; i++, j--) {
            if (copy.charAt(i) != copy.charAt(j)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        boolean res = isPalindrome("A man, a plan, a canal: Panama");
        System.out.println(res);
    }
}
