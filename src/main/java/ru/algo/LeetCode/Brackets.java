package ru.algo.LeetCode;

import java.util.Stack;

public class Brackets {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character ch : s.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(ch);
                continue;
            }
            boolean misMatches = (stack.isEmpty())
                    || (ch == ')' && stack.peek() != '(')
                    || (ch == ']' && stack.peek() != '[')
                    || (ch == '}' && stack.peek() != '{');
            if (misMatches) {
                return false;
            } else {
                stack.pop();
            }
        }
        return stack.isEmpty();
    }
}
