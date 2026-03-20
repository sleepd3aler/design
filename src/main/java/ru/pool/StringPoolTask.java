package ru.pool;

public class StringPoolTask {
    public static void main(String[] args) {
        String a = "hello"; // <- Str pool
        String b = "hello"; // <-- Str pool
        String c = "hello"; // <- Str pool
        String e = "he" + "llo"; // str pool
        System.out.println(a == b); // true
        System.out.println(a == c); // true
        System.out.println(a == e); // true
    }
}
