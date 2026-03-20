package ru.pool;

public class IntegerPoolTask {
    public static void main(String[] args) {
        Integer a = 100; // int pool in [-128 - 127]
        Integer b = 100; // int pool in [-128 - 127]

        Integer c = 200; // heap -> 200 > 127 ( new Object )
        Integer d = 200; // heap -> 200 > 127 ( new Object )

        Integer e = Integer.valueOf(100); // return from int cache(pool)
        Integer f = new Integer(100); // new Object -> heap

        System.out.println(a == b); // true
        System.out.println(c == d); // false
        System.out.println(a == e); // true
        System.out.println(a == f); // false
        System.out.println(a.equals(f)); // true

    }
}
