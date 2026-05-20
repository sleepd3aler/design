package ru.algo.LeetCode;

public class Palindrome {
    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        s = s.toLowerCase();
        while (left < right) {
            char first = s.charAt(left);
            char second = s.charAt(right);
            if (!Character.isLetterOrDigit(first)) {
                left++;
                continue;
            }
            if (!Character.isLetterOrDigit(second)) {
                right--;
                continue;
            }
            if (first != second) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "A man, a plan, a canal: Panama";
        boolean res = isPalindrome(s);
        System.out.println(res);
        System.out.println(s);
    }
}
