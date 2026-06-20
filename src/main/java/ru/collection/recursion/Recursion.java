package ru.collection.recursion;

import java.util.HashMap;
import java.util.Map;

public class Recursion {
    public int loop(int summary, int index) {
        for (int i = index; i > 0; i--) {
            summary += i;
        }
        return summary;
    }

    public int sum(int summary, int index) {
        if (index > 0) {
            summary += index;
            index--;
            System.out.println(" ".repeat(index) + "before " + index);
            summary = sum(summary, index);
            System.out.println(" ".repeat(index) + "after " + index);
        }
        return summary;
    }

    public long factorialLoop(long f) {
        long res = 1;
        if (f > 0) {
            for (int i = 1; i <= f; i++) {
                res = res * i;
            }
        }
        return res;
    }

    public long factorialRecursion(long f) {
        long res = 1;
        if (f > 0) {
            res = res * f;
            f--;
            res *= factorialRecursion(f);
        }
        return res;
    }

    public long fibonacciLoop(int n) {
        long res = 0L;
        if (n == 1) {
            res = 1L;
        } else if (n > 1) {
            long f1 = 1L;
            long f2 = 1L;
            for (int i = 0; i < (n - 2); i++) {
                res = f2 + f1;
                f1 = f2;
                f2 = res;
            }
        }
        return res;
    }

    public long fibonacciRecursion(int n, Map<Integer, Long> map) {

        if (n == 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        long res = fibonacciRecursion(n - 1, map) + fibonacciRecursion(n - 2, map);
        map.put(n, res);
        return res;
    }

    public static void main(String[] args) {
        Recursion recursion = new Recursion();
//        int resultLoop = recursion.loop(10, 5);
//        int resultRec = recursion.sum(10, 5);
//        System.out.println("result  of loop = " + resultLoop);
//        System.out.println("result  of recursion = " + resultRec);
//        int factorialLoop = (int) recursion.factorialLoop(6);
//        System.out.println("factorial loop : " + factorialLoop);
//        int factorialRec = (int) recursion.factorialRecursion(6);
//        System.out.println("factorial rec : " + factorialRec);

        System.out.println("Start of Loop: " + System.currentTimeMillis() / 1000.0);
        long fibLoop = recursion.fibonacciLoop(100);
        System.out.println("Fibonacci loop : " + fibLoop);
        System.out.println("End of Loop: " + System.currentTimeMillis() / 1000.0);
        long fibRec = recursion.fibonacciRecursion(100, new HashMap<>());
        System.out.println("Fibonacci rec : " + fibRec);
        System.out.println("End of Loop: " + System.currentTimeMillis() / 1000.0);
    }
}
