package ru.algo.LeetCode;

public class Palindrome {
    public static boolean isPalindrome(String s) {

//        System.out.println(copy);
        int left = 0;
        int right = s.length() - 1;
        while (left < s.length()) {
            char first = s.toLowerCase().charAt(left);
            char second = s.toLowerCase().charAt(right);
            if (!Character.isLetter(first)) {
                left++;
                continue;
            }
            if (!Character.isLetter(second)) {
                right--;
                continue;
            }
            if ((Character.isLetter(first) && Character.isLetter(second)) && first == second) {
                left++;
                right--;

            } else {
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
